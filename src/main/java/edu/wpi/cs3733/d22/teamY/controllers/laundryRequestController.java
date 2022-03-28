package edu.wpi.cs3733.d22.teamY.controllers;

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

public class laundryRequestController {
  @FXML private JFXRadioButton hazardousRadioButton;
  @FXML private JFXRadioButton scrubsRadioButton;
  @FXML private JFXRadioButton linensRadioButton;
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextArea input_AdditionalNotes;

  private Scene requestMenu = null;

  public laundryRequestController() throws IOException {}

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    if (requestMenu == null) {
      requestMenu =
          new Scene(
              FXMLLoader.load(
                  Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));
    }
    App.getInstance().setScene(requestMenu); // Returns to request menu
    resetAllFields();
  }

  @FXML
  void resetAllFields() {
    hazardousRadioButton.setSelected(false);
    scrubsRadioButton.setSelected(false);
    linensRadioButton.setSelected(false);
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AdditionalNotes.setText("");
  }
}
