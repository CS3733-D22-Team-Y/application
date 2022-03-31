package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class mainPageController {
  Scene welcomePage = null;
  Scene requestMenu = null;
  Scene activeRequests = null;
  Scene viewMap = null;
  Scene table;

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
  void activeRequest() throws IOException {
    // Sets scene to view service request
    activeRequests =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/activeServiceRequest.fxml"))));
    App.getInstance().setScene(activeRequests);
  }

  @FXML
  void viewMap() throws IOException {
    // Sets scene to view service request
    viewMap =
        new Scene(
            FXMLLoader.load(Objects.requireNonNull(App.class.getResource("views/mapPage.fxml"))));
    App.getInstance().setScene(viewMap);
  }

  @FXML
  void locationTable() throws IOException {
    if (table == null) {
      table =
          new Scene(
              FXMLLoader.load(
                  Objects.requireNonNull(App.class.getResource("views/locTablePage.fxml"))));
    }
    App.getInstance().setScene(table);
  }

  @FXML
  void equipmentTable() throws IOException {
    if (table == null) {
      table =
          new Scene(
              FXMLLoader.load(
                  Objects.requireNonNull(
                      App.class.getResource("views/medEquipReqTablePage.fxml"))));
    }
    App.getInstance().setScene(table);
  }

  @FXML
  void killApplication() throws IOException {
    Platform.exit();
  }
}
