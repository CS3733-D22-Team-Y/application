package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
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

public class ActServReqTablePageController extends AbsGlobalControllerFuncs {
  @FXML private TableView<MedEquipReq> medEquipReqTableView;

  @FXML Pane sidebarPane;

  public void initialize() {
    List<MedEquipReq> medEquipReqs = Collections.emptyList();
    try {
      medEquipReqs = DBManager.getAll(MedEquipReq.class);
    } catch (Exception e) {
      e.printStackTrace();
    }

    ObservableList<MedEquipReq> locationsObservable = FXCollections.observableList(medEquipReqs);

    TableColumn<MedEquipReq, Integer> requestNum = new TableColumn<>("Request Number");
    TableColumn<MedEquipReq, Integer> equipID = new TableColumn<>("Equipment ID");
    TableColumn<MedEquipReq, Integer> targetNodeID = new TableColumn<>("Target Node ID");

    requestNum.setCellValueFactory(new PropertyValueFactory<>("requestNum"));
    equipID.setCellValueFactory(new PropertyValueFactory<>("equipID"));
    targetNodeID.setCellValueFactory(new PropertyValueFactory<>("targetLocID"));

    medEquipReqTableView.getColumns().add(requestNum);
    medEquipReqTableView.getColumns().add(equipID);
    medEquipReqTableView.getColumns().add(targetNodeID);
    medEquipReqTableView.setItems(locationsObservable);
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
