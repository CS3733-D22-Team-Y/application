package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.awt.*;
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
    sidebar = SceneLoadingUtil.initializeSidebar(sidebarPane);
  }

  // All below methods call corresponding scene setting methods in an instance of app
  // These are called by the corresponding button in the Request Menu (requestMenu.fxml)

  @FXML
  void mainMenu() throws IOException {
    SceneLoadingUtil.loadScene("views/mainPage.fxml");
  }

  @FXML
  void securityServices() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/securityServicesRequest.fxml");
  }

  @FXML
  void medicalEquipment() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/medicalEquipmentRequest.fxml");
  }

  @FXML
  void floralDelivery() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/floralRequest.fxml");
  }

  @FXML
  void laundryServices() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/laundryRequest.fxml");
  }

  @FXML
  void mealDelivery() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/mealRequest.fxml");
  }

  @FXML
  void labResults() throws IOException {
    SceneLoadingUtil.loadScene("views/requestTypes/labRequest.fxml");
  }

  // Sidebar
  @FXML
  void openSidebarLayout() {
    SceneLoadingUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }

  @FXML
  void closeSidebarLayout() {
    SceneLoadingUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }
}
