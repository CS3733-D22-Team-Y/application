package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DataManager;
import edu.wpi.cs3733.d22.teamY.MedEquipReq;
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

public class MedEquipReqTablePageController extends AbsGlobalControllerFuncs {
  @FXML private TableView<MedEquipReq> medEquipReqTableView;

  public void initialize() {
    List<MedEquipReq> medEquipReqs;
    try {
      medEquipReqs = DataManager.getMedEquipReqDao().getAllMedEquipReq();
    } catch (DaoGetException e) {
      e.printStackTrace();
      medEquipReqs = Collections.emptyList();
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
}
