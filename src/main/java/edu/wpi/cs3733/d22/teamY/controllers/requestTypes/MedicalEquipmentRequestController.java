package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.controllers.SceneLoading;
import edu.wpi.cs3733.d22.teamY.controllers.SceneUtil;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MedicalEquipmentRequestController {
  // Text Inputs
  @FXML private TextField input_AssignedNurse;
  @FXML private JFXComboBox<String> roomsComboBox;
  @FXML private TextField roomsHiddenField;

  @FXML private JFXTextArea input_AdditionalNotes;
  // Radio Buttons
  @FXML private MFXRadioButton bedRadioButton;
  @FXML private MFXRadioButton xrayRadioButton;
  @FXML private MFXRadioButton infusionPumpRadioButton;
  @FXML private MFXRadioButton reclinerRadioButton;
  @FXML private TextArea errorLabel;

  // Equipment types text. These should be changed depending on what the names in the database are.
  private final String bedText = "BED";
  private final String xrayText = "XRAY";
  private final String infusionPumpText = "PUMP";
  private final String reclinerText = "RECLINER";

  private Scene requestMenu = null;

  public MedicalEquipmentRequestController() throws IOException {}

  @FXML
  private void initialize() {

    updateAvailableEquip();

    System.out.println(RequestControllerUtil.allRoomsComboBox.getItems().size());
    roomsComboBox.setItems(RequestControllerUtil.allRoomsComboBox.getItems());
  }

  @FXML
  private void setRoomText() {
    roomsHiddenField.setText(roomsComboBox.getValue());
  }

  private void updateAvailableEquip() {

    bedRadioButton.setText(
        "Beds: \n"
            + DBUtils.getAvailableEquipment("BED").getKey().toString()
            + "/"
            + DBUtils.getAvailableEquipment("BED").getValue().toString()
            + " available");
    xrayRadioButton.setText(
        "Portable X-ray: \n"
            + DBUtils.getAvailableEquipment("XRAY").getKey().toString()
            + "/"
            + DBUtils.getAvailableEquipment("XRAY").getValue().toString()
            + " available");
    infusionPumpRadioButton.setText(
        "Pumps: \n"
            + DBUtils.getAvailableEquipment("PUMP").getKey().toString()
            + "/"
            + DBUtils.getAvailableEquipment("PUMP").getValue().toString()
            + " available");
    reclinerRadioButton.setText(
        "Recliners: \n"
            + DBUtils.getAvailableEquipment("RECLINER").getKey().toString()
            + "/"
            + DBUtils.getAvailableEquipment("RECLINER").getValue().toString()
            + " available");
  }

  // BACKEND PEOPLE, THIS FUNCTION PASSES THE PARAMETERS TO THE DATABASE

  /**
   * Submits a service request.
   *
   * @param roomID The room ID.
   * @param additionalNotes Any additional notes.
   * @param equipmentTypeSelected The type of bouquet selected.
   */
  private void submitRequest(String roomID, String additionalNotes, String equipmentTypeSelected)
      throws IOException {
    String nextRequest = String.valueOf(DBUtils.getNextRequestNum(EntryType.MED_EQUIP_REQUEST));
    DBManager.save(
        new MedEquipReq(
            nextRequest,
            roomID,
            "",
            RequestStatus.INCOMPLETE,
            additionalNotes,
            equipmentTypeSelected));
    DBUtils.updateCleanStatus(equipmentTypeSelected, roomID);
    System.out.println("Saved MedEquipRequest");
    updateAvailableEquip();
    SceneUtil.welcomePage.mainPage();
  }

  // Called when the submit button is pressed.
  @FXML
  void submitButton() throws IOException {
    // Checks if a bouquet choice has been made

    if (roomsHiddenField.getText().equals("") || input_AssignedNurse.getText().equals("")) {
      errorLabel.setText("Missing Required Fields.");

    } else if (!RequestControllerUtil.isRadioButtonSelected(
        bedRadioButton, xrayRadioButton, infusionPumpRadioButton, reclinerRadioButton)) {
      errorLabel.setText("Please select an equipment option.");

    } else if (RequestControllerUtil.isRadioButtonSelected(reclinerRadioButton)
        && DBUtils.getAvailableEquipment("RECLINER").getKey() == 0) {

      errorLabel.setText("Equipment not available.");

    } else if (RequestControllerUtil.isRadioButtonSelected(infusionPumpRadioButton)
        && DBUtils.getAvailableEquipment("PUMP").getKey() == 0) {

      errorLabel.setText("Equipment not available.");

    } else if (RequestControllerUtil.isRadioButtonSelected(xrayRadioButton)
        && DBUtils.getAvailableEquipment("XRAY").getKey() == 0) {

      errorLabel.setText("Equipment not available.");

    } else if (RequestControllerUtil.isRadioButtonSelected(bedRadioButton)
        && DBUtils.getAvailableEquipment("BED").getKey() == 0) {

      errorLabel.setText("Equipment not available.");

    } else {
      submitRequest(
          DBUtils.convertNameToID(roomsComboBox.getValue()),
          input_AdditionalNotes.getText(),
          getEquipmentType());
      errorLabel.setText("");

      SceneLoading.loadPopup(
          "views/popups/ReqSubmitted.fxml", "views/requestTypes/MedicalEquipmentRequest.fxml");

      resetAllFields();
    }
  }

  @FXML
  void backButton() throws IOException {
    if ((!roomsHiddenField.getText().equals("") || !input_AssignedNurse.getText().equals(""))
        || RequestControllerUtil.isRadioButtonSelected(
            bedRadioButton, xrayRadioButton, infusionPumpRadioButton, reclinerRadioButton)) {
      SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/requestTypes/FloralRequest.fxml");
      if (!SceneLoading.stayOnPage) {
        SceneUtil.welcomePage.mainPage();
      }
    } else {
      SceneUtil.welcomePage.mainPage();
    }
  }

  // Called when the submit button is pressed.

  // Returns the database name of the selected radio button.
  private String getEquipmentType() {
    if (reclinerRadioButton.isSelected()) return reclinerText;
    if (bedRadioButton.isSelected()) return bedText;
    if (infusionPumpRadioButton.isSelected()) return infusionPumpText;
    if (xrayRadioButton.isSelected()) return xrayText;
    // Should never happen
    return ("");
  }

  //  Reset button functionality
  @FXML
  void resetAllFields() {
    RequestControllerUtil.resetTextFields(
        roomsHiddenField, input_AssignedNurse, input_AdditionalNotes);
    // Radio buttons
    RequestControllerUtil.resetRadioButtons(
        bedRadioButton, xrayRadioButton, infusionPumpRadioButton, reclinerRadioButton);
    errorLabel.setText("");
    roomsComboBox.setValue("");
  }
}
