package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class mainPageController {

  @FXML
  void requestMenu() throws IOException {
    SceneLoading.loadScene("views/requestMenu.fxml");
  }

  @FXML
  void activeRequest() throws IOException {
    SceneLoading.loadScene("views/activeServiceRequest.fxml");
  }

  @FXML
  void viewMap() throws IOException {
    SceneLoading.loadScene("views/mapPage.fxml");
  }

  @FXML
  void locationTable() throws IOException {
    SceneLoading.loadScene("views/locTablePage.fxml");
  }

  @FXML
  void serviceTable() throws IOException {
    SceneLoading.loadScene("views/ActServReqTablePage.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    Platform.exit();
  }
}
