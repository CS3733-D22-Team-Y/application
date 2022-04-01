package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class mainPageController extends AbsGlobalControllerFuncs {

  @FXML
  void requestMenu() throws IOException {
    loadScene("views/requestMenu.fxml");
  }

  @FXML
  void activeRequest() throws IOException {
    loadScene("views/activeServiceRequest.fxml");
  }

  @FXML
  void viewMap() throws IOException {
    loadScene("views/mapPage.fxml");
  }

  @FXML
  void locationTable() throws IOException {
    loadScene("views/locTablePage.fxml");
  }

  @FXML
  void serviceTable() throws IOException {
    loadScene("views/ActServReqTablePage.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    Platform.exit();
  }
}
