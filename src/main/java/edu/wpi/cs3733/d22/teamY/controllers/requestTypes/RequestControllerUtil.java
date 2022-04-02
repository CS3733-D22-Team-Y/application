package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import javafx.scene.control.TextField;

public class RequestControllerUtil {
  // Checks if any of the given radio buttons are selected.
  protected static boolean isRadioButtonSelected(JFXRadioButton... buttons) {
    for (JFXRadioButton currButton : buttons) {
      if (currButton.isSelected()) return true;
    }
    return false;
  }

  // Resets all the given radio buttons so they are  no longer selected.
  protected static void resetRadioButtons(JFXRadioButton... buttons) {
    for (JFXRadioButton currButton : buttons) {
      currButton.setSelected(false);
    }
  }

  // Resets all the given text fields to nothing.
  protected static void resetTextFields(TextField... text) {
    for (TextField currField : text) {
      currField.setText("");
    }
  }
}
