package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DaoManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ActServReqTablePageController {
  @FXML private TableView<MedEquipReq> medEquipReqTableView;

  @FXML Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  public void initialize() throws IOException {
    List<MedEquipReq> medEquipReqs;
    try {
      medEquipReqs = DaoManager.getMedEquipReqDao().getAllMedEquipReq();
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

    Pane paneToLoad = FXMLLoader.load(App.class.getResource("views/sideBar.fxml"));
    sidebar = (AnchorPane) paneToLoad.lookup("#mainPane");
    sidebarPane.getChildren().clear();
    sidebarPane.getChildren().add(paneToLoad);
  }

  // back button
  @FXML
  void mainMenu() throws IOException {
    SceneLoadingUtil.loadScene("views/mainPage.fxml");
  }

  @FXML
  void openSidebarLayout() {
    sidebarHamburger.setVisible(false);
    closeSidebarHiddenButton.setVisible(true);
    sidebar.setVisible(true);
  }

  @FXML
  void closeSidebarLayout() {
    sidebarHamburger.setVisible(true);
    closeSidebarHiddenButton.setVisible(false);
    sidebar.setVisible(false);
  }
}
