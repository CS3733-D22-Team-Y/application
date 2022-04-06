package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainPageController {

  @FXML
  void requestMenu() throws IOException {
    SceneLoading.loadScene("views/requestMenu.fxml");
  }

  @FXML
  void activeRequest() throws IOException {
    SceneLoading.loadScene("views/activeServiceRequest.fxml");
  }

  @FXML
  void viewMap() throws IOException {
    SceneLoading.loadScene("views/mapPage.fxml");
  }

  @FXML
  void locationTable() throws IOException {
    SceneLoading.loadScene("views/locTablePage.fxml");
  }

  @FXML
  void serviceTable() throws IOException {
    SceneLoading.loadScene("views/ActServReqTablePage.fxml");
  }

  @FXML
  void changeTheme() throws IOException {
    SceneLoading.loadScene("views/ChangeTheme.fxml");
  }

  @FXML
  void personalSettings() throws IOException {
    SceneLoading.loadScene("views/PersonalSettings.fxml");
  }

  // Specific Request Types
  @FXML
  void medRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/medicalEquipmentRequest.fxml");
  }

  @FXML
  void labRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/labRequest.fxml");
  }

  @FXML
  void mealRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/mealRequest.fxml");
  }

  @FXML
  void laundryRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/laundryRequest.fxml");
  }

  @FXML
  void floralRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/floralRequest.fxml");
  }

  @FXML
  void securityRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/securityServicesRequest.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    System.out.println("pressed button");
    SceneLoading.loadPopup("views/ConfirmClose.fxml", "views/mainPage.fxml");
  }
}
