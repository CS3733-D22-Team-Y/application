package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class requestMenuController {

  public requestMenuController() throws IOException {}

  // All below methods call corresponding scene setting methods in an instance of app
  // These are called by the corresponding button in the Request Menu (requestMenu.fxml)
  @FXML
  void securityServices() throws IOException {
    Scene securityRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(
                    App.class.getResource("views/securityServicesRequest.fxml"))));

    App.getInstance().setScene(securityRequest);
  }

  @FXML
  void medicalEquipment() throws IOException {
    Scene medicalRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(
                    App.class.getResource("views/medicalEquipmentRequest.fxml"))));

    App.getInstance().setScene(medicalRequest);
  }

  @FXML
  void floralDelivery() throws IOException {
    Scene floralRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/floralRequest.fxml"))));

    App.getInstance().setScene(floralRequest);
  }

  @FXML
  void laundryServices() throws IOException {
    Scene laundryRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/laundryRequest.fxml"))));

    App.getInstance().setScene(laundryRequest);
  }

  @FXML
  void mealDelivery() throws IOException {
    Scene mealRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/mealRequest.fxml"))));
    App.getInstance().setScene(mealRequest);
  }

  @FXML
  void activeRequests() throws IOException {
    Scene activeRequests =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/activeRequests.fxml"))));
    App.getInstance().setScene(activeRequests);
  }
}
