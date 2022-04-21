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
import edu.wpi.cs3733.d22.teamY.utilTemp.Languages;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TranslatorRequestController implements IController {
  // Radio Buttons
  @FXML private MFXRadioButton spanishRadioButton;
  @FXML private MFXRadioButton chineseRadioButton;
  @FXML private MFXRadioButton germanRadioButton;
  @FXML private MFXRadioButton arabicRadioButton;
  @FXML private MFXRadioButton otherRadioButton;

  @FXML private MFXTextField input_OtherLanguage;
  // Input fields
  @FXML private JFXComboBox<String> nursesComboBox;
  @FXML private TextField nursesHiddenField;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;

  @FXML private AnchorPane mainPane;

  // Language types text. These should be changed depending on what the names in the database are.
  private final String spanishText = "spanish";
  private final String chineseText = "chinese";
  private final String germanText = "german";
  private final String arabicText = "arabic";
  private final String otherText = "other";

  public Languages langs = new Languages();

  public TranslatorRequestController() {}

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
   * @param languageTypeSelected The type of language selected.
   */
  private void submitRequest(
      String roomID, String assignedNuse, String additionalNotes, String languageTypeSelected) {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.TRANSLATOR,
            assignedNuse,
            roomID,
            additionalNotes,
            2,
            RequestStatus.INCOMPLETE,
            new String[] {languageTypeSelected}));

    System.out.println("Saved TranslatorRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a language choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(otherRadioButton)
        && Objects.equals(input_OtherLanguage.getText(), "")) {
      errorLabel.setText("Missing Required Fields.");
    } else if (RequestControllerUtil.isRadioButtonSelected(otherRadioButton)
        && !langs.isLanguage(input_OtherLanguage.getText())) {
      errorLabel.setText("Language not valid.");
    } else if (RequestControllerUtil.isRadioButtonSelected(
            spanishRadioButton,
            chineseRadioButton,
            germanRadioButton,
            arabicRadioButton,
            otherRadioButton)
        && !Objects.equals(roomsHiddenField.getText(), "")
        && !Objects.equals(nursesHiddenField.getText(), "")
        && !(RequestControllerUtil.isRadioButtonSelected(otherRadioButton)
            && Objects.equals(input_OtherLanguage.getText(), ""))) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          nursesHiddenField.getText(),
          input_AdditionalNotes.getText(),
          getLanguageType());
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
  private String getLanguageType() {
    if (spanishRadioButton.isSelected()) return spanishText;
    if (chineseRadioButton.isSelected()) return chineseText;
    if (germanRadioButton.isSelected()) return germanText;
    if (arabicRadioButton.isSelected()) return arabicText;
    if (otherRadioButton.isSelected()) return input_OtherLanguage.getText();
    // Should never happen
    return ("");
  }

  @FXML
  void enableMiscBox() {
    input_OtherLanguage.setAllowEdit(true);
    input_OtherLanguage.setSelectable(true);
  }

  @FXML
  void disableMiscBox() {
    input_OtherLanguage.setAllowEdit(false);
    input_OtherLanguage.setSelectable(false);
    RequestControllerUtil.resetTextFields(input_OtherLanguage);
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    if (otherRadioButton.isSelected()) {
      RequestControllerUtil.resetTextFields(input_OtherLanguage);
    }
    RequestControllerUtil.resetRadioButtons(
        spanishRadioButton,
        chineseRadioButton,
        germanRadioButton,
        arabicRadioButton,
        otherRadioButton);
    RequestControllerUtil.resetTextFields(
        roomsHiddenField, nursesHiddenField, input_AdditionalNotes);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleFullscreenItemAroundTopLeft(mainPane);
  }
}
