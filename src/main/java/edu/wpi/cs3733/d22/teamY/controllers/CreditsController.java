package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreditsController {
  @FXML private MFXButton backButton;

  @FXML
  private void backButton() {
    Stage stage;
    stage = (Stage) backButton.getScene().getWindow();
    stage.close();
  }

  private void newLoadPopup(String[] name) throws IOException {
    Stage stage = new Stage(StageStyle.UNDECORATED);
    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/popups/Portrait.fxml"));
    Scene sceneToLoad = new Scene(loader.load());
    Scene parentScene =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/popups/AboutUs.fxml"))));
    PortraitController portrait = loader.getController();
    portrait.rewriteInfo(name);
    stage.setScene(sceneToLoad);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(parentScene.getWindow());
    stage.showAndWait();
  }
}
