package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class floralRequestController {
  @FXML private JFXRadioButton GetWellSoonBouquetRadioButton;
  @FXML private JFXRadioButton NewBabyRadioButton;
  @FXML private JFXRadioButton BouquetOfTheDayRadioButton;
  @FXML private TextField floralRoomID;
  @FXML private TextField floralPatientName;
  @FXML private TextArea floralAdditionalNotes;

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    App.getInstance().setSceneToRequestMenu(); // Returns to request menu
  }

  @FXML
  void resetAllFields() {
    GetWellSoonBouquetRadioButton.setSelected(false);
    NewBabyRadioButton.setSelected(false);
    BouquetOfTheDayRadioButton.setSelected(false);
    floralRoomID.setText("");
    floralPatientName.setText("");
    floralAdditionalNotes.setText("");
  }
}
