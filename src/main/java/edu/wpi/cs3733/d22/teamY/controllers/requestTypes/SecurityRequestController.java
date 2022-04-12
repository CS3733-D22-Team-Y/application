package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.SecurityServiceRequest;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SecurityRequestController {
  // Text input
  @FXML private MFXTextField input_PatientID;
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;

  @FXML private JFXTextArea input_AdditionalNotes;

  @FXML private MFXTextField input_OtherText;
  // Radio buttons
  @FXML private MFXRadioButton unwantedGuestRadioButton;
  @FXML private MFXRadioButton disruptionRadioButton;
  @FXML private MFXRadioButton theftRadioButton;
  @FXML private MFXRadioButton otherRadioButton;

  @FXML private MFXRadioButton mostUrgentRadioButton;
  @FXML private MFXRadioButton urgentRadioButton;
  @FXML private MFXRadioButton lowPriorityRadioButton;
  // Error Label
  @FXML private TextArea errorLabel;

  private Scene requestMenu = null;

  // Security types text. These should be changed depending on what the names in the database are.
  private static final String unwantedGuestText = "unwantedGuest";
  private static final String disruptionText = "disruption";
  private static final String theftText = "theft";
  private static final String otherText = "other";

  // Security priority text. These should be changed depending on what the names in  the database
  // are.
  private static final String mostUrgentText = "mostUrgent";
  private static final String urgentText = "urgent";
  private static final String lowPriorityText = "lowPriority";

  public SecurityRequestController() throws IOException {}

  @FXML
  void initialize() {
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param requestTypeSelected The type of request selected.
   * @param requestPriority The priority of the request.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {

    String nextRequest =
        String.valueOf(DBUtils.getNextRequestNum(EntryType.SECURITY_SERVICE_REQUEST));
    DBManager.save(
        new SecurityServiceRequest(
            nextRequest,
            roomID,
            assignedNurse,
            requestStatus,
            additionalNotes,
            requestTypeSelected,
            requestPriority));
    System.out.println("Saved SecurityServiceRequest");
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

    Boolean allFields =
        !Objects.equals(input_RoomID.getText(), "")
            && !Objects.equals(input_AssignedNurse.getText(), "")
            && !Objects.equals(input_PatientID.getText(), "");

    // Checks if a bouquet choice has been made
    if (typeSelected && prioritySelected && allFields) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AssignedNurse.getText(),
          input_PatientID.getText(),
          input_AdditionalNotes.getText(),
          getRequestType(),
          getRequestPriority());
      errorLabel.setText("");
    } else {
      // Print error messages
      if (typeSelected || prioritySelected || allFields || !allFields) {
        errorLabel.setText("Missing Required Fields.");
      }
    }
  }

  // Returns the database name of the selected radio button.
  private String getRequestType() {
    if (theftRadioButton.isSelected()) return theftText;
    if (disruptionRadioButton.isSelected()) return disruptionText;
    if (unwantedGuestRadioButton.isSelected()) return unwantedGuestText;
    if (otherRadioButton.isSelected()) return otherText;
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

  @FXML
  void enableMiscBox() {
    input_OtherText.setAllowEdit(true);
    input_OtherText.setSelectable(true);
  }

  @FXML
  void disableMiscBox() {
    input_OtherText.setAllowEdit(false);
    input_OtherText.setSelectable(false);
    RequestControllerUtil.resetTextFields(input_OtherText);
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    // Text input
    RequestControllerUtil.resetTextFields(
        roomsHiddenField,
        input_AssignedNurse,
        input_PatientID,
        input_AdditionalNotes,
        input_OtherText);
    // Report type radio buttons
    RequestControllerUtil.resetRadioButtons(
        unwantedGuestRadioButton,
        disruptionRadioButton,
        theftRadioButton,
        otherRadioButton,
        mostUrgentRadioButton,
        urgentRadioButton,
        lowPriorityRadioButton);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
