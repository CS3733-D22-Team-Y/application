package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TranslatorRequestController {
  // Radio Buttons
  @FXML private MFXRadioButton spanishRadioButton;
  @FXML private MFXRadioButton chineseRadioButton;
  @FXML private MFXRadioButton germanRadioButton;
  @FXML private MFXRadioButton arabicRadioButton;
  @FXML private MFXRadioButton otherRadioButton;

  @FXML private MFXTextField input_OtherLanguage;
  // Input fields
  @FXML private MFXTextField input_RoomID;
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private MFXTextField input_PatientID;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private Label errorLabel;

  // Language types text. These should be changed depending on what the names in the database are.
  private final String spanishText = "spanish";
  private final String chineseText = "chinese";
  private final String germanText = "german";
  private final String arabicText = "arabic";
  private final String otherText = "other";

  public TranslatorRequestController() {}

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param languageTypeSelected The type of language selected.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String languageTypeSelected) {
    // Get request Num
    // String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.TRANSLATOR_REQUEST));
    /*
    DBManager.save(
            new TranslatorRequest(
                    nextRequest,
                    roomID,
                    assignedNurse,
                    requestStatus,
                    additionalNotes,
                    bouquetTypeSelected));
     */
    // System.out.println("Saved TranslatorRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
        spanishRadioButton,
        chineseRadioButton,
        germanRadioButton,
        arabicRadioButton,
        otherRadioButton)) {
      submitRequest(
          input_RoomID.getText(),
          input_AssignedNurse.getText(),
          input_PatientID.getText(),
          input_AdditionalNotes.getText(),
          getLanguageType());
      RequestControllerUtil.resetLabels(errorLabel);
    } else {
      errorLabel.setText("Please select a language option.");
    }
  }

  // Returns the database name of the selected radio button.
  private String getLanguageType() {
    if (spanishRadioButton.isSelected()) return spanishText;
    if (chineseRadioButton.isSelected()) return chineseText;
    if (germanRadioButton.isSelected()) return germanText;
    if (arabicRadioButton.isSelected()) return arabicText;
    if (otherRadioButton.isSelected()) return otherText;
    // Should never happen
    return ("");
  }

  @FXML
  void enableMiscBox() {
    input_OtherLanguage.setAllowEdit(true);
    input_OtherLanguage.setSelectable(true);
  }

  @FXML
  void disableMiscBox() {
    input_OtherLanguage.setAllowEdit(false);
    input_OtherLanguage.setSelectable(false);
    RequestControllerUtil.resetTextFields(input_OtherLanguage);
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        spanishRadioButton,
        chineseRadioButton,
        germanRadioButton,
        arabicRadioButton,
        otherRadioButton);
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_AssignedNurse, input_AdditionalNotes, input_PatientID);
    RequestControllerUtil.resetLabels(errorLabel);
  }
}
