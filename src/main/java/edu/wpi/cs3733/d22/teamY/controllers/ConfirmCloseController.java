package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ConfirmCloseController implements IController {

  @FXML private JFXButton stayButton;

  @FXML
  void logOut() throws IOException {
    SceneLoading.loadScene("views/Welcome.fxml");
    NewSceneLoading.removeScene("views/ChatSelector.fxml");
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

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {}
}
