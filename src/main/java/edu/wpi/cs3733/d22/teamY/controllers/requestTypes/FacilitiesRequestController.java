package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FacilitiesRequestController {
  // Radio Buttons
  @FXML private MFXRadioButton spillRadioButton;
  @FXML private MFXRadioButton hazRadioButton;
  @FXML private MFXRadioButton bathRadioButton;
  @FXML private MFXRadioButton moveRadioButton;
  @FXML private MFXRadioButton otherRadioButton;
  @FXML private MFXTextField otherTextArea;

  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;
  private Scene requestMenu = null;

  public FacilitiesRequestController() {}

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  public void initialize() throws IOException {

    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  @FXML
  void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param type The type of request selected.
   */
  private void submitRequest(String roomID, String additionalNotes, String type)
      throws IOException {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.FACILITIES,
            "None",
            roomID,
            additionalNotes,
            3,
            RequestStatus.INCOMPLETE,
            new String[] {type}));

    System.out.println("Saved Facilities Request");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
            spillRadioButton, hazRadioButton, bathRadioButton, moveRadioButton, otherRadioButton)
        && !Objects.equals(roomsHiddenField.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AdditionalNotes.getText(),
          getFacilityType());
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
    Boolean typeSelected =
        RequestControllerUtil.isRadioButtonSelected(
            spillRadioButton, hazRadioButton, bathRadioButton, moveRadioButton, otherRadioButton);

    Boolean allFields = !Objects.equals(roomsHiddenField.getText(), "");

    if (typeSelected || allFields) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/SideBar.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/FacilitiesRequest.fxml");
      } else {
        NewSceneLoading.loadScene("views/RequestMenu.fxml");
      }
    } else {
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
    }
  }

  // Returns the database name of the selected radio button.
  private String getFacilityType() {
    if (spillRadioButton.isSelected()) {
      return "Spill";
    }
    if (hazRadioButton.isSelected()) {
      return "Haz";
    }
    if (bathRadioButton.isSelected()) {
      return "Bath";
    }
    if (otherRadioButton.isSelected()) {
      return otherTextArea.getText();
    }
    // Should never happen
    return ("");
  }

  @FXML
  void enableMiscBox() {
    otherTextArea.setAllowEdit(true);
    otherTextArea.setSelectable(true);
  }

  @FXML
  void disableMiscBox() {
    otherTextArea.setAllowEdit(false);
    otherTextArea.setSelectable(false);
    RequestControllerUtil.resetTextFields(otherTextArea);
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        spillRadioButton, hazRadioButton, bathRadioButton, moveRadioButton, otherRadioButton);
    RequestControllerUtil.resetTextFields(roomsHiddenField, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
