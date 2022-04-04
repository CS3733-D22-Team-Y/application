package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class mealRequestController {
  // Text input
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextArea input_AdditionalNotes;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;

  @FXML private TextArea input_SpecialInstructions;
  // Radio button main course
  @FXML private JFXRadioButton pizzaRadioButton;
  @FXML private JFXRadioButton burgerRadioButton;
  @FXML private JFXRadioButton saladRadioButton;
  // Radio button sides
  @FXML private JFXRadioButton riceRadioButton;
  @FXML private JFXRadioButton peasRadioButton;
  @FXML private JFXRadioButton appleRadioButton;
  // Dropdown menu
  @FXML private JFXComboBox<String> dietaryRestrictionsSelectionBox;

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

  public mealRequestController() throws IOException {}

  @FXML
  public void initialize() {
    // Required b/c SceneBuilder doesn't provide a ComboBox element editor
    dietaryRestrictionsSelectionBox
        .getItems()
        .addAll(textNone, "Gluten Free", "Vegetarian", "Vegan", textOther);
  }

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
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
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String mainChoice,
      String sideChoice,
      String allergies,
      String specialInstructions) {
    // Code to add the fields to the database goes here.
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
            pizzaRadioButton, burgerRadioButton, saladRadioButton)
        && RequestControllerUtil.isRadioButtonSelected(
            riceRadioButton, peasRadioButton, appleRadioButton)) {
      submitRequest(
          input_RoomID.getText(),
          input_PatientName.getText(),
          input_AssignedNurse.getText(),
          input_RequestStatus.getText(),
          input_AdditionalNotes.getText(),
          getMainChoice(),
          getSideChoice(),
          dietaryRestrictionsSelectionBox.getValue(),
          input_SpecialInstructions.getText());
    } else {
      System.out.println("Please select meal and side options.");
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

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    SceneLoading.loadScene("views/requestMenu.fxml");
    resetAllFields();
  }

  // Checks if the "Special Instructions" box should be enabled.
  // Will only enable if "Other (specify)" is selected.
  @FXML
  void checkSpecialInstructionsEnable() {
    if (dietaryRestrictionsSelectionBox.getValue().equals(textOther)) {
      input_SpecialInstructions.setDisable(false);
    } else {
      input_SpecialInstructions.setDisable(true);
    }
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    // Input text fields
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_PatientName, input_AssignedNurse, input_RequestStatus);

    input_AdditionalNotes.setText("");
    input_SpecialInstructions.setText("");
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
  }
}
