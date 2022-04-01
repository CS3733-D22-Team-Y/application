package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DaoManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class medicalEquipmentRequestController {
  // Text Inputs
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;

  @FXML private TextArea input_AdditionalNotes;
  // Radio Buttons
  @FXML private JFXRadioButton bedRadioButton;
  @FXML private JFXRadioButton xrayRadioButton;
  @FXML private JFXRadioButton infusionPumpRadioButton;
  @FXML private JFXRadioButton reclinerRadioButton;

  private Scene requestMenu = null;

  public medicalEquipmentRequestController() throws IOException {}

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    if (requestMenu == null) {
      requestMenu =
          new Scene(
              FXMLLoader.load(
                  Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));
    }
    resetAllFields();
    App.getInstance().setScene(requestMenu); // Returns to request menu
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    // Text input
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AssignedNurse.setText("");
    input_RequestStatus.setText("");

    input_AdditionalNotes.setText("");
    // Radio buttons
    bedRadioButton.setSelected(false);
    xrayRadioButton.setSelected(false);
    infusionPumpRadioButton.setSelected(false);
    reclinerRadioButton.setSelected(false);
  }

  @FXML
  void submitData() throws DaoAddException {
    MedEquipReq submission =
        new MedEquipReq("1422", input_AdditionalNotes.getText(), input_RoomID.getText());
    DaoManager.getMedEquipReqDao().addMedEquipReq(submission);
  }
}
