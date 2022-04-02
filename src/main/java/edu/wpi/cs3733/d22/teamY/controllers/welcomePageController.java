package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class welcomePageController {

  @FXML
  void mainPage() throws IOException {
    SceneLoadingUtil.loadScene("views/mainPage.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    Platform.exit();
  }
}
