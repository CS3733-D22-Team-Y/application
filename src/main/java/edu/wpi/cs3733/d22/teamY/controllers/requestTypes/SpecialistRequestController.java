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
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SpecialistRequestController {
  // Radio Buttons
  @FXML private MFXRadioButton cardiologistButton;
  @FXML private MFXRadioButton neurologistButton;
  @FXML private MFXRadioButton dermatologistButton;
  // Input fields
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;
  private Scene requestMenu = null;

  // Bouquet types text. These should be changed depending on what the names in the database are.

  private final String cardiologistText = "cardiologist";
  private final String neurologistText = "neurologist";
  private final String dermatologistText = "dermatologist";

  public SpecialistRequestController() {}

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  public void initialize() throws IOException {

    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param specialistTypeSelected The type of bouquet selected.
   */
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String specialistTypeSelected)
      throws IOException {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.SPECIALIST,
            "none",
            roomID,
            additionalNotes,
            9,
            RequestStatus.INCOMPLETE,
            new String[] {specialistTypeSelected}));

    System.out.println("Saved SpecialistRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
            dermatologistButton, cardiologistButton, neurologistButton)
        && !Objects.equals(roomsHiddenField.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          "none",
          input_AdditionalNotes.getText(),
          getSpecialistType());
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
            dermatologistButton, cardiologistButton, neurologistButton)
        || !input_AdditionalNotes.getText().equals("")
        || !roomsHiddenField.getText().equals("")) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/SideBar.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/SpecialistRequest.fxml");
      } else {
        NewSceneLoading.loadScene("views/RequestMenu.fxml");
      }
    } else {
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
    }
  }

  // Returns the database name of the selected radio button.
  private String getSpecialistType() {
    if (neurologistButton.isSelected()) return neurologistText;
    if (cardiologistButton.isSelected()) return cardiologistText;
    if (dermatologistButton.isSelected()) return dermatologistText;
    // Should never happen
    return ("");
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        neurologistButton, cardiologistButton, dermatologistButton);
    RequestControllerUtil.resetTextFields(roomsHiddenField, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
