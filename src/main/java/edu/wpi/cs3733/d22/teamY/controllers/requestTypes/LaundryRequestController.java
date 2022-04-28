package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
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
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LaundryRequestController implements IController {
  // Radio Buttons
  @FXML private MFXRadioButton hazardousRadioButton;
  @FXML private MFXRadioButton scrubsRadioButton;
  @FXML private MFXRadioButton linensRadioButton;
  // Text inputs
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  @FXML private JFXComboBox<String> nursesComboBox;
  @FXML private TextField nursesHiddenField;
  // Additional  Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;

  @FXML private AnchorPane mainPane;
  @FXML ImageView bgImage;

  private Scene requestMenu = null;

  private final String hazardousText = "hazardous";
  private final String scrubsText = "scrubs";
  private final String linensText = "linens";

  public LaundryRequestController() {}

  public void initialize() throws IOException {
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
   * @param laundryTypeSelected The type of result selected.
   */
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String laundryTypeSelected) {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.LAUNDRY,
            assignedNurse,
            roomID,
            additionalNotes,
            3,
            RequestStatus.INCOMPLETE,
            new String[] {laundryTypeSelected}));
    System.out.println("Saved Laundry Request");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a lab result choice has been made.
    if (RequestControllerUtil.isRadioButtonSelected(
            hazardousRadioButton, linensRadioButton, scrubsRadioButton)
        && !Objects.equals(roomsHiddenField.getText(), "")
        && !Objects.equals(nursesHiddenField.getText(), "")) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          nursesHiddenField.getText(),
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
            hazardousRadioButton, linensRadioButton, scrubsRadioButton)
        || !Objects.equals(roomsHiddenField.getText(), "")
        || !Objects.equals(nursesHiddenField.getText(), "")) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/SideBar.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/LaundryRequest.fxml");
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
  private String getResultType() {
    if (hazardousRadioButton.isSelected()) return hazardousText;
    if (linensRadioButton.isSelected()) return linensText;
    if (scrubsRadioButton.isSelected()) return scrubsText;
    // Will never be used
    return "";
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    SceneLoading.loadScene("views/RequestMenu.fxml");
    resetAllFields();
  }

  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetRadioButtons(
        scrubsRadioButton, linensRadioButton, hazardousRadioButton);
    RequestControllerUtil.resetTextFields(
        roomsHiddenField, nursesHiddenField, input_AdditionalNotes);
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
    // bgImage.scaleYProperty().bind(NewSceneLoading.activeWindow.heightProperty());
  }
}
