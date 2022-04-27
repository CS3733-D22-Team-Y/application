package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SRConfirmCloseController implements IController {
  @FXML private JFXButton stayButton;
  @FXML private JFXButton exitButton;

  @FXML
  void exit() throws IOException {
    SceneLoading.stayOnPage = false;
    Stage stage;
    stage = (Stage) exitButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void stayIn() {
    SceneLoading.stayOnPage = true;
    Stage stage;
    stage = (Stage) stayButton.getScene().getWindow();
    stage.close();
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {}
}
