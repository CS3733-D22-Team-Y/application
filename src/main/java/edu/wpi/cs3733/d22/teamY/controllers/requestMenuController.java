package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import java.awt.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    Pane paneToLoad = FXMLLoader.load(App.class.getResource("views/sideBar.fxml"));
    sidebar = (AnchorPane) paneToLoad.lookup("#mainPane");
    sidebarPane.getChildren().clear();
    sidebarPane.getChildren().add(paneToLoad);
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
