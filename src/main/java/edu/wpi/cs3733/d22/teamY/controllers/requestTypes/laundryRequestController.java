package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.controllers.AbsGlobalControllerFuncs;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

  private Scene requestMenu = null;

  private final String hazardousText = "hazardous";
  private final String scrubsText = "scrubs";
  private final String linensText = "linens";

  public laundryRequestController() throws IOException {}

  // BACKEND PEOPLE,THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param laundryTypeSelected The type of result selected.
   * @throws DaoAddException if there is an error adding something to the database (one of the
   *     fields is invalid)
   */
  private void submitRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String laundryTypeSelected)
      throws DaoAddException {
    // Code to add the fields to the database goes here.
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a lab result choice has been made.
    if (RequestControllerUtil.isRadioButtonSelected(
        hazardousRadioButton, linensRadioButton, scrubsRadioButton)) {
      try {
        submitRequest(
            input_RoomID.getText(),
            input_PatientName.getText(),
            input_AssignedNurse.getText(),
            input_RequestStatus.getText(),
            input_AdditionalNotes.getText(),
            getResultType());
      }
      // Thrown if one of the fields in submitRequest is invalid.
      catch (DaoAddException e) {
        System.out.println("One of more fields was invalid.");
      }
    } else {
      System.out.println("Please select the type of laundry.");
    }
  }

  // Returns the database name of the selected radio button.
  private String getResultType() {
    if (hazardousRadioButton.isSelected()) return hazardousText;
    if (linensRadioButton.isSelected()) return linensText;
    if (scrubsRadioButton.isSelected()) return scrubsText;
    // Will never be used
    return "";
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    loadScene("views/requestMenu.fxml");
    resetAllFields();
  }

  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        scrubsRadioButton, linensRadioButton, hazardousRadioButton);
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_RequestStatus, input_AssignedNurse, input_PatientName);
    input_AdditionalNotes.setText("");
  }
}
