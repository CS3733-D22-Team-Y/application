package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class changeThemeController {
  // Base pane for displaying new scenes
  @FXML private Pane mapPane;
  // Sidebar
  @FXML private Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;
  AnchorPane sidebar = null;

  @FXML
  void initialize() throws IOException {
    sidebar = SidebarUtil.initializeSidebar(sidebarPane);
  }

  @FXML
  void mainMenu() throws IOException {
    SceneLoading.loadScene("views/mainPage.fxml");
  }

  // Sidebar
  @FXML
  void openSidebarLayout() {
    SidebarUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }

  @FXML
  void closeSidebarLayout() {
    SidebarUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
  }

  @FXML
  void changeTheme() throws IOException {
    SceneLoading.loadScene("views/ChangeTheme.fxml", "views/css/theme1.css");
  }

  @FXML
  void backToNormal() throws IOException {
    SceneLoading.loadScene("views/ChangeTheme.fxml", "views/css/normalTheme.css");
  }
}
