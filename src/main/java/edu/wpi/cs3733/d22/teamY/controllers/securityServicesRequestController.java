package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class securityServicesRequestController {
  // Text input
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private JFXRadioButton unwantedGuestRadioButton;
  @FXML private JFXRadioButton disruptionRadioButton;
  @FXML private JFXRadioButton theftRadioButton;

  @FXML private JFXRadioButton mostUrgentRadioButton;
  @FXML private JFXRadioButton urgentRadioButton;
  @FXML private JFXRadioButton lowPriorityRadioButton;

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    resetAllFields();
    App.getInstance().setSceneToRequestMenu(); // Returns to request menu
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    // Text input
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AdditionalNotes.setText("");
    // Report type radio buttons
    unwantedGuestRadioButton.setSelected(false);
    disruptionRadioButton.setSelected(false);
    theftRadioButton.setSelected(false);
    // Priority radio buttons
    mostUrgentRadioButton.setSelected(false);
    urgentRadioButton.setSelected(false);
    lowPriorityRadioButton.setSelected(false);
  }
}
