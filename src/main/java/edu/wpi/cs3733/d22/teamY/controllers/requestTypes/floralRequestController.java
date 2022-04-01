package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;

import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class floralRequestController {
  // Radio Buttons
  @FXML private JFXRadioButton getWellSoonBouquetRadioButton;
  @FXML private JFXRadioButton newBabyRadioButton;
  @FXML private JFXRadioButton bouquetOfTheDayRadioButton;
  // Input fields
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;

  private Scene requestMenu = null;

  //Bouquet types text
  private static String getWellSoonBouquetText = "getWellSoon";
  private static String newBabyBouquetText = "newBaby";
  private static String bouquetOfTheDayText = "bouquetOfDay";

  public floralRequestController() throws IOException {}

  //THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param bouquetTypeSelected The type of bouquet selected.
   * @throws DaoAddException if there is an error adding something to the database (one of the fields is invalid)
   */
  private void submitRequest(String roomID, String patientName, String assignedNurse, String requestStatus, String additionalNotes, String bouquetTypeSelected) throws DaoAddException{
    //Code to add the fields to the database goes here.
  }

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
  void submitButton() {
    if(isRadioButtonSelected()) {
      try {
        submitRequest(input_RoomID.getText(), input_PatientName.getText(), input_AssignedNurse.getText(), input_RequestStatus.getText(), input_AdditionalNotes.getText(), getBouquetType());
      }
      //Thrown if one of the fields in submitRequest is invalid.
      catch (DaoAddException e) {
        System.out.println("One of more fields was invalid.");
      }
    }
    else {
      System.out.println("Please select a bouquet option.");
    }
  }

  //Checks if a radio button is selected.
  private boolean isRadioButtonSelected() {
    return getWellSoonBouquetRadioButton.isSelected() || newBabyRadioButton.isSelected() || bouquetOfTheDayRadioButton.isSelected();
  }

  //Gets the bouquet type bases on the radio button selected.
  private String getBouquetType() {
    if(getWellSoonBouquetRadioButton.isSelected()) return getWellSoonBouquetText;
    if(newBabyRadioButton.isSelected()) return newBabyBouquetText;
    if(bouquetOfTheDayRadioButton.isSelected()) return bouquetOfTheDayText;
    //Should never happen
    return("");
  }

  // Reset button functionality
  @FXML
  void resetAllFields() {
    getWellSoonBouquetRadioButton.setSelected(false);
    newBabyRadioButton.setSelected(false);
    bouquetOfTheDayRadioButton.setSelected(false);
    input_RoomID.setText("");
    input_PatientName.setText("");
    input_AssignedNurse.setText("");
    input_RequestStatus.setText("");
    input_AdditionalNotes.setText("");
  }
}
