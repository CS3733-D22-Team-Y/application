package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXRadioButton;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MedicalEquipmentRequestController {
  // Text Inputs
  @FXML private TextField input_RoomID;
  @FXML private TextField input_PatientName;
  @FXML private TextField input_AssignedNurse;
  @FXML private TextField input_RequestStatus;

  @FXML private TextArea input_AdditionalNotes;
  // Radio Buttons
  @FXML private JFXRadioButton bedRadioButton;
  @FXML private JFXRadioButton xrayRadioButton;
  @FXML private JFXRadioButton infusionPumpRadioButton;
  @FXML private JFXRadioButton reclinerRadioButton;
  @FXML private Label errorLabel;

  // Equipment types text. These should be changed depending on what the names in the database are.
  private final String bedText = "BED";
  private final String xrayText = "XRAY";
  private final String infusionPumpText = "PUMP";
  private final String reclinerText = "RECLINER";

  private Scene requestMenu = null;

  public MedicalEquipmentRequestController() throws IOException {}

  @FXML
  private void initialize() {
    bedRadioButton.setText(
        "Beds: "
            + DBUtils.getAvailableEquipment("BED").getKey().toString()
            + " available out of "
            + DBUtils.getAvailableEquipment("BED").getValue().toString());
    xrayRadioButton.setText(
        "X-ray: "
            + DBUtils.getAvailableEquipment("XRAY").getKey().toString()
            + " available out of "
            + DBUtils.getAvailableEquipment("XRAY").getValue().toString());
    infusionPumpRadioButton.setText(
        "Pumps: "
            + DBUtils.getAvailableEquipment("PUMP").getKey().toString()
            + " available out of "
            + DBUtils.getAvailableEquipment("PUMP").getValue().toString());
    reclinerRadioButton.setText(
        "Recliners: "
            + DBUtils.getAvailableEquipment("RECLINER").getKey().toString()
            + " available out of "
            + DBUtils.getAvailableEquipment("RECLINER").getValue().toString());
  }

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param assignedNurse The assigned nurse.
   * @param requestStatus The request status.
   * @param additionalNotes Any additional notes.
   * @param equipmentTypeSelected The type of bouquet selected.
   */
  private void submitRequest(
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String equipmentTypeSelected) {

    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.MED_EQUIP_REQ));
    DBManager.save(
        new MedEquipReq(
            nextRequest,
            roomID,
            assignedNurse,
            requestStatus,
            additionalNotes,
            equipmentTypeSelected));
    DBUtils.updateCleanStatus(equipmentTypeSelected, roomID);
    System.out.println("Saved MedEquipRequest");
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() {
    // Checks if a bouquet choice has been made
    if (RequestControllerUtil.isRadioButtonSelected(
        reclinerRadioButton, infusionPumpRadioButton, xrayRadioButton, bedRadioButton)) {
      submitRequest(
          input_RoomID.getText(),
          input_AssignedNurse.getText(),
          input_RequestStatus.getText(),
          input_AdditionalNotes.getText(),
          getEquipmentType());
      RequestControllerUtil.resetLabels(errorLabel);
    } else {
      errorLabel.setText("Please select an equipment option.");
    }
  }

  // Returns the database name of the selected radio button.
  private String getEquipmentType() {
    if (reclinerRadioButton.isSelected()) return reclinerText;
    if (bedRadioButton.isSelected()) return bedText;
    if (infusionPumpRadioButton.isSelected()) return infusionPumpText;
    if (xrayRadioButton.isSelected()) return xrayText;
    // Should never happen
    return ("");
  }

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    SceneLoading.loadScene("views/RequestMenu.fxml");
    resetAllFields();
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetTextFields(
        input_RoomID, input_AssignedNurse, input_RequestStatus, input_AdditionalNotes);
    // Radio buttons
    RequestControllerUtil.resetRadioButtons(
        bedRadioButton, xrayRadioButton, infusionPumpRadioButton, reclinerRadioButton);
    RequestControllerUtil.resetLabels(errorLabel);
  }
}
