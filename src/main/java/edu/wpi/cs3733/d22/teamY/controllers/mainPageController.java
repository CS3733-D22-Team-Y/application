package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class mainPageController {
  Scene welcomePage = null;
  Scene requestMenu = null;
  Scene activeRequests = null;

  @FXML
  void welcomePage() throws IOException {
    welcomePage =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/welcomePage.fxml"))));
    App.getInstance().setScene(welcomePage);
  }

  @FXML
  void requestMenu() throws IOException {
    // Sets scene to service request page
    requestMenu =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));
    App.getInstance().setScene(requestMenu);
  }

  @FXML
  void activeRequests() throws IOException {
    // Sets scene to view service request
    activeRequests =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/activeRequests.fxml"))));
    App.getInstance().setScene(activeRequests);
  }
}
