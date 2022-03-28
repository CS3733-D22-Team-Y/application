package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class mealRequestController {
  @FXML private TextField inputRoomID;
  @FXML private TextField inputPatientName;
  @FXML private TextArea inputAdditionalNotes;
  @FXML private JFXRadioButton pizzaSelect;
  @FXML private JFXRadioButton burgerSelect;
  @FXML private JFXRadioButton saladSelect;
  @FXML private JFXRadioButton riceSelect;
  @FXML private JFXRadioButton peasSelect;
  @FXML private JFXRadioButton appleSelect;
  @FXML private TextArea specialInstructions;
  @FXML private JFXComboBox<String> dietaryRestrictionsSelectionBox;

  private String textOther = "Other (specify)";
  private String textNone = "None";

  @FXML
  public void initialize() {
    // Required b/c Scenebuilder doesn't provide a ComboBox element editor
    dietaryRestrictionsSelectionBox
        .getItems()
        .addAll("None", "Gluten Free", "Vegetarian", "Vegan", textOther);
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    App.getInstance().setSceneToRequestMenu(); // Returns to request menu
  }

  // Checks if the "Special Instructions" box should be enabled.
  // Will only enable if "Other (specify)" is checked.
  @FXML
  void checkSpecialInstructionsEnable() {
    if (dietaryRestrictionsSelectionBox.getValue().equals(textOther))
      specialInstructions.setDisable(false);
    else specialInstructions.setDisable(true);
  }

  @FXML
  void resetAllFields() {
    // Input text fields
    inputRoomID.setText("");
    inputPatientName.setText("");
    inputAdditionalNotes.setText("");
    specialInstructions.setText("");
    // Mains
    pizzaSelect.setSelected(false);
    burgerSelect.setSelected(false);
    saladSelect.setSelected(false);
    // Sides
    riceSelect.setSelected(false);
    peasSelect.setSelected(false);
    appleSelect.setSelected(false);
    // Selection box
    dietaryRestrictionsSelectionBox.setValue(textNone);
  }
}
