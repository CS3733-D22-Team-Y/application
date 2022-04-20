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
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MaintenanceRequestController {

  @FXML private AnchorPane sidebarPane;

  @FXML private JFXComboBox<String> medEquipComboBox;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  @FXML private TextField medEquipHiddenField;
  @FXML private JFXTextArea input_AdditionalNotes;
  @FXML private MFXTextField input_OtherText;
  @FXML private MFXRadioButton medEquipRadioButton;
  @FXML private MFXRadioButton elevatorRadioButton;
  @FXML private MFXRadioButton lightsRadioButton;
  @FXML private MFXRadioButton otherRadioButton;

  @FXML private MFXRadioButton replacementRadioButton;
  @FXML private MFXRadioButton maintenanceRadioButton;
  @FXML private MFXRadioButton unsureRadioButton;
  @FXML private TextArea errorLabel;

  private Scene requestMenu = null;

  //
  //  Maintenance types text. These should be changed depending on what the names in the database
  // are.
  private static final String medicalEquipmentText = "medicalEquipment";
  private static final String elevatorText = "elevator";
  private static final String lightsText = "lights";
  private static final String otherText = "other";

  // Maintenance priority text. These should be changed depending on what the names in  the database
  // are.
  private static final String maintenanceText = "maintenance";
  private static final String replacementText = "replacement";
  private static final String unsureText = "unsure";

  public MaintenanceRequestController() throws IOException {}

  @FXML
  void initialize() throws IOException {
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
    medEquipComboBox.setItems(RequestControllerUtil.allmedEquipComboBox.getItems());
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  @FXML
  private void setMedicalEquipmentText() {
    medEquipHiddenField.setText(medEquipComboBox.getValue());
  }

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param requestTypeSelected The type of request selected.
   * @param maintenanceRequestPriority The priority of the request.
   */
  private void submitRequest(
      String roomID,
      String additionalNotes,
      String requestTypeSelected,
      String maintenanceRequestPriority) {

    DBManager.save(
        new ServiceRequest(
            RequestTypes.MAINTENANCE,
            "none",
            roomID,
            additionalNotes,
            1,
            RequestStatus.INCOMPLETE,
            new String[] {requestTypeSelected, maintenanceRequestPriority}));
    System.out.println("Saved Maintenance Request");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    Boolean typeSelected =
        RequestControllerUtil.isRadioButtonSelected(
            medEquipRadioButton, elevatorRadioButton, lightsRadioButton, otherRadioButton);

    Boolean prioritySelected =
        RequestControllerUtil.isRadioButtonSelected(
            replacementRadioButton, maintenanceRadioButton, unsureRadioButton);

    Boolean allFields =
        !Objects.equals(roomsHiddenField.getText(), "")
            || !Objects.equals(medEquipHiddenField.getText(), "");

    if (typeSelected && prioritySelected && allFields) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AdditionalNotes.getText(),
          getRequestType(),
          getRequestPriority());
      errorLabel.setText("");
      SceneLoading.loadPopup("views/popups/ReqSubmitted.fxml", "views/SideBar.fxml");
      NewSceneLoading.reloadScene("views/ActiveServiceRequest.fxml");
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
      resetAllFields();
    } else {
      // Print error messages
      if (typeSelected || prioritySelected || allFields || allFields) {
        errorLabel.setText("Missing Required Fields.");
      }
    }
  }

  @FXML
  void backButton() throws IOException {
    Boolean typeSelected =
        RequestControllerUtil.isRadioButtonSelected(
            medEquipRadioButton, lightsRadioButton, elevatorRadioButton, otherRadioButton);

    Boolean prioritySelected =
        RequestControllerUtil.isRadioButtonSelected(
            replacementRadioButton, maintenanceRadioButton, unsureRadioButton);

    Boolean allFields = !Objects.equals(roomsHiddenField.getText(), "");

    if (typeSelected || prioritySelected || allFields) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/requestTypes/FloralRequest.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/MaintenanceRequest.fxml");
      }
    } else {
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
    }
  }

  // Returns the database name of the selected radio button.
  private String getRequestType() {
    if (medEquipRadioButton.isSelected()) return medicalEquipmentText;
    if (elevatorRadioButton.isSelected()) return elevatorText;
    if (lightsRadioButton.isSelected()) return lightsText;
    if (otherRadioButton.isSelected()) return otherText;
    // Should never happen
    return ("");
  }

  // Returns the database name of the selected radio button.
  private String getRequestPriority() {
    if (maintenanceRadioButton.isSelected()) return maintenanceText;
    if (replacementRadioButton.isSelected()) return replacementText;
    if (unsureRadioButton.isSelected()) return unsureText;
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
    RequestControllerUtil.resetTextFields(roomsHiddenField, input_AdditionalNotes, input_OtherText);
    // Report type radio buttons
    RequestControllerUtil.resetRadioButtons(
        maintenanceRadioButton,
        medEquipRadioButton,
        elevatorRadioButton,
        otherRadioButton,
        lightsRadioButton,
        unsureRadioButton,
        replacementRadioButton);
    errorLabel.setText("");
    roomsComboBox.setValue("");
    medEquipComboBox.setValue("");
  }
}
