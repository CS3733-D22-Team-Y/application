package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.model.FloralRequest;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

public class FloralRequestController {
  // Radio Buttons
  @FXML private MFXRadioButton getWellSoonBouquetRadioButton;
  @FXML private MFXRadioButton newBabyRadioButton;
  @FXML private MFXRadioButton bouquetOfTheDayRadioButton;
  // Input fields
  @FXML private MFXTextField input_RoomID;
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private MFXTextField input_PatientID;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;

  private Scene requestMenu = null;

  // Bouquet types text. These should be changed depending on what the names in the database are.
  private final String getWellSoonBouquetText = "getWellSoon";
  private final String newBabyBouquetText = "newBaby";
  private final String bouquetOfTheDayText = "bouquetOfDay";

  public FloralRequestController() {}

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  public void initialize() {

    // errorTest.setVisible(true);
  }
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param bouquetTypeSelected The type of bouquet selected.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String bouquetTypeSelected)
      throws IOException {
    // Get request Num
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.FLORAL_REQUEST));
    DBManager.save(
        new FloralRequest(
            nextRequest,
            roomID,
            assignedNurse,
            requestStatus,
            additionalNotes,
            bouquetTypeSelected));
    System.out.println("Saved FloralRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
        getWellSoonBouquetRadioButton, newBabyRadioButton, bouquetOfTheDayRadioButton)) {
      submitRequest(
          input_RoomID.getText(),
          input_AssignedNurse.getText(),
          input_PatientID.getText(),
          input_AdditionalNotes.getText(),
          getBouquetType());
      errorLabel.setText("");
    } else {
      errorLabel.setText("Please select a bouquet option.");
    }
  }

  // Returns the database name of the selected radio button.
  private String getBouquetType() {
    if (getWellSoonBouquetRadioButton.isSelected()) return getWellSoonBouquetText;
    if (newBabyRadioButton.isSelected()) return newBabyBouquetText;
    if (bouquetOfTheDayRadioButton.isSelected()) return bouquetOfTheDayText;
    // Should never happen
    return ("");
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        getWellSoonBouquetRadioButton, newBabyRadioButton, bouquetOfTheDayRadioButton);
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_AssignedNurse, input_AdditionalNotes, input_PatientID);
    errorLabel.setText("");
  }
}
