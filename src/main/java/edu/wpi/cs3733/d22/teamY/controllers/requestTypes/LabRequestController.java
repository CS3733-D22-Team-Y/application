package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.SceneUtil;
import edu.wpi.cs3733.d22.teamY.model.LabRequest;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LabRequestController {

  // Input fields
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  // Additional Notes
  @FXML private JFXTextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private MFXRadioButton bloodRadioButton;
  @FXML private MFXRadioButton urineRadioButton;
  @FXML private MFXRadioButton xrayRadioButton;
  @FXML private MFXRadioButton catScanRadioButton;
  @FXML private MFXRadioButton mriRadioButton;
  // Error Label
  @FXML private TextArea errorLabel;

  // Result types text. These should be changed depending on what the names in the database are.
  private final String bloodSampleText = "bloodSample";
  private final String urineSampleText = "urineSample";
  private final String xrayText = "xray";
  private final String catScanText = "catScan";
  private final String mriText = "mri";

  @FXML
  void initialize() {
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  // BACKEND PEOPLE,THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param resultTypeSelected The type of result selected.
   */
  private void submitRequest(String roomID, String additionalNotes, String resultTypeSelected) {
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.LAB_REQUEST));
    DBManager.save(
        new LabRequest(
            nextRequest,
            roomID,
            "",
            RequestStatus.INCOMPLETE,
            additionalNotes,
            resultTypeSelected));
    System.out.println("Saved LabRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a lab result choice has been made.
    if (RequestControllerUtil.isRadioButtonSelected(
            bloodRadioButton, urineRadioButton, xrayRadioButton, catScanRadioButton, mriRadioButton)
        && !Objects.equals(roomsHiddenField.getText(), "")
        && !Objects.equals(input_AssignedNurse.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AdditionalNotes.getText(),
          getResultType());
      errorLabel.setText("");
      SceneUtil.welcomePage.mainPage();
      SceneLoading.loadPopup(
          "views/popups/ReqSubmitted.fxml", "views/requestTypes/LabRequest.fxml");
      resetAllFields();
    } else {
      errorLabel.setText("Missing Required Fields.");
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
        roomsHiddenField, input_AssignedNurse, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
