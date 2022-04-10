package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBManager;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class PersonalSettingsPageController {

  @FXML MFXTextField legalName;
  @FXML MFXTextField prefName;
  @FXML MFXTextField title;
  @FXML MFXTextField email;
  @FXML MFXTextField dob;
  @FXML MFXTextField pronouns;
  @FXML MFXTextField phone;

  public void initialize() {
    legalName.setText(PersonalSettings.currentEmployee.getName());
    prefName.setText(PersonalSettings.currentEmployee.getPrefName());
    title.setText(PersonalSettings.currentEmployee.getRole());
    email.setText(PersonalSettings.currentEmployee.getEmail());
    dob.setText(PersonalSettings.currentEmployee.getDOB());
    pronouns.setText(PersonalSettings.currentEmployee.getPronouns());
    phone.setText(PersonalSettings.currentEmployee.getPhone());
  }

  @FXML
  public void updateLegalName() {
    PersonalSettings.currentEmployee.setName(legalName.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }

  @FXML
  public void updatePrefName() {
    PersonalSettings.currentEmployee.setPrefName(prefName.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }

  @FXML
  public void updateTitle() {
    PersonalSettings.currentEmployee.setRole(title.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }

  @FXML
  public void updateEmail() {
    PersonalSettings.currentEmployee.setEmail(email.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }

  @FXML
  public void updateDob() {
    PersonalSettings.currentEmployee.setDOB(dob.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }

  @FXML
  public void updatePronouns() {
    PersonalSettings.currentEmployee.setPronouns(pronouns.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }

  @FXML
  public void updatePhone() {
    PersonalSettings.currentEmployee.setPhone(phone.getText());
    DBManager.update(PersonalSettings.currentEmployee);
  }
}
