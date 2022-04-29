package edu.wpi.cs3733.d22.teamY.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CreditsController {
  @FXML private MFXButton backButton;

  @FXML
  private void backButton() {
    Stage stage;
    stage = (Stage) backButton.getScene().getWindow();
    stage.close();
  }






  //                FXMLLoader.load(
  //
  // Objects.requireNonNull(App.class.getResource("views/popups/AboutUs.fxml"))));
  // PortraitController portrait = loader.getController();
  // portrait.rewriteInfo(name);
  // stage.setScene(sceneToLoad);
  // stage.initModality(Modality.APPLICATION_MODAL);
  // stage.initOwner(parentScene.getWindow());
  // stage.showAndWait();
  // }
}
