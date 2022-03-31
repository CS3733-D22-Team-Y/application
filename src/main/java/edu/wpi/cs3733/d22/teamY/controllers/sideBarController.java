package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

public class sideBarController extends AbsGlobalControllerFuncs {
  @FXML
  void loadMainPage() throws IOException {
    loadScene("views/mainPage.fxml");
  }

  @FXML
  void loadViewMap() throws IOException {
    loadScene("views/mapPage.fxml");
  }

  @FXML
  void loadCreateServiceRequest() throws IOException {
    loadScene("views/requestMenu.fxml");
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    loadScene("views/activeServiceRequest.fxml");
  }
}
