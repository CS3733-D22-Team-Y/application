package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class laundryRequestController {
  @FXML private JFXRadioButton hazardousRadioButton;
  @FXML private JFXRadioButton scrubsRadioButton;
  @FXML private JFXRadioButton linensRadioButton;
  @FXML private TextField laundryRequestRoomID;
  @FXML private TextField laundryPatientName;
  @FXML private TextArea laundryAdditionalNotes;

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    App.getInstance().setSceneToRequestMenu(); // Returns to request menu
  }

  @FXML
  void resetAllFields() {
    hazardousRadioButton.setSelected(false);
    scrubsRadioButton.setSelected(false);
    linensRadioButton.setSelected(false);
    laundryRequestRoomID.setText("");
    laundryPatientName.setText("");
    laundryAdditionalNotes.setText("");
  }
}
