package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.RequestTypes;
import edu.wpi.cs3733.d22.teamY.controllers.NewSceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LabRequestController {

  // Input fields
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  @FXML private JFXComboBox<String> nursesComboBox;
  @FXML private TextField nursesHiddenField;
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
  // Side bar
  @FXML private AnchorPane sidebarPane;
  // Result types text. These should be changed depending on what the names in the database are.
  private final String bloodSampleText = "bloodSample";
  private final String urineSampleText = "urineSample";
  private final String xrayText = "xray";
  private final String catScanText = "catScan";
  private final String mriText = "mri";

  @FXML
  void initialize() throws IOException {
    resetAllFields();
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
    nursesComboBox.setItems(RequestControllerUtil.allNursesComboBox.getItems());
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  @FXML
  private void setNurseText() {
    nursesHiddenField.setText(nursesComboBox.getValue());
  }

  // BACKEND PEOPLE,THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param resultTypeSelected The type of result selected.
   */
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String resultTypeSelected) {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.LAB,
            assignedNurse,
            roomID,
            additionalNotes,
            1,
            RequestStatus.INCOMPLETE,
            new String[] {resultTypeSelected}));
    System.out.println("Saved LabRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a lab result choice has been made.
    if (RequestControllerUtil.isRadioButtonSelected(
            bloodRadioButton, urineRadioButton, xrayRadioButton, catScanRadioButton, mriRadioButton)
        && !Objects.equals(roomsHiddenField.getText(), "")
        && !Objects.equals(nursesHiddenField.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          DBUtils.convertNameToID(nursesComboBox.getValue()),
          input_AdditionalNotes.getText(),
          getResultType());
      errorLabel.setText("");
      SceneLoading.loadPopup("views/popups/ReqSubmitted.fxml", "views/SideBar.fxml");
      NewSceneLoading.reloadScene("views/ActiveServiceRequest.fxml");
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
      resetAllFields();
    } else {
      errorLabel.setText("Missing Required Fields.");
    }
  }

  @FXML
  void backButton() throws IOException {
    if (RequestControllerUtil.isRadioButtonSelected(
            bloodRadioButton, urineRadioButton, xrayRadioButton, catScanRadioButton, mriRadioButton)
        || !Objects.equals(roomsHiddenField.getText(), "")
        || !Objects.equals(nursesHiddenField.getText(), "")) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/requestTypes/FloralRequest.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/LabResult.fxml");
      }
    } else {
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
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
        roomsHiddenField, nursesHiddenField, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
    nursesComboBox.setValue("");
  }
}
