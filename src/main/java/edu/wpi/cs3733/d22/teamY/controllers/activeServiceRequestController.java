package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class activeServiceRequestController extends AbsGlobalControllerFuncs {
  @FXML private Pane sidebarPane;

  @FXML
  void mainMenu() throws IOException {
    loadScene("views/mainPage.fxml");
  }

  // Sidebar
  @FXML
  void autoOpenCloseSidebar() throws IOException {
    if (sidebarPane.getChildren().size() == 0) {
      loadSidebar(sidebarPane);
    } else {
      removeSidebar(sidebarPane);
    }
  }
}
