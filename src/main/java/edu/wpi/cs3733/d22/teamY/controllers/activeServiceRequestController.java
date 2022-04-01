package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class activeServiceRequestController extends AbsGlobalControllerFuncs {
  @FXML private Pane sidebarPane;

  @FXML
  void initialize() throws IOException {
    // Show sidebar
    loadSidebar(sidebarPane);
  }

  @FXML
  void mainMenu() throws IOException {
    loadScene("views/mainPage.fxml");
  }
}
