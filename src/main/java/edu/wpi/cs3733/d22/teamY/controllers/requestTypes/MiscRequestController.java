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
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MiscRequestController implements IController {
  @FXML private JFXComboBox<String> nursesComboBox;
  @FXML private TextField nursesHiddenField;
  @FXML private JFXTextArea input_AdditionalNotes;
  @FXML private MFXTextField input_RequestName;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;

  @FXML private AnchorPane mainPane;
  @FXML private ImageView bgImage;

  public MiscRequestController() {}

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

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param requestName The type of bouquet selected.
   */
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String requestName) {
    // Get request Num
    DBManager.save(
        new ServiceRequest(
            RequestTypes.MEAL,
            assignedNurse,
            roomID,
            additionalNotes,
            4,
            RequestStatus.INCOMPLETE,
            new String[] {requestName}));
    System.out.println("Saved MiscRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    if (roomsHiddenField.getText().equals("")
        || input_AdditionalNotes.getText().equals("")
        || nursesHiddenField.getText().equals("")
        || input_RequestName.getText().equals("")) {
      errorLabel.setText("Missing Required Fields.");

    } else {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          nursesHiddenField.getText(),
          input_AdditionalNotes.getText(),
          input_RequestName.getText());
      errorLabel.setText("");
      SceneLoading.loadPopup("views/popups/ReqSubmitted.fxml", "views/SideBar.fxml");
      NewSceneLoading.reloadScene("views/ActiveServiceRequest.fxml");
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
      resetAllFields();
    }
  }

  @FXML
  void backButton() throws IOException {
    if ((!roomsHiddenField.getText().equals("")
        || !input_AdditionalNotes.getText().equals("")
        || !nursesHiddenField.getText().equals("")
        || !input_RequestName.getText().equals(""))) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/SideBar.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/MiscRequest.fxml");
      } else {
        resetAllFields();
        NewSceneLoading.loadScene("views/RequestMenu.fxml");
      }
    } else {
      resetAllFields();
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
    }
  }

  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetTextFields(
        input_RequestName, input_AdditionalNotes, roomsHiddenField, nursesHiddenField);
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
