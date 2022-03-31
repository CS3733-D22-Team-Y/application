package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

  private String textOther = "Other (specify)";
  private String textNone = "None";

  private Scene requestMenu = null;

  public mealRequestController() throws IOException {}

  @FXML
  public void initialize() {
    // Required b/c SceneBuilder doesn't provide a ComboBox element editor
    dietaryRestrictionsSelectionBox
        .getItems()
        .addAll("None", "Gluten Free", "Vegetarian", "Vegan", textOther);
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    if (requestMenu == null) {
      requestMenu =
          new Scene(
              FXMLLoader.load(
                  Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));
    }
    resetAllFields();
    App.getInstance().setScene(requestMenu); // Returns to request menu
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
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AssignedNurse.setText("");
    input_RequestStatus.setText("");

    input_AdditionalNotes.setText("");
    input_SpecialInstructions.setText("");
    // Mains
    pizzaRadioButton.setSelected(false);
    burgerRadioButton.setSelected(false);
    saladRadioButton.setSelected(false);
    // Sides
    riceRadioButton.setSelected(false);
    peasRadioButton.setSelected(false);
    appleRadioButton.setSelected(false);
    // Selection box
    dietaryRestrictionsSelectionBox.setValue(textNone);
  }
}
