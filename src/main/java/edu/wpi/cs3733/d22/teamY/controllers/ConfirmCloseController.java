package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ConfirmCloseController {

  @FXML private JFXButton stayButton;

  @FXML
  void logOut() throws IOException {
    SceneLoading.loadScene("views/WelcomePage.fxml");
    Stage stage;
    stage = (Stage) stayButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void stayIn() {
    Stage stage;
    stage = (Stage) stayButton.getScene().getWindow();
    stage.close();
  }
}
