package edu.wpi.cs3733.d22.teamY.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class HelpButtonController {

  @FXML private MFXButton backButton;

  @FXML
  void backButton() {
    Stage stage;
    stage = (Stage) backButton.getScene().getWindow();
    stage.close();
  }
}
