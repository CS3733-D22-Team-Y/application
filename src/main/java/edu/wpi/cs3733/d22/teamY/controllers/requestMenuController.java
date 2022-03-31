package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class requestMenuController extends AbsGlobalControllerFuncs {
  @FXML Pane sidebarPane;
  // Test

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

  // Sidebar
  @FXML
  void autoOpenCloseSidebar() throws IOException {
    if (sidebarPane.getChildren().size() == 0) {
      loadSidebar(sidebarPane);
    } else {
      removeSidebar(sidebarPane);
    }
  }

  @FXML
  void labResults() throws IOException {
    Scene labResults =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(
                    App.class.getResource("views/requestTypes/labRequest.fxml"))));
    App.getInstance().setScene(labResults);
  }
}
