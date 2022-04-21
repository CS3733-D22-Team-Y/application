package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PortraitController {

    @FXML private ImageView backButton;

    @FXML private Label nameText;
    @FXML private Label roleText;

    @FXML private Rectangle imagePlaceholder;

    @FXML private JFXTextArea quoteText;

    @FXML
    private void backButton() {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
