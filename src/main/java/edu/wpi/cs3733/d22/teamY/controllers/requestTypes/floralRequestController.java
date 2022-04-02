package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoadingUtil;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class floralRequestController {
  // Radio Buttons
  @FXML private JFXRadioButton getWellSoonBouquetRadioButton;
  @FXML private JFXRadioButton newBabyRadioButton;
  @FXML private JFXRadioButton bouquetOfTheDayRadioButton;
  // Input fields
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;

  private Scene requestMenu = null;

  // Bouquet types text. These should be changed depending on what the names in the database are.
  private final String getWellSoonBouquetText = "getWellSoon";
  private final String newBabyBouquetText = "newBaby";
  private final String bouquetOfTheDayText = "bouquetOfDay";

  public floralRequestController() {}

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param bouquetTypeSelected The type of bouquet selected.
   * @throws DaoAddException if there is an error adding something to the database (one of the
   *     fields is invalid)
   */
  private void submitRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String bouquetTypeSelected)
      throws DaoAddException {
    // Code to add the fields to the database goes here.
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    SceneLoadingUtil.loadScene("views/requestMenu.fxml");
    resetAllFields();
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
        getWellSoonBouquetRadioButton, newBabyRadioButton, bouquetOfTheDayRadioButton)) {
      try {
        submitRequest(
            input_RoomID.getText(),
            input_PatientName.getText(),
            input_AssignedNurse.getText(),
            input_RequestStatus.getText(),
            input_AdditionalNotes.getText(),
            getBouquetType());
      }
      // Thrown if one of the fields in submitRequest is invalid.
      catch (DaoAddException e) {
        System.out.println("One of more fields was invalid.");
      }
    } else {
      System.out.println("Please select a bouquet option.");
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
        input_RoomID, input_PatientName, input_AssignedNurse, input_RequestStatus);
    input_AdditionalNotes.setText("");
  }
}
