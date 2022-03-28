package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class securityServicesRequestController {
  // Text input
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private JFXRadioButton unwantedGuestRadioButton;
  @FXML private JFXRadioButton disruptionRadioButton;
  @FXML private JFXRadioButton theftRadioButton;

  @FXML private JFXRadioButton mostUrgentRadioButton;
  @FXML private JFXRadioButton urgentRadioButton;
  @FXML private JFXRadioButton lowPriorityRadioButton;

  private Scene requestMenu = null;

  public securityServicesRequestController() throws IOException {}

  @FXML
  void backToRequestMenu() throws IOException {
    if (requestMenu == null) {
      requestMenu =
          new Scene(
              FXMLLoader.load(
                  Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));
    }
    resetAllFields();
    App.getInstance().setScene(requestMenu); // Returns to request menu
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    // Text input
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AdditionalNotes.setText("");
    // Report type radio buttons
    unwantedGuestRadioButton.setSelected(false);
    disruptionRadioButton.setSelected(false);
    theftRadioButton.setSelected(false);
    // Priority radio buttons
    mostUrgentRadioButton.setSelected(false);
    urgentRadioButton.setSelected(false);
    lowPriorityRadioButton.setSelected(false);
  }
}
