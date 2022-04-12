package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.model.MiscRequest;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MiscRequestController {
  @FXML private MFXTextField input_AssignedNurse;
  @FXML private JFXTextArea input_AdditionalNotes;
  @FXML private MFXTextField input_RequestName;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;

  public MiscRequestController() {}

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
   * @param requestName The type of bouquet selected.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestName) {
    // Get request Num
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.MISC_REQUEST));
    DBManager.save(
        new MiscRequest(
            nextRequest, roomID, assignedNurse, requestStatus, additionalNotes, requestName));
    System.out.println("Saved MiscRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    submitRequest(
        DBUtils.convertNameToID(roomsComboBox.getValue()),
        input_AssignedNurse.getText(),
        "Patient ID",
        input_AdditionalNotes.getText(),
        input_RequestName.getText());
  }

  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetTextFields(
        input_RequestName, input_AdditionalNotes, roomsHiddenField, input_AssignedNurse);
    roomsComboBox.setValue("");
  }
}
