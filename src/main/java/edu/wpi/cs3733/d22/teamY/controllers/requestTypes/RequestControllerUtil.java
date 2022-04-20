package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.Location;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

public class RequestControllerUtil {
  public static JFXComboBox<String> allRoomsComboBox = new JFXComboBox<>();
  public static JFXComboBox<String> allmedEquipComboBox = new JFXComboBox<>();

  public static void initialize() {
    LinkedList<String> items = new LinkedList<>();
    LinkedList<String> medEquip = new LinkedList<>();
    for (int i = 0; i < DBManager.getAll(Location.class).size(); i++) {
      items.add(((Location) DBManager.getAll(Location.class).get(i)).getShortName());
    }
    allRoomsComboBox.setItems(FXCollections.observableList(items));
    medEquip.add("Bed");
    medEquip.add("Pump");
    medEquip.add("X-Ray");
    medEquip.add("Recliner");
    allmedEquipComboBox.setItems((FXCollections.observableList(medEquip)));
  }

  // Checks if any of the given radio buttons are selected.
  protected static boolean isRadioButtonSelected(JFXRadioButton... buttons) {
    for (JFXRadioButton currButton : buttons) {
      if (currButton.isSelected()) return true;
    }
    return false;
  }

  // Checks if any of the given radio buttons are selected.
  protected static boolean isRadioButtonSelected(MFXRadioButton... buttons) {
    for (MFXRadioButton currButton : buttons) {
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

  // Resets all the given radio buttons so they are  no longer selected.
  protected static void resetRadioButtons(MFXRadioButton... buttons) {
    for (MFXRadioButton currButton : buttons) {
      currButton.setSelected(false);
    }
  }

  // Resets all the given text fields to nothing.
  protected static void resetTextFields(TextInputControl... text) {
    for (TextInputControl currField : text) {
      currField.setText("");
    }
  }

  // Resets all labels to nothing.
  protected static void resetLabels(Labeled... labels) {
    for (Labeled currLabel : labels) {
      currLabel.setText("");
    }
  }
}
