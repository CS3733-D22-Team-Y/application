package edu.wpi.cs3733.d22.teamY.controllers;

import java.awt.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class requestMenuController extends AbsGlobalControllerFuncs {
  @FXML Pane sidebarPane;

  public requestMenuController() throws IOException {}

  // All below methods call corresponding scene setting methods in an instance of app
  // These are called by the corresponding button in the Request Menu (requestMenu.fxml)

  @FXML
  void mainMenu() throws IOException {
    loadScene("views/mainPage.fxml");
  }

  @FXML
  void securityServices() throws IOException {
    loadScene("views/requestTypes/securityServicesRequest.fxml");
  }

  @FXML
  void medicalEquipment() throws IOException {
    loadScene("views/requestTypes/medicalEquipmentRequest.fxml");
  }

  @FXML
  void floralDelivery() throws IOException {
    loadScene("views/requestTypes/floralRequest.fxml");
  }

  @FXML
  void laundryServices() throws IOException {
    loadScene("views/requestTypes/laundryRequest.fxml");
  }

  @FXML
  void mealDelivery() throws IOException {
    loadScene("views/requestTypes/mealRequest.fxml");
  }

  @FXML
  void labResults() throws IOException {
    loadScene("views/requestTypes/labRequest.fxml");
  }

  // Sidebar
  @FXML
  void autoOpenCloseSidebar() throws IOException {
    if (sidebarPane.getChildren().size() == 0) {
      loadSidebar(sidebarPane);
    } else {
      removeSidebar(sidebarPane);
    }
  }
}
