package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

  // Structured array for convenient portrait loading
  // [String, String, String, String] = [Name, Role, Quote, Image path]
  String[] connor = {"Connor Horn", "Lead Software", "Quote", "views/images/Connor"};
  String[] ben = {"Ben Schmitt", "Assistant Lead", "Quote", "views/images/Ben"};
  String[] emily = {"Emily Gorelik", "Assistant Lead", "Quote", "views/images/Emily"};
  String[] nathanA = {"Nathan Anderson", "Product Owner", "Quote", "views/images/NathanA"};
  String[] sky = {"Sky Tang", "Product Manager", "Quote", "views/images/Sky"};
  String[] john = {"John Carrotta", "Scrum Master", "Quote", "views/images/John"};
  String[] jake = {"Jake Brady", "Document Analyst", "Quote", "views/images/Jake"};
  String[] engjell = {"Engjell Ramadani", "Front End", "Quote", "views/images/Engjell"};
  String[] nicholas = {"Nicholas Heineman", "Front End", "Quote", "views/images/Nicholas"};
  String[] nathanP = {"Nathan Pollock", "Back End", "Quote", "views/images/NathanP"};
  String[] ethan = {"Ethan Catania", "Full Stack", "Quote", "views/images/Ethan"};

  // Functions to load specific info into the Portrait template
  @FXML
  private void loadConnor() throws IOException {
    newLoadPopup(connor);
  }

  @FXML
  private void loadBen() throws IOException {
    newLoadPopup(ben);
  }

  @FXML
  private void loadEmily() throws IOException {
    newLoadPopup(emily);
  }

  @FXML
  private void loadNathanA() throws IOException {
    newLoadPopup(nathanA);
  }

  @FXML
  private void loadSky() throws IOException {
    newLoadPopup(sky);
  }

  @FXML
  private void loadJohn() throws IOException {
    newLoadPopup(john);
  }

  @FXML
  private void loadJake() throws IOException {
    newLoadPopup(jake);
  }

  @FXML
  private void loadEngjell() throws IOException {
    newLoadPopup(engjell);
  }

  @FXML
  private void loadNicholas() throws IOException {
    newLoadPopup(nicholas);
  }

  @FXML
  private void loadNathanP() throws IOException {
    newLoadPopup(nathanP);
  }

  @FXML
  private void loadEthan() throws IOException {
    newLoadPopup(ethan);
  }
}
