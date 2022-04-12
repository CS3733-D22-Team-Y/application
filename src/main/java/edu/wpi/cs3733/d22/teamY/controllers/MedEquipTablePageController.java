package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import java.io.IOException;
import java.util.ArrayList;
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

public class MedEquipTablePageController {

  @FXML private TableView<MedEquip> equipTableView;

  private List<MedEquip> medEquips;

  @FXML Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  public void initialize() throws IOException {

    setupTable();
    // updateTable();
  }

  public void setupTable() {

    try {
      medEquips = new ArrayList<MedEquip>();
    } catch (Exception e) {
      e.printStackTrace();
      medEquips = Collections.emptyList();
      System.out.println("nothing here!");
    }

    medEquips.add(new MedEquip("1", "1", "1", "1"));
    ObservableList<MedEquip> locationsObservable = FXCollections.observableList(medEquips);

    TableColumn<MedEquip, String> equipID = new TableColumn<>("Equipment ID");
    TableColumn<MedEquip, String> equipType = new TableColumn<>("Equipment Type");
    TableColumn<MedEquip, String> equipLocId = new TableColumn<>("Equipment Location ID");
    TableColumn<MedEquip, String> isClean = new TableColumn<>("status");

    equipID.setCellValueFactory(new PropertyValueFactory<MedEquip, String>("equipID"));
    equipType.setCellValueFactory(new PropertyValueFactory<MedEquip, String>("equipType"));
    equipLocId.setCellValueFactory(new PropertyValueFactory<MedEquip, String>("equipLocId"));
    isClean.setCellValueFactory(new PropertyValueFactory<MedEquip, String>("isClean"));

    equipTableView.getColumns().add(equipID);
    System.out.println(equipTableView.getColumns().size());
    /*
    for (int i = 0; i < equipTableView.getColumns().size(); i++) {
      System.out.println(equipTableView.getColumns().get(i).getColumns().get(i));
    }
    */

    equipTableView.getColumns().add(equipType);
    equipTableView.getColumns().add(equipLocId);
    equipTableView.getColumns().add(isClean);

    equipTableView.setItems(locationsObservable);
    // medEquips = DBManager.getAll(MedEquip.class);

    for (int i = 0; i < 15; i++) {
      medEquips.add((MedEquip) DBManager.getAll(MedEquip.class).get(i));
    }
  }

  public void updateTable() {
    medEquips = DBManager.getAll(MedEquip.class);
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
