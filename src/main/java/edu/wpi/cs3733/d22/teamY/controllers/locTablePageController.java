package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DaoManager;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class locTablePageController extends AbsGlobalControllerFuncs {
  @FXML private TableView<Location> tableView;

  @FXML Pane sidebarPane;

  public void initialize() {
    List<Location> locations;
    try {
      locations = DaoManager.getLocationDao().getAllLocations();
    } catch (DaoGetException e) {
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
  }

  // back button
  @FXML
  void mainMenu() throws IOException {
    loadScene("views/mainPage.fxml");
  }

  // Sidebar
  @FXML
  void autoOpenCloseSidebar() throws IOException {
    if (sidebarPane.getChildren().size() == 0) {
      loadSidebar(sidebarPane);
    } else {
      removeSidebar(sidebarPane);
    }
  }
}
