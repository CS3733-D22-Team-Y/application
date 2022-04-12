package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.TranslatorRequest;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TranslatorRequestController {
  // Radio Buttons
  @FXML private MFXRadioButton spanishRadioButton;
  @FXML private MFXRadioButton chineseRadioButton;
  @FXML private MFXRadioButton germanRadioButton;
  @FXML private MFXRadioButton arabicRadioButton;
  @FXML private MFXRadioButton otherRadioButton;

  @FXML private MFXTextField input_OtherLanguage;
  // Input fields
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;

  // Language types text. These should be changed depending on what the names in the database are.
  private final String spanishText = "spanish";
  private final String chineseText = "chinese";
  private final String germanText = "german";
  private final String arabicText = "arabic";
  private final String otherText = "other";

  public TranslatorRequestController() {}

  @FXML
  void initialize() {
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

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
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.TRANSLATOR_REQUEST));

    DBManager.save(
        new TranslatorRequest(
            nextRequest,
            roomID,
            assignedNurse,
            requestStatus,
            additionalNotes,
            languageTypeSelected));

    System.out.println("Saved TranslatorRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
            spanishRadioButton,
            chineseRadioButton,
            germanRadioButton,
            arabicRadioButton,
            otherRadioButton)
        && !Objects.equals(roomsComboBox.getValue(), "")
        && !Objects.equals(input_AssignedNurse.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AssignedNurse.getText(),
          "open",
          input_AdditionalNotes.getText(),
          getLanguageType());
      errorLabel.setText("");
      SceneLoading.loadPopup(
          "views/popups/ReqSubmitted.fxml", "views/requestTypes/TranslatorRequest.fxml");
    } else {
      errorLabel.setText("Missing Required Fields.");
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
    if (otherRadioButton.isSelected()) {
      RequestControllerUtil.resetTextFields(input_OtherLanguage);
    }
    RequestControllerUtil.resetRadioButtons(
        spanishRadioButton,
        chineseRadioButton,
        germanRadioButton,
        arabicRadioButton,
        otherRadioButton);
    RequestControllerUtil.resetTextFields(
        roomsHiddenField, input_AssignedNurse, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
