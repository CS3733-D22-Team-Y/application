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
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MealRequestController implements IController {
  // Text input
  @FXML private TextArea input_AdditionalNotes;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  @FXML private JFXComboBox<String> nursesComboBox;
  @FXML private TextField nursesHiddenField;
  @FXML private JFXComboBox<String> dietaryRestrictionsSelectionBox;
  @FXML private TextField restrictionsHiddenField;

  // Radio button main course
  @FXML private MFXRadioButton pizzaRadioButton;
  @FXML private MFXRadioButton burgerRadioButton;
  @FXML private MFXRadioButton saladRadioButton;
  // Radio button sides
  @FXML private MFXRadioButton riceRadioButton;
  @FXML private MFXRadioButton peasRadioButton;
  @FXML private MFXRadioButton appleRadioButton;
  // Error Label
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;

  @FXML private AnchorPane mainPane;
  @FXML private ImageView bgImage;
  // Combobox text items
  private final String textOther = "Other (specify)";
  private final String textNone = "None";

  // Main choices text
  private final String pizzaText = "pizza";
  private final String burgerText = "burger";
  private final String saladText = "salad";
  // Side choices text
  private final String riceText = "rice";
  private final String peasText = "peas";
  private final String appleText = "apple";
  // Combobox text

  public MealRequestController() throws IOException {}

  @FXML
  public void initialize() throws IOException {
    resetAllFields();

    // Required b/c SceneBuilder doesn't provide a ComboBox element editor
    dietaryRestrictionsSelectionBox
        .getItems()
        .addAll(textNone, "Gluten Free", "Vegetarian", "Vegan", textOther);
    dietaryRestrictionsSelectionBox.setValue("");

    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
    nursesComboBox.setItems(RequestControllerUtil.allNursesComboBox.getItems());
    restrictionsHiddenField.setText("None");
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  @FXML
  private void setRestrictionText() {
    restrictionsHiddenField.setText(dietaryRestrictionsSelectionBox.getValue());
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
   * @param mainChoice The choice for the main meal.
   * @param sideChoice The choice for the side.
   * @param allergies Any allergies to be considered.
   * @param specialInstructions Any special instructions to be considered (only if "other" is
   *     checked)
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String additionalNotes,
      String mainChoice,
      String sideChoice,
      String allergies,
      String specialInstructions) {
    DBManager.save(
        new ServiceRequest(
            RequestTypes.MEAL,
            assignedNurse,
            roomID,
            additionalNotes,
            5,
            RequestStatus.INCOMPLETE,
            new String[] {mainChoice, sideChoice, allergies, specialInstructions}));
    System.out.println("Saved MealRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    errorLabel.setText("Missing Required Fields.");
    Boolean mealSelected =
        RequestControllerUtil.isRadioButtonSelected(
            pizzaRadioButton, burgerRadioButton, saladRadioButton);

    Boolean allFields =
        !Objects.equals(roomsHiddenField.getText(), "")
            && !Objects.equals(nursesHiddenField.getText(), "");

    Boolean sideSelected =
        RequestControllerUtil.isRadioButtonSelected(
            riceRadioButton, peasRadioButton, appleRadioButton);

    // Checks if a bouquet choice has been made
    if (mealSelected && sideSelected && allFields) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          nursesHiddenField.getText(),
          input_AdditionalNotes.getText(),
          getMainChoice(),
          getSideChoice(),
          dietaryRestrictionsSelectionBox.getValue(),
          input_AdditionalNotes.getText());
      errorLabel.setText("");
      SceneLoading.loadPopup("views/popups/ReqSubmitted.fxml", "views/SideBar.fxml");
      NewSceneLoading.reloadScene("views/ActiveServiceRequest.fxml");
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
      resetAllFields();
    } else {
      if (allFields || sideSelected || mealSelected) {
        errorLabel.setText("Missing Required Fields.");
      }
    }
  }

  @FXML
  void backButton() throws IOException {
    Boolean mealSelected =
        RequestControllerUtil.isRadioButtonSelected(
            pizzaRadioButton, burgerRadioButton, saladRadioButton);

    Boolean allFields =
        !Objects.equals(roomsHiddenField.getText(), "")
            || !Objects.equals(nursesHiddenField.getText(), "");

    Boolean sideSelected =
        RequestControllerUtil.isRadioButtonSelected(
            riceRadioButton, peasRadioButton, appleRadioButton);

    // Checks if a bouquet choice has been made
    if (mealSelected || sideSelected || allFields) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/SideBar.fxml");
      if (SceneLoading.stayOnPage) {
        NewSceneLoading.loadScene("views/requestTypes/MealRequest.fxml");
      } else {
        resetAllFields();
        NewSceneLoading.loadScene("views/RequestMenu.fxml");
      }
    } else {
      resetAllFields();
      NewSceneLoading.loadScene("views/RequestMenu.fxml");
    }
  }

  private String getMainChoice() {
    if (pizzaRadioButton.isSelected()) return pizzaText;
    if (burgerRadioButton.isSelected()) return burgerText;
    if (saladRadioButton.isSelected()) return saladText;
    // Should never happen
    return ("");
  }

  private String getSideChoice() {
    if (riceRadioButton.isSelected()) return riceText;
    if (peasRadioButton.isSelected()) return peasText;
    if (appleRadioButton.isSelected()) return appleText;
    // Should never happen
    return ("");
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    // Input text fields
    RequestControllerUtil.resetTextFields(
        nursesHiddenField, input_AdditionalNotes, roomsHiddenField);
    // Mains
    RequestControllerUtil.resetRadioButtons(
        pizzaRadioButton,
        burgerRadioButton,
        saladRadioButton,
        riceRadioButton,
        peasRadioButton,
        appleRadioButton);
    // Selection box
    dietaryRestrictionsSelectionBox.setValue(textNone);
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
