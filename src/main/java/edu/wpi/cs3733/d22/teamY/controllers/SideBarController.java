package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SideBarController {
  @FXML AnchorPane mainPane;
  @FXML private Label nameLabel;
  @FXML private Label accessLevel;

  @FXML
  void initialize() {
    accessLevel.setText("Access Level: " + PersonalSettings.currentEmployee.getAccessLevel());
    nameLabel.setText(PersonalSettings.currentEmployee.getName());
    mainPane.setVisible(true);
  }

  @FXML
  void loadMainPage() throws IOException {
    SceneLoading.loadScene("views/Map.fxml");
  }

  @FXML
  void loadViewMap() throws IOException {
    SceneLoading.loadScene("views/Map.fxml");
  }

  @FXML
  void loadCreateServiceRequest() throws IOException {
    SceneLoading.loadScene("views/RequestMenu.fxml");
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    SceneLoading.loadScene("views/ActiveServiceRequest.fxml");
  }

  @FXML
  void loadLaundryRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/LaundryRequest.fxml");
  }

  @FXML
  void loadMedicalEquipment() throws IOException {
    SceneLoading.loadScene("views/requestTypes/MedicalEquipmentRequest.fxml");
  }

  @FXML
  void loadMealDelivery() throws IOException {
    SceneLoading.loadScene("views/requestTypes/MealRequest.fxml");
  }

  @FXML
  void loadFloralDelivery() throws IOException {
    SceneLoading.loadScene("views/requestTypes/FloralRequest.fxml");
  }

  @FXML
  void loadSecurity() throws IOException {
    SceneLoading.loadScene("views/requestTypes/SecurityServicesRequest.fxml");
  }

  @FXML
  void loadLab() throws IOException {
    SceneLoading.loadScene("views/requestTypes/LabRequest.fxml");
  }

  @FXML
  void loadLocationTable() throws IOException {
    SceneLoading.loadScene("views/LocTable.fxml");
  }

  @FXML
  void loadMedEquipmentRequests() throws IOException {
    SceneLoading.loadScene("views/ActServReqTable.fxml");
  }

  @FXML
  void loadChangeTheme() throws IOException {
    SceneLoading.loadScene("views/ChangeTheme.fxml");
  }

  @FXML
  void loadPersonalSettings() throws IOException {
    SceneLoading.loadScene("views/PersonalSettings.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    SceneLoading.loadPopup("views/ConfirmClose.fxml", "views/SideBar.fxml");
  }
}
