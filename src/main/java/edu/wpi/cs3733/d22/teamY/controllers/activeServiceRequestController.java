package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    Pane paneToLoad = FXMLLoader.load(App.class.getResource("views/sideBar.fxml"));
    sidebar = (AnchorPane) paneToLoad.lookup("#mainPane");
    sidebarPane.getChildren().clear();
    sidebarPane.getChildren().add(paneToLoad);
  }

  @FXML
  void mainMenu() throws IOException {
    SceneLoadingUtil.loadScene("views/mainPage.fxml");
  }

  @FXML
  void openSidebarLayout() {
    sidebarHamburger.setVisible(false);
    closeSidebarHiddenButton.setVisible(true);
    sidebar.setVisible(true);
  }

  @FXML
  void closeSidebarLayout() {
    sidebarHamburger.setVisible(true);
    closeSidebarHiddenButton.setVisible(false);
    sidebar.setVisible(false);
  }
}
