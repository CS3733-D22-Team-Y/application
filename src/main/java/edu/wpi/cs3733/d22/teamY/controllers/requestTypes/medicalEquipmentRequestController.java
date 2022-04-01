package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DataManager;
import edu.wpi.cs3733.d22.teamY.MedEquipReq;
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

  // Equipment types text. These should be changed depending on what the names in the database are.
  private final String bedText = "bed";
  private final String xrayText = "xray";
  private final String infusionPumpText = "infusionPump";
  private final String reclinerText = "recliner";

  private Scene requestMenu = null;

  // Currently  the submit button is tied to submitData, when it should be tied to submitButton.

  public medicalEquipmentRequestController() throws IOException {}

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param equipmentTypeSelected The type of bouquet selected.
   * @throws DaoAddException if there is an error adding something to the database (one of the
   *     fields is invalid)
   */
  private void submitRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String equipmentTypeSelected)
      throws DaoAddException {
    // Code to add the fields to the database goes here.
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
        reclinerRadioButton, infusionPumpRadioButton, xrayRadioButton, bedRadioButton)) {
      try {
        submitRequest(
            input_RoomID.getText(),
            input_PatientName.getText(),
            input_AssignedNurse.getText(),
            input_RequestStatus.getText(),
            input_AdditionalNotes.getText(),
            getEquipmentType());
      }
      // Thrown if one of the fields in submitRequest is invalid.
      catch (DaoAddException e) {
        System.out.println("One of more fields was invalid.");
      }
    } else {
      System.out.println("Please select an equipment option.");
    }
  }

  // Returns the database name of the selected radio button.
  private String getEquipmentType() {
    if (reclinerRadioButton.isSelected()) return reclinerText;
    if (bedRadioButton.isSelected()) return bedText;
    if (infusionPumpRadioButton.isSelected()) return infusionPumpText;
    if (xrayRadioButton.isSelected()) return xrayText;
    // Should never happen
    return ("");
  }

  @FXML
  void backToRequestMenu() throws IOException {
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
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_PatientName, input_AssignedNurse, input_RequestStatus);

    input_AdditionalNotes.setText("");
    // Radio buttons
    RequestControllerUtil.resetRadioButtons(
        bedRadioButton, xrayRadioButton, infusionPumpRadioButton, reclinerRadioButton);
  }

  @FXML
  void submitData() throws DaoAddException {
    MedEquipReq submission =
        new MedEquipReq("1422", input_AdditionalNotes.getText(), input_RoomID.getText());
    DataManager.getMedEquipReqDao().addMedEquipReq(submission);
  }
}
