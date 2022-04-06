package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class sideBarController extends AbsGlobalControllerFuncs {
  @FXML
  void loadMainPage() throws IOException {
    loadScene("views/mainPage.fxml");
  }

  @FXML
  void loadViewMap() throws IOException {
    loadScene("views/mapPage.fxml");
  }

  @FXML
  void loadCreateServiceRequest() throws IOException {
    loadScene("views/requestMenu.fxml");
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    loadScene("views/activeServiceRequest.fxml");
  }

  @FXML
  void loadLaundryRequest() throws IOException {
    loadScene("views/requestTypes/laundryRequest.fxml");
  }

  @FXML
  void loadMedicalEquipment() throws IOException {
    loadScene("views/requestTypes/medicalEquipmentRequest.fxml");
  }

  @FXML
  void loadMealDelivery() throws IOException {
    loadScene("views/requestTypes/mealRequest.fxml");
  }

  @FXML
  void loadFloralDelivery() throws IOException {
    loadScene("views/requestTypes/floralRequest.fxml");
  }

  @FXML
  void loadSecurity() throws IOException {
    loadScene("views/requestTypes/securityServicesRequest.fxml");
  }

  @FXML
  void loadLab() throws IOException {
    loadScene("views/requestTypes/labRequest.fxml");
  }

  @FXML
  void loadLocationTable() throws IOException {
    loadScene("views/locTablePage.fxml");
  }

  @FXML
  void loadMedEquipmentRequests() throws IOException {
    loadScene("views/ActServReqTablePage.fxml");
  }

  @FXML
  void loadPersonalSettings() throws IOException {
    loadScene("views/requestTypes/personalSettings.fxml");
  }

  @FXML
  void loadChangeTheme() throws IOException {
    loadScene("views/ChangeTheme.fxml");
  }

  @FXML
  void killApplication() {
    Platform.exit();
  }
}
