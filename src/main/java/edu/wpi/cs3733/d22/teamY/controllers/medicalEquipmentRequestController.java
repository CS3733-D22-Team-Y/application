package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class medicalEquipmentRequestController {
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextArea input_AdditionalNotes;
  @FXML private JFXRadioButton bedRadioButton;
  @FXML private JFXRadioButton xrayRadioButton;
  @FXML private JFXRadioButton infusionPumpRadioButton;
  @FXML private JFXRadioButton reclinerRadioButton;

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
    // Radio buttons
    bedRadioButton.setSelected(false);
    xrayRadioButton.setSelected(false);
    infusionPumpRadioButton.setSelected(false);
    reclinerRadioButton.setSelected(false);
  }
}
