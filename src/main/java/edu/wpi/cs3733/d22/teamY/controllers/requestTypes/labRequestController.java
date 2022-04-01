package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.controllers.AbsGlobalControllerFuncs;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class labRequestController extends AbsGlobalControllerFuncs {

  // Input fields
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private JFXRadioButton antibodiesRadioButton;
  @FXML private JFXRadioButton allergiesRadioButton;
  @FXML private JFXRadioButton diagnosisRadioButton;

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    loadScene("views/requestMenu.fxml");
    resetAllFields();
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    antibodiesRadioButton.setSelected(false);
    allergiesRadioButton.setSelected(false);
    diagnosisRadioButton.setSelected(false);
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AssignedNurse.setText("");
    input_RequestStatus.setText("");
    input_AdditionalNotes.setText("");
  }
}
