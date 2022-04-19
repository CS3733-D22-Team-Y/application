package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.model.LaundryRequest;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DashboardController {
  // Radio Buttons
  @FXML private MFXRadioButton hazardousRadioButton;
  @FXML private MFXRadioButton scrubsRadioButton;
  @FXML private MFXRadioButton linensRadioButton;
  // Text inputs
  @FXML private TextField input_AssignedNurse;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;
  // Additional  Notes
  @FXML private TextArea input_AdditionalNotes;
  // Error Label
  @FXML private TextArea errorLabel;
  // Side bar
  @FXML private AnchorPane sidebarPane;

  private Scene requestMenu = null;

  private final String hazardousText = "hazardous";
  private final String scrubsText = "scrubs";
  private final String linensText = "linens";

  public DashboardController() {}

  public void initialize() throws IOException {}

  // BACKEND PEOPLE,THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param laundryTypeSelected The type of result selected.
   */
  private void submitRequest(
      String roomID, String assignedNurse, String additionalNotes, String laundryTypeSelected) {
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.LAUNDRY_REQUEST));
    DBManager.save(
        new LaundryRequest(
            nextRequest,
            roomID,
            assignedNurse,
            RequestStatus.INCOMPLETE,
            additionalNotes,
            laundryTypeSelected));
    System.out.println("Saved Laundry Request");
  }

  // Called when the submit button is pressed.

  // Returns the database name of the selected radio button.
  private String getResultType() {
    if (hazardousRadioButton.isSelected()) return hazardousText;
    if (linensRadioButton.isSelected()) return linensText;
    if (scrubsRadioButton.isSelected()) return scrubsText;
    // Will never be used
    return "";
  }
}
