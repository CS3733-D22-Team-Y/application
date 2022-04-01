package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class labRequestController extends AbsRequestController {
  private Scene requestMenu = null;

  // Input fields
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;
  // Additional Notes
  @FXML private TextArea input_AdditionalNotes;
  // Radio buttons
  @FXML private JFXRadioButton antibodiesRadioButton;
  @FXML private JFXRadioButton allergiesRadioButton;
  @FXML private JFXRadioButton diagnosisRadioButton;

  // Result types text. These should be changed depending on what the names in the database are.
  private final String antibodiesText = "antibodies";
  private final String allergiesText = "allergies";
  private final String diagnosisText = "diagnosis";

  // THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE
  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param patientName The patient name.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param resultTypeSelected The type of result selected.
   * @throws DaoAddException if there is an error adding something to the database (one of the
   *     fields is invalid)
   */
  private void submitRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String resultTypeSelected)
      throws DaoAddException {
    // Code to add the fields to the database goes here.
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a lab result choice has been made.
    if (isRadioButtonSelected(allergiesRadioButton, antibodiesRadioButton, diagnosisRadioButton)) {
      try {
        submitRequest(
            input_RoomID.getText(),
            input_PatientName.getText(),
            input_AssignedNurse.getText(),
            input_RequestStatus.getText(),
            input_AdditionalNotes.getText(),
            getResultType());
      }
      // Thrown if one of the fields in submitRequest is invalid.
      catch (DaoAddException e) {
        System.out.println("One of more fields was invalid.");
      }
    } else {
      System.out.println("Please select a result type.");
    }
  }

  // Retuens the database name of the selected radio button.
  private String getResultType() {
    if (antibodiesRadioButton.isSelected()) return antibodiesText;
    if (allergiesRadioButton.isSelected()) return allergiesText;
    if (diagnosisRadioButton.isSelected()) return diagnosisText;
    // Will never be used
    return "";
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

  // Reset button functionality
  @FXML
  void resetAllFields() {
    resetRadioButtons(antibodiesRadioButton, allergiesRadioButton, diagnosisRadioButton);
    resetTextFields(input_RoomID, input_PatientName, input_AssignedNurse, input_RequestStatus);
    input_AdditionalNotes.setText("");
  }
}
