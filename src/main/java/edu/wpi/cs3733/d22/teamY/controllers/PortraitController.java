package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PortraitController {

  @FXML private ImageView backButton;

  @FXML private Label nameText;
  @FXML private Label roleText;

  @FXML private ImageView portraitImage;

  @FXML private JFXTextArea quoteText;

  @FXML
  private void backButton() {
    Stage stage;
    stage = (Stage) backButton.getScene().getWindow();
    stage.close();
  }

  // Function to put the right text and image stuff in the right places in the portrait template
  @FXML
  void rewriteInfo(String[] name) throws FileNotFoundException {
    nameText.setText(name[0]);
    roleText.setText(name[1]);
    quoteText.setText(name[2]);
    portraitImage.setImage(new Image(App.class.getResource(name[3]).toString()));
  }

}
