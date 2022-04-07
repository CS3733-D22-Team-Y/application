package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.LabRequest;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LabRequestController {

  // Input fields
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private JFXRadioButton bloodRadioButton;
  @FXML private JFXRadioButton urineRadioButton;
  @FXML private JFXRadioButton xrayRadioButton;
  @FXML private JFXRadioButton catScanRadioButton;
  @FXML private JFXRadioButton mriRadioButton;
  // Error Label
  @FXML private Label errorLabel;

  // Result types text. These should be changed depending on what the names in the database are.
  private final String bloodSampleText = "bloodSample";
  private final String urineSampleText = "urineSample";
  private final String xrayText = "xray";
  private final String catScanText = "catScan";
  private final String mriText = "mri";

  // BACKEND PEOPLE,THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param resultTypeSelected The type of result selected.
   */
  private void submitRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String resultTypeSelected) {
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.LAB_REQUEST));
    DBManager.save(
        new LabRequest(
            nextRequest,
            roomID,
            patientName,
            assignedNurse,
            requestStatus,
            additionalNotes,
            resultTypeSelected));
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a lab result choice has been made.
    if (RequestControllerUtil.isRadioButtonSelected(
        bloodRadioButton, urineRadioButton, xrayRadioButton, catScanRadioButton, mriRadioButton)) {
      submitRequest(
          input_RoomID.getText(),
          input_PatientName.getText(),
          input_AssignedNurse.getText(),
          input_RequestStatus.getText(),
          input_AdditionalNotes.getText(),
          getResultType());
      RequestControllerUtil.resetLabels(errorLabel);
    } else {
      errorLabel.setText("Please select a result type.");
    }
  }

  // Returns the database name of the selected radio button.
  private String getResultType() {
    if (bloodRadioButton.isSelected()) return bloodSampleText;
    if (urineRadioButton.isSelected()) return urineSampleText;
    if (xrayRadioButton.isSelected()) return xrayText;
    if (catScanRadioButton.isSelected()) return catScanText;
    if (mriRadioButton.isSelected()) return mriText;
    // Will never be used
    return "";
  }

  @FXML
  void backToRequestMenu() throws IOException {
    SceneLoading.loadScene("views/RequestMenu.fxml");
    resetAllFields();
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        bloodRadioButton, urineRadioButton, xrayRadioButton, catScanRadioButton, mriRadioButton);
    RequestControllerUtil.resetTextFields(
        input_RoomID,
        input_PatientName,
        input_AssignedNurse,
        input_RequestStatus,
        input_AdditionalNotes);
    RequestControllerUtil.resetLabels(errorLabel);
  }
}
