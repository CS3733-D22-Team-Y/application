package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SecurityServicesRequestController {
  // Text input
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;

  @FXML private TextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private JFXRadioButton unwantedGuestRadioButton;
  @FXML private JFXRadioButton disruptionRadioButton;
  @FXML private JFXRadioButton theftRadioButton;

  @FXML private JFXRadioButton mostUrgentRadioButton;
  @FXML private JFXRadioButton urgentRadioButton;
  @FXML private JFXRadioButton lowPriorityRadioButton;
  // Error Label
  @FXML private Label errorLabel;

  private Scene requestMenu = null;

  // Security types text. These should be changed depending on what the names in the database are.
  private final String unwantedGuestText = "unwantedGuest";
  private final String disruptionText = "disruption";
  private final String theftText = "theft";

  // Security priority text. These should be changed depending on what the names in  the database
  // are.
  private final String mostUrgentText = "mostUrgent";
  private final String urgentText = "urgent";
  private final String lowPriorityText = "lowPriority";

  public SecurityServicesRequestController() throws IOException {}

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param requestTypeSelected The type of request selected.
   * @param requestPriority The priority of the request.
   */
  private void submitRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {
    // Code to add the fields to the database goes here.
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    Boolean typeSelected =
        RequestControllerUtil.isRadioButtonSelected(
            disruptionRadioButton, theftRadioButton, unwantedGuestRadioButton);
    Boolean prioritySelected =
        RequestControllerUtil.isRadioButtonSelected(
            urgentRadioButton, mostUrgentRadioButton, lowPriorityRadioButton);
    // Checks if a bouquet choice has been made
    if (typeSelected && prioritySelected) {
      submitRequest(
          input_RoomID.getText(),
          input_PatientName.getText(),
          input_AssignedNurse.getText(),
          input_RequestStatus.getText(),
          input_AdditionalNotes.getText(),
          getRequestType(),
          getRequestPriority());
      RequestControllerUtil.resetLabels(errorLabel);
    } else {
      // Print error messages
      if (typeSelected) {
        errorLabel.setText("Please select a priority.");
      } else if (prioritySelected) {
        errorLabel.setText("Please select a request type.");
      } else {
        errorLabel.setText("Please select a request type and priority.");
      }
    }
  }

  // Returns the database name of the selected radio button.
  private String getRequestType() {
    if (theftRadioButton.isSelected()) return theftText;
    if (disruptionRadioButton.isSelected()) return disruptionText;
    if (unwantedGuestRadioButton.isSelected()) return unwantedGuestText;
    // Should never happen
    return ("");
  }

  // Returns the database name of the selected radio button.
  private String getRequestPriority() {
    if (lowPriorityRadioButton.isSelected()) return lowPriorityText;
    if (urgentRadioButton.isSelected()) return urgentText;
    if (mostUrgentRadioButton.isSelected()) return mostUrgentText;
    // Should never happen
    return ("");
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    SceneLoading.loadScene("views/RequestMenu.fxml");
    resetAllFields();
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    // Text input
    RequestControllerUtil.resetTextFields(
        input_RoomID,
        input_PatientName,
        input_AssignedNurse,
        input_RequestStatus,
        input_AdditionalNotes);
    // Report type radio buttons
    RequestControllerUtil.resetRadioButtons(
        unwantedGuestRadioButton,
        disruptionRadioButton,
        theftRadioButton,
        mostUrgentRadioButton,
        urgentRadioButton,
        lowPriorityRadioButton);
    RequestControllerUtil.resetLabels(errorLabel);
  }
}
