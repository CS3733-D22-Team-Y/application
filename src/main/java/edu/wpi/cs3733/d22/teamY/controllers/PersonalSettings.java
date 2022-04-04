package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PersonalSettings extends AbsGlobalControllerFuncs {
  // Base pane for displaying new scenes
  @FXML private Pane mapPane;
  // Sidebar pane
  @FXML private Pane sidebarPane;
  // Hamburger that opens the sidebar
  @FXML private JFXHamburger hamburger;
  // Menu of buttons
  @FXML private VBox buttonBox;
  // Temp button
  @FXML JFXButton tempSidebarButton;

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
