package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PersonalSettingsController {
  // Base pane for displaying new scenes
  @FXML private Pane mapPane;
  // Sidebar
  @FXML private Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;
  AnchorPane sidebar = null;
  public static Employee currentEmployee =
      new Employee(
          "-1", "Guest", "Guest", "Guest", "Guest", "Guest", "Guest", 0, "none", "Guest", " ", " ",
          " ", " ");

  @FXML
  void initialize() throws IOException {}

  @FXML
  void mainMenu() throws IOException {
    SceneLoading.loadScene("views/Map.fxml");
  }
}