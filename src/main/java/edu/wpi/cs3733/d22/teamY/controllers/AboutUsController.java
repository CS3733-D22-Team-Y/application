package edu.wpi.cs3733.d22.teamY.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AboutUsController {

  @FXML private MFXButton backButton;

  // Rectangles with images
  @FXML private Rectangle loadConnor;
  @FXML private Rectangle loadBen;
  @FXML private Rectangle loadEmily;
  @FXML private Rectangle loadNathanA;
  @FXML private Rectangle loadSky;
  @FXML private Rectangle loadJohn;
  @FXML private Rectangle loadJake;
  @FXML private Rectangle loadEngjell;
  @FXML private Rectangle loadNicholas;
  @FXML private Rectangle loadNathanP;
  @FXML private Rectangle loadEthan;

  @FXML
  void initialize() throws IOException {
    SceneUtil.removeOpacity(
        loadConnor,
        loadBen,
        loadEmily,
        loadNathanA,
        loadSky,
        loadJohn,
        loadJake,
        loadEngjell,
        loadNicholas,
        loadNathanP,
        loadEthan);
  }

  @FXML
  private void backButton() {
    Stage stage;
    stage = (Stage) backButton.getScene().getWindow();
    stage.close();
  }

  // Functions to load specific info into the Portrait template
  @FXML
  private void loadConnor() {
    // SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadBen() {}

  @FXML
  private void loadEmily() {}

  @FXML
  private void loadNathanA() {}

  @FXML
  private void loadSky() {}

  @FXML
  private void loadJohn() {}

  @FXML
  private void loadJake() {}

  @FXML
  private void loadEngjell() {}

  @FXML
  private void loadNicholas() {}

  @FXML
  private void loadNathanP() {}

  @FXML
  private void loadEthan() {}
}
