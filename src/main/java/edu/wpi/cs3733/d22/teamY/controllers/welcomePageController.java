package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

public class welcomePageController {

  @FXML
  void mainPage() throws IOException {
    SceneLoading.loadScene("views/mapPage.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    System.out.println("pressed button");
    SceneLoading.loadPopup("views/ConfirmClose.fxml", "views/welcomePage.fxml");
  }
}
