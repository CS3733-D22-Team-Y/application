package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;

import edu.wpi.cs3733.d22.teamY.controllers.AbsGlobalControllerFuncs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class laundryRequestController extends AbsGlobalControllerFuncs {
  // Radio Buttons
  @FXML private JFXRadioButton hazardousRadioButton;
  @FXML private JFXRadioButton scrubsRadioButton;
  @FXML private JFXRadioButton linensRadioButton;
  // Text inputs
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;
  // Additional  Notes
  @FXML private TextArea input_AdditionalNotes;

  public laundryRequestController() throws IOException {}

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    loadScene("views/requestMenu.fxml");
    resetAllFields();
  }

  @FXML
  void resetAllFields() {
    hazardousRadioButton.setSelected(false);
    scrubsRadioButton.setSelected(false);
    linensRadioButton.setSelected(false);
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AssignedNurse.setText("");
    input_RequestStatus.setText("");
    input_AdditionalNotes.setText("");
  }
}
