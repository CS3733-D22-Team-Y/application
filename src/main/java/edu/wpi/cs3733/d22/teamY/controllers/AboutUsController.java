package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutUsController {

  @FXML private MFXButton backButton;

  // ImageViews with images
  @FXML private ImageView loadConnor;
  @FXML private ImageView loadBen;
  @FXML private ImageView loadEmily;
  @FXML private ImageView loadNathanA;
  @FXML private ImageView loadSky;
  @FXML private ImageView loadJohn;
  @FXML private ImageView loadJake;
  @FXML private ImageView loadEngjell;
  @FXML private ImageView loadNicholas;
  @FXML private ImageView loadNathanP;
  @FXML private ImageView loadEthan;

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
  String[] connor = {"Connor Horn", "Lead Software", "Quote", "views/images/Connor.jpg"};
  String[] ben = {"Ben Schmitt", "Assistant Lead", "Quote", "views/images/Ben.jpg"};
  String[] emily = {"Emily Gorelik", "Assistant Lead", "Quote", "views/images/Emily.jpg"};
  String[] nathanA = {"Nathan Anderson", "Product Owner", "Quote", "views/images/NathanA.jpg"};
  String[] sky = {
    "Sky Tang",
    "Product Manager",
    "We're all different people throughout our lives, and that's okay, that's good, you've gotta keep moving, so long as you remember all the people that you used to be - Matt Smith",
    "views/images/Sky.jpg"
  };
  String[] john = {
    "John Carrotta",
    "Scrum Master",
    "Break main and it's hands on sight - Basically everyone",
    "views/images/John.jpg"
  };
  String[] jake = {
    "Jake Brady",
    "Document Analyst",
    "I'm the video game boy, I'm the one who wins! - Arin Hanson",
    "views/images/Jake.jpg"
  };
  String[] engjell = {"Engjell Ramadani", "Front End", "Quote", "views/images/Engjell.jpg"};
  String[] nicholas = {"Nicholas Heineman", "Front End", "Quote", "views/images/Nicholas.jpg"};
  String[] nathanP = {"Nathan Pollock", "Back End", "Quote", "views/images/NathanP.jpg"};
  String[] ethan = {"Ethan Catania", "Full Stack", "Quote", "views/images/Ethan.jpg"};

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
