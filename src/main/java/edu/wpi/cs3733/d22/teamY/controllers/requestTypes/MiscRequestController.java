package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.NewSceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.SceneUtil;
import edu.wpi.cs3733.d22.teamY.model.MiscRequest;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MiscRequestController {
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private JFXTextArea input_AdditionalNotes;
  @FXML private MFXTextField input_RequestName;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;
  public MiscRequestController() {}

  @FXML
  void initialize() throws IOException {
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
   * @param requestName The type of bouquet selected.
   */
  private void submitRequest(String roomID, String additionalNotes, String requestName) {
    // Get request Num
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.MISC_REQUEST));
    DBManager.save(
        new MiscRequest(
            nextRequest, roomID, "", RequestStatus.INCOMPLETE, additionalNotes, requestName));
    System.out.println("Saved MiscRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    if (roomsHiddenField.getText().equals("")
        || input_AdditionalNotes.getText().equals("")
        || input_AssignedNurse.getText().equals("")
        || input_RequestName.getText().equals("")) {
      errorLabel.setText("Missing Required Fields.");

    } else {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AdditionalNotes.getText(),
          input_RequestName.getText());
      errorLabel.setText("");
      SceneUtil.welcomePage.mainPage();
      SceneLoading.loadPopup(
          "views/popups/ReqSubmitted.fxml", "views/requestTypes/MiscRequest.fxml");
      resetAllFields();
    }
  }

  @FXML
  void backButton() throws IOException {
    if ((!roomsHiddenField.getText().equals("")
        || !input_AdditionalNotes.getText().equals("")
        || !input_AssignedNurse.getText().equals("")
        || !input_RequestName.getText().equals(""))) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/requestTypes/FloralRequest.fxml");
      if (!SceneLoading.stayOnPage) {
        SceneUtil.welcomePage.mainPage();
      }
    } else {
      SceneUtil.welcomePage.mainPage();
    }
  }

  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetTextFields(
        input_RequestName, input_AdditionalNotes, roomsHiddenField, input_AssignedNurse);
    roomsComboBox.setValue("");
  }
}
