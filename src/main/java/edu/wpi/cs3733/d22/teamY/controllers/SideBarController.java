package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBUtils;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SideBarController {
  @FXML AnchorPane mainPane;
  @FXML private Label nameLabel;
  @FXML private Label accessLevel;
  @FXML private ToggleButton dbToggle;

  // Scaling
  @FXML private VBox bottomSidebarText;
  @FXML private Rectangle sidebarFrame;

  @FXML
  void initialize() {
    // accessLevel.setText("Access Level: " + PersonalSettings.currentEmployee.getAccessLevel());
    // nameLabel.setText(PersonalSettings.currentEmployee.getName());
    // mainPane.setVisible(true);
  }

  @FXML
  void initializeScale() {
    Scene currScene = bottomSidebarText.getScene();
    bottomSidebarText.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    sidebarFrame.scaleYProperty().bind(currScene.heightProperty().divide(800));
    sidebarFrame.layoutYProperty().bind(sidebarFrame.scaleYProperty().multiply(390).subtract(380));
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

  @FXML
  void dbSwitcherToggle() {
    boolean state = dbToggle.isSelected();
    DBUtils.switchDBType(state);
  }
}
