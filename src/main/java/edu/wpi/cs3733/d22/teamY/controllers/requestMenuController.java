package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class requestMenuController {
  @FXML Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  public requestMenuController() throws IOException {}

  @FXML
  void initialize() throws IOException {
    // Show sidebar
    sidebar = SidebarUtil.initializeSidebar(sidebarPane);
  }

  // All below methods call corresponding scene setting methods in an instance of app
  // These are called by the corresponding button in the Request Menu (requestMenu.fxml)

  @FXML
  void mainMenu() throws IOException {
    SceneLoading.loadScene("views/mapPage.fxml");
  }

  @FXML
  void securityServices() throws IOException {
    SceneLoading.loadScene("views/requestTypes/securityServicesRequest.fxml");
  }

  @FXML
  void medicalEquipment() throws IOException {
    SceneLoading.loadScene("views/requestTypes/medicalEquipmentRequest.fxml");
  }

  @FXML
  void floralDelivery() throws IOException {
    SceneLoading.loadScene("views/requestTypes/floralRequest.fxml");
  }

  @FXML
  void laundryServices() throws IOException {
    SceneLoading.loadScene("views/requestTypes/laundryRequest.fxml");
  }

  @FXML
  void mealDelivery() throws IOException {
    SceneLoading.loadScene("views/requestTypes/mealRequest.fxml");
  }

  @FXML
  void labResults() throws IOException {
    SceneLoading.loadScene("views/requestTypes/labRequest.fxml");
  }

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
