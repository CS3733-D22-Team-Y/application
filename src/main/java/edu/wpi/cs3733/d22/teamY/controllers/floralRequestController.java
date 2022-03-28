package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class floralRequestController {
  @FXML private JFXRadioButton getWellSoonBouquetRadioButton;
  @FXML private JFXRadioButton newBabyRadioButton;
  @FXML private JFXRadioButton bouquetOfTheDayRadioButton;
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextArea input_AdditionalNotes;

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    resetAllFields();
    App.getInstance().setSceneToRequestMenu(); // Returns to request menu
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    getWellSoonBouquetRadioButton.setSelected(false);
    newBabyRadioButton.setSelected(false);
    bouquetOfTheDayRadioButton.setSelected(false);
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AdditionalNotes.setText("");
  }
}
