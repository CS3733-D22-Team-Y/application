package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.RequestTypes;
import edu.wpi.cs3733.d22.teamY.controllers.IController;
import edu.wpi.cs3733.d22.teamY.controllers.NewSceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.Scaling;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LabRequestController implements IController {

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
  // Side bar
  @FXML private AnchorPane sidebarPane;
  // Result types text. These should be changed depending on what the names in the database are.
  private final String bloodSampleText = "bloodSample";
  private final String urineSampleText = "urineSample";
  private final String xrayText = "xray";
  private final String catScanText = "catScan";
  private final String mriText = "mri";

  @FXML private AnchorPane mainPane;

  @FXML
  void initialize() throws IOException {
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
    NewSceneLoading.loadSidebar(sidebarPane);
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
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String resultTypeSelected) {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.LAB,
            assignedNurse,
            roomID,
            additionalNotes,
            7,
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
        && !Objects.equals(input_AssignedNurse.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AssignedNurse.getText(),
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
    NewSceneLoading.loadScene("views/RequestMenu.fxml");
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

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleItemAroundCenter(mainPane);
  }
}
