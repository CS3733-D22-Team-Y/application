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

  //Linking to PortraitController
  PortraitController portrait = new PortraitController();

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
    portrait.rewriteInfo(connor);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadBen() throws IOException {
    portrait.rewriteInfo(ben);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadEmily() throws IOException {
    portrait.rewriteInfo(emily);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadNathanA() throws IOException {
    portrait.rewriteInfo(nathanA);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadSky() throws IOException {
    portrait.rewriteInfo(sky);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadJohn() throws IOException {
    portrait.rewriteInfo(john);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadJake() throws IOException {
    portrait.rewriteInfo(jake);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadEngjell() throws IOException {
    portrait.rewriteInfo(engjell);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadNicholas() throws IOException {
    portrait.rewriteInfo(nicholas);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadNathanP() throws IOException {
    portrait.rewriteInfo(nathanP);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }

  @FXML
  private void loadEthan() throws IOException {
    portrait.rewriteInfo(ethan);
    SceneLoading.loadPopup("views/popups/Portrait.fxml", "views/popups/AboutUs.fxml");
  }
}