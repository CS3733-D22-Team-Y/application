package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MedEquipTablePageController {

  @FXML private TableView<MedEquip> medEquipTableView;

  @FXML private AnchorPane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  @FXML
  public void initialize() throws IOException {

    List<MedEquip> medEquips;
    try {
      medEquips = DBManager.getAll(MedEquip.class);
    } catch (Exception e) {
      e.printStackTrace();
      medEquips = Collections.emptyList();
    }

    TableColumn<MedEquip, String> equipIDCol = new TableColumn<>("Equipment ID");
    equipIDCol.setCellValueFactory(new PropertyValueFactory<>("equipID"));

    TableColumn<MedEquip, String> equipTypeCol = new TableColumn<>("Equipment Type");
    equipTypeCol.setCellValueFactory(new PropertyValueFactory<>("equipType"));

    TableColumn<MedEquip, String> equipLocIdCol = new TableColumn<>("Equipment Location");
    equipLocIdCol.setCellValueFactory(new PropertyValueFactory<>("equipLocId"));

    TableColumn<MedEquip, String> isCleanCol = new TableColumn<>("Clean Status");
    isCleanCol.setCellValueFactory(new PropertyValueFactory<>("isClean"));

    TableColumn<MedEquip, String> statusCol = new TableColumn<>("Equipment Status");
    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));


    medEquipTableView.getColumns().add(equipIDCol);
    medEquipTableView.getColumns().add(equipTypeCol);
    medEquipTableView.getColumns().add(equipLocIdCol);
    medEquipTableView.getColumns().add(isCleanCol);
    medEquipTableView.getColumns().add(statusCol);

    for (MedEquip e : medEquips) {
      //You can change how the data is displayed here
      medEquipTableView.getItems().add(e);
    }

    NewSceneLoading.loadSidebar(sidebarPane);
  }

  public static class ChangeTheme {
    // Base pane for displaying new scenes
    @FXML private Pane mapPane;
    // Sidebar
    @FXML private Pane sidebarPane;
    @FXML private JFXButton closeSidebarHiddenButton;
    @FXML private JFXHamburger sidebarHamburger;
    AnchorPane sidebar = null;

    // Sidebar
    @FXML
    void openSidebarLayout() {
      SidebarUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
    }

    @FXML
    void closeSidebarLayout() {
      SidebarUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
    }
  }
}
