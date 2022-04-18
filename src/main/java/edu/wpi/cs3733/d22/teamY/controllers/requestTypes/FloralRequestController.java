package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.NewSceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.FloralRequest;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FloralRequestController {
  // Radio Buttons
  @FXML private MFXRadioButton getWellSoonBouquetRadioButton;
  @FXML private MFXRadioButton newBabyRadioButton;
  @FXML private MFXRadioButton bouquetOfTheDayRadioButton;
  // Input fields
  @FXML private MFXTextField input_AssignedNurse;
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
  private final String getWellSoonBouquetText = "getWellSoon";
  private final String newBabyBouquetText = "newBaby";
  private final String bouquetOfTheDayText = "bouquetOfDay";

  public FloralRequestController() {}

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
   * @param bouquetTypeSelected The type of bouquet selected.
   */
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String bouquetTypeSelected)
      throws IOException {
    // Get request Num
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.FLORAL_REQUEST));
    DBManager.save(
        new FloralRequest(
            nextRequest,
            roomID,
            assignedNurse,
            RequestStatus.INCOMPLETE,
            additionalNotes,
            bouquetTypeSelected));
    System.out.println("Saved FloralRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
            getWellSoonBouquetRadioButton, newBabyRadioButton, bouquetOfTheDayRadioButton)
        && !Objects.equals(roomsHiddenField.getText(), "")
        && !Objects.equals(input_AssignedNurse.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AssignedNurse.getText(),
          input_AdditionalNotes.getText(),
          getBouquetType());
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
            getWellSoonBouquetRadioButton, newBabyRadioButton, bouquetOfTheDayRadioButton)
        || !input_AssignedNurse.getText().equals("")
        || !input_AdditionalNotes.getText().equals("")
        || !Objects.equals(roomsHiddenField.getText(), "")) {
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/FloralRequest.fxml");
      }
    } else {
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
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
        roomsHiddenField, input_AssignedNurse, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
