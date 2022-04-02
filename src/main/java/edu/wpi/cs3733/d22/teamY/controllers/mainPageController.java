package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class mainPageController {

  @FXML
  void requestMenu() throws IOException {
    SceneLoadingUtil.loadScene("views/requestMenu.fxml");
  }

  @FXML
  void activeRequest() throws IOException {
    SceneLoadingUtil.loadScene("views/activeServiceRequest.fxml");
  }

  @FXML
  void viewMap() throws IOException {
    SceneLoadingUtil.loadScene("views/mapPage.fxml");
  }

  @FXML
  void locationTable() throws IOException {
    SceneLoadingUtil.loadScene("views/locTablePage.fxml");
  }

  @FXML
  void serviceTable() throws IOException {
    SceneLoadingUtil.loadScene("views/ActServReqTablePage.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    Platform.exit();
  }
}
