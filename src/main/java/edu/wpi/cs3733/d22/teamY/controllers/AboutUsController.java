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
  String[] connor = {
    "Connor Horn",
    "Lead Software",
    "\"The only thing that matters about security is the perception of it\" - Connor",
    "views/images/Connor.jpg"
  };
  String[] ben = {"Ben Schmitt", "Assistant Lead", "\"I don't even know\"", "views/images/Ben.jpg"};
  String[] emily = {
    "Emily Gorelik",
    "Assistant Lead",
    "\"This class is giving me gray hair\" - Ben",
    "views/images/Emily.jpg"
  };
  String[] nathanA = {
    "Nathan Anderson",
    "Product Owner",
    "\"https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html\" - Oracle",
    "views/images/NathanA.jpg"
  };
  String[] sky = {
    "Sky Tang",
    "Product Manager",
    "\"We're all different people throughout our lives, and that's okay, that's good, you've gotta keep moving, so long as you remember all the people that you used to be\" - Matt Smith",
    "views/images/Sky.jpg"
  };
  String[] john = {
    "John Carrotta",
    "Scrum Master",
    "\"Break main and it's hands on sight\" - Ass Leads",
    "views/images/John.jpg"
  };
  String[] jake = {
    "Jake Brady",
    "Document Analyst",
    "\"I'm the video game boy, I'm the one who wins!\" - Arin Hanson",
    "views/images/Jake.png"
  };
  String[] engjell = {
    "Engjell Ramadani",
    "Front End",
    "\"Last name ever first name greatest like merging into main I ain't nothing to play with\" - Drake",
    "views/images/Engjell.jpg"
  };
  String[] nicholas = {
    "Nicholas Heineman",
    "Front End",
    "\"Not just the Panes but the Panes and the Panes too. They're like Panes and I slaughtered them like Panes. ... I hate Panes!\" - Couldn't tell you",
    "views/images/Nicholas.png"
  };
  String[] nathanP = {
    "Nathan Pollock", "Back End", "\"Lambda functions - Connor\"", "views/images/NathanP.jpg"
  };
  String[] ethan = {
    "Ethan Catania",
    "Full Stack",
    "\"Dude, get GitHub Desktop\" - Ben Schmitt",
    "views/images/Ethan.jpg"
  };

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
