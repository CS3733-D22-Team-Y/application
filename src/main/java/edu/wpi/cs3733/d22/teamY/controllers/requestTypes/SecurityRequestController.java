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
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SecurityRequestController implements IController {
  // Text input
  @FXML private JFXComboBox<String> nursesComboBox;
  @FXML private TextField nursesHiddenField;
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
  // Side bar
  @FXML private AnchorPane sidebarPane;
  private Scene requestMenu = null;

  @FXML private AnchorPane mainPane;
  @FXML private ImageView bgImage;

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
  void initialize() throws IOException {
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

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param requestTypeSelected The type of request selected.
   * @param requestPriority The priority of the request.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {

    DBManager.save(
        new ServiceRequest(
            RequestTypes.SECURITY,
            assignedNurse,
            roomID,
            additionalNotes,
            10,
            RequestStatus.INCOMPLETE,
            new String[] {requestTypeSelected, requestPriority}));
    System.out.println("Saved SecurityServiceRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    Boolean typeSelected =
        RequestControllerUtil.isRadioButtonSelected(
            disruptionRadioButton, theftRadioButton, unwantedGuestRadioButton, otherRadioButton);

    Boolean prioritySelected =
        RequestControllerUtil.isRadioButtonSelected(
            urgentRadioButton, mostUrgentRadioButton, lowPriorityRadioButton);

    Boolean allFields =
        !Objects.equals(roomsHiddenField.getText(), "")
            && !Objects.equals(nursesHiddenField.getText(), "");

    if (RequestControllerUtil.isRadioButtonSelected(otherRadioButton)
        && Objects.equals(input_OtherText.getText(), "")) {
      errorLabel.setText("Missing Required Fields.");
    } else if (typeSelected && prioritySelected && allFields) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          nursesHiddenField.getText(),
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
      if (typeSelected || prioritySelected || allFields || !allFields) {
        errorLabel.setText("Missing Required Fields.");
      }
    }
  }

  @FXML
  void backButton() throws IOException {
    Boolean typeSelected =
        RequestControllerUtil.isRadioButtonSelected(
            disruptionRadioButton, theftRadioButton, unwantedGuestRadioButton, otherRadioButton);

    Boolean prioritySelected =
        RequestControllerUtil.isRadioButtonSelected(
            urgentRadioButton, mostUrgentRadioButton, lowPriorityRadioButton);

    Boolean allFields =
        !Objects.equals(roomsHiddenField.getText(), "")
            || !Objects.equals(nursesHiddenField.getText(), "");

    if (typeSelected || prioritySelected || allFields) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/SideBar.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/SecurityRequest.fxml");
      } else {
        resetAllFields();
        NewSceneLoading.loadScene("views/RequestMenu.fxml");
      }
    } else {
      resetAllFields();
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
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
        roomsHiddenField, nursesHiddenField, input_AdditionalNotes, input_OtherText);
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
    nursesComboBox.setValue("");
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleFullscreenItemAroundTopLeft(mainPane);
    Scaling.scaleBackground(bgImage);
  }
}
