package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class sideBarController {
  @FXML AnchorPane mainPane;

  @FXML
  void loadMainPage() throws IOException {
    SceneLoadingUtil.loadScene("views/mainPage.fxml");
  }

  @FXML
  void loadViewMap() throws IOException {
    SceneLoadingUtil.loadScene("views/mapPage.fxml");
  }

  @FXML
  void loadCreateServiceRequest() throws IOException {
    SceneLoadingUtil.loadScene("views/requestMenu.fxml");
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    SceneLoadingUtil.loadScene("views/activeServiceRequest.fxml");
  }

  @FXML
  void loadLaundryRequest() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/laundryRequest.fxml");
  }

  @FXML
  void loadMedicalEquipment() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/medicalEquipmentRequest.fxml");
  }

  @FXML
  void loadMealDelivery() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/mealRequest.fxml");
  }

  @FXML
  void loadFloralDelivery() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/floralRequest.fxml");
  }

  @FXML
  void loadSecurity() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/securityServicesRequest.fxml");
  }

  @FXML
  void loadLab() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/labRequest.fxml");
  }

  @FXML
  void loadLocationTable() throws IOException {
    SceneLoadingUtil.loadScene("views/locTablePage.fxml");
  }

  @FXML
  void loadMedEquipmentRequests() throws IOException {
    SceneLoadingUtil.loadScene("views/ActServReqTablePage.fxml");
  }

  @FXML
  void killApplication() {
    Platform.exit();
  }
}
