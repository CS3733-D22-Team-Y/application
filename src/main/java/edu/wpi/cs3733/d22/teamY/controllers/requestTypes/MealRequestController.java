package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.model.MealRequest;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MealRequestController {
  // Text input
  @FXML private TextField input_PatientID;
  @FXML private TextArea input_AdditionalNotes;
  @FXML private TextField input_AssignedNurse;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;

  // Radio button main course
  @FXML private MFXRadioButton pizzaRadioButton;
  @FXML private MFXRadioButton burgerRadioButton;
  @FXML private MFXRadioButton saladRadioButton;
  // Radio button sides
  @FXML private MFXRadioButton riceRadioButton;
  @FXML private MFXRadioButton peasRadioButton;
  @FXML private MFXRadioButton appleRadioButton;
  // Dropdown menu
  @FXML private JFXComboBox<String> dietaryRestrictionsSelectionBox;
  // Error Label
  @FXML private TextArea errorLabel;

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
  private final String textGF = "glutenFree";
  private final String vegetarianText = "vegetarian";
  private final String veganText = "vegan";

  public MealRequestController() throws IOException {}

  @FXML
  public void initialize() {
    // Required b/c SceneBuilder doesn't provide a ComboBox element editor
    dietaryRestrictionsSelectionBox
        .getItems()
        .addAll(textNone, "Gluten Free", "Vegetarian", "Vegan", textOther);
    dietaryRestrictionsSelectionBox.setValue(textNone);

    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
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
      String requestStatus,
      String additionalNotes,
      String mainChoice,
      String sideChoice,
      String allergies,
      String specialInstructions) {
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.MEAL_REQUEST));
    DBManager.save(
        new MealRequest(
            nextRequest,
            roomID,
            assignedNurse,
            requestStatus,
            additionalNotes,
            mainChoice,
            sideChoice,
            allergies,
            specialInstructions));
    System.out.println("Saved MealRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    errorLabel.setText("Missing Required Fields");
    Boolean mealSelected =
        RequestControllerUtil.isRadioButtonSelected(
            pizzaRadioButton, burgerRadioButton, saladRadioButton);

    Boolean allFields =
        !Objects.equals(roomsComboBox.getValue(), "")
            && !Objects.equals(input_AssignedNurse.getText(), "");

    Boolean sideSelected =
        RequestControllerUtil.isRadioButtonSelected(
            riceRadioButton, peasRadioButton, appleRadioButton);

    // Checks if a bouquet choice has been made
    if (mealSelected && sideSelected && allFields) {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AssignedNurse.getText(),
          "temp",
          input_AdditionalNotes.getText(),
          getMainChoice(),
          getSideChoice(),
          dietaryRestrictionsSelectionBox.getValue(),
          input_AdditionalNotes.getText());
      errorLabel.setText("");
    } else {
      if (allFields || sideSelected || mealSelected) {
        errorLabel.setText("Missing Required Fields");
      }
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
        input_AssignedNurse, input_AdditionalNotes, roomsHiddenField, input_PatientID);
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
  }
}
