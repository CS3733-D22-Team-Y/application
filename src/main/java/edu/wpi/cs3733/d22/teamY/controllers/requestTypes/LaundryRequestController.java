package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.LaundryRequest;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LaundryRequestController {
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
  // Error Label
  @FXML private Label errorLabel;

  private Scene requestMenu = null;

  private final String hazardousText = "hazardous";
  private final String scrubsText = "scrubs";
  private final String linensText = "linens";

  public LaundryRequestController() throws IOException {}

  // BACKEND PEOPLE,THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param laundryTypeSelected The type of result selected.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String laundryTypeSelected) {
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.LAUNDRY_REQUEST));
    DBManager.save(
        new LaundryRequest(
            nextRequest,
            roomID,
            assignedNurse,
            requestStatus,
            additionalNotes,
            laundryTypeSelected));
    System.out.println("Saved Laundry Request");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a lab result choice has been made.
    if (RequestControllerUtil.isRadioButtonSelected(
        hazardousRadioButton, linensRadioButton, scrubsRadioButton)) {
      submitRequest(
          input_RoomID.getText(),
          input_AssignedNurse.getText(),
          input_RequestStatus.getText(),
          input_AdditionalNotes.getText(),
          getResultType());
      RequestControllerUtil.resetLabels(errorLabel);
    } else {
      errorLabel.setText("Please select the type of laundry.");
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
    SceneLoading.loadScene("views/RequestMenu.fxml");
    resetAllFields();
  }

  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        scrubsRadioButton, linensRadioButton, hazardousRadioButton);
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_RequestStatus, input_AssignedNurse, input_AdditionalNotes);
    RequestControllerUtil.resetLabels(errorLabel);
  }
}
