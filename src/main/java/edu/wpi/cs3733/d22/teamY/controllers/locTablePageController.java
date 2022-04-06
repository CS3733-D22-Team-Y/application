package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.model.Location;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class locTablePageController {
  @FXML private TableView<Location> tableView;

  @FXML Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  public void initialize() throws IOException {
    List<Location> locations;
    try {
      locations = DBManager.getAll(Location.class);
    } catch (Exception e) {
      e.printStackTrace();
      locations = Collections.emptyList();
    }

    ObservableList<Location> locationsObservable = FXCollections.observableList(locations);

    TableColumn<Location, String> nodeID = new TableColumn<>("Node ID");
    TableColumn<Location, Integer> xCoord = new TableColumn<>("X Coord");
    TableColumn<Location, Integer> yCoord = new TableColumn<>("Y Coord");
    TableColumn<Location, String> floor = new TableColumn<>("Floor");
    TableColumn<Location, String> nodeType = new TableColumn<>("Node Type");
    TableColumn<Location, String> longName = new TableColumn<>("Long Name");
    TableColumn<Location, String> shortName = new TableColumn<>("Short Name");

    nodeID.setCellValueFactory(new PropertyValueFactory<>("nodeID"));
    xCoord.setCellValueFactory(new PropertyValueFactory<>("xCoord"));
    yCoord.setCellValueFactory(new PropertyValueFactory<>("yCoord"));
    floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
    nodeType.setCellValueFactory(new PropertyValueFactory<>("nodeType"));
    longName.setCellValueFactory(new PropertyValueFactory<>("longName"));
    shortName.setCellValueFactory(new PropertyValueFactory<>("shortName"));

    tableView.getColumns().add(nodeID);
    tableView.getColumns().add(xCoord);
    tableView.getColumns().add(yCoord);
    tableView.getColumns().add(floor);
    tableView.getColumns().add(nodeType);
    tableView.getColumns().add(longName);
    tableView.getColumns().add(shortName);
    tableView.setItems(locationsObservable);

    sidebar = SidebarUtil.initializeSidebar(sidebarPane);
  }

  // back button
  @FXML
  void mainMenu() throws IOException {
    SceneLoading.loadScene("views/mapPage.fxml");
  }

  // Sidebar
  @FXML
  void openSidebarLayout() {
    SidebarUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }

  @FXML
  void closeSidebarLayout() {
    SidebarUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }

  @FXML
  void refreshLocations() throws IOException {
    DBUtils.refreshLocationsFromCSV();
    SceneLoading.loadScene("views/locTablePage.fxml");
  }

  @FXML
  void deleteLocations() throws IOException {
    DBUtils.deleteLocations();
    SceneLoading.loadScene("views/locTablePage.fxml");
  }
}
