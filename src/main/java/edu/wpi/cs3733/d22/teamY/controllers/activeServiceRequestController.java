package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class activeServiceRequestController {
  @FXML private Pane sidebarPane;

  @FXML private JFXButton backButton;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;
  AnchorPane sidebar = null;

  @FXML
  void initialize() throws IOException {
    sidebar = SceneLoadingUtil.initializeSidebar(sidebarPane);
  }

  @FXML
  void mainMenu() throws IOException {
    SceneLoadingUtil.loadScene("views/mainPage.fxml");
  }

  @FXML
  void openSidebarLayout() {
    SceneLoadingUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }

  @FXML
  void closeSidebarLayout() {
    SceneLoadingUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }
}
