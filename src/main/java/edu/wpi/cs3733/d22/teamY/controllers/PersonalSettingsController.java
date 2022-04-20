package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PersonalSettingsController implements IController {

  @FXML MFXTextField legalName;
  @FXML MFXTextField prefName;
  @FXML MFXTextField title;
  @FXML MFXTextField email;
  @FXML MFXTextField dob;
  @FXML MFXTextField pronouns;
  @FXML MFXTextField phone;

  @FXML MFXButton edit, apply, cancel, lightModeButton, darkModeButton;

  @FXML AnchorPane sidebarPane;

  @FXML AnchorPane mainPane;

  AnchorPane sidebar = null;
  public static Employee currentEmployee =
      new Employee(
          "-1", "Guest", "Guest", "Guest", "Guest", "Guest", "Guest", 0, "none", "Guest", " ", " ",
          " ", " ");

  @FXML
  void initialize() throws IOException {
    legalName.setText(PersonalSettings.currentEmployee.getName());
    prefName.setText(PersonalSettings.currentEmployee.getPrefName());
    title.setText(PersonalSettings.currentEmployee.getRole());
    email.setText(PersonalSettings.currentEmployee.getEmail());
    dob.setText(PersonalSettings.currentEmployee.getDOB());
    pronouns.setText(PersonalSettings.currentEmployee.getPronouns());
    phone.setText(PersonalSettings.currentEmployee.getPhone());

    setEdit(false);
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  private void setEdit(boolean b) {
    legalName.setEditable(b);
    prefName.setEditable(b);
    title.setEditable(b);
    email.setEditable(b);
    dob.setEditable(b);
    pronouns.setEditable(b);
    phone.setEditable(b);

    legalName.setDisable(!b);
    prefName.setDisable(!b);
    title.setDisable(!b);
    email.setDisable(!b);
    dob.setDisable(!b);
    pronouns.setDisable(!b);
    phone.setDisable(!b);
  }

  @FXML
  void editMode() {
    edit.setVisible(false);
    cancel.setVisible(true);
    apply.setVisible(true);

    setEdit(true);
  }

  @FXML
  public void updateFields() {
    edit.setVisible(true);
    cancel.setVisible(false);
    apply.setVisible(false);

    updateLegalName();
    updatePrefName();
    updateTitle();
    updateEmail();
    updateDob();
    updatePronouns();
    updatePhone();

    setEdit(false);
  }

  @FXML
  public void cancelEdits() throws IOException {
    edit.setVisible(true);
    cancel.setVisible(false);
    apply.setVisible(false);

    initialize();
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

  @FXML
  void darkMode() {
    PersonalSettings.currentEmployee.setTheme("DARK");
    DBManager.update(PersonalSettings.currentEmployee);
    darkModeButton
        .getScene()
        .getStylesheets()
        .add(
            Objects.requireNonNull(App.class.getResource("views/css/ThemeDark.css"))
                .toExternalForm());
  }

  @FXML
  void lightMode() {
    PersonalSettings.currentEmployee.setTheme("LIGHT");
    DBManager.update(PersonalSettings.currentEmployee);
    lightModeButton
        .getScene()
        .getStylesheets()
        .remove(
            Objects.requireNonNull(App.class.getResource("views/css/ThemeDark.css"))
                .toExternalForm());
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleItemAroundCenter(mainPane);
  }
}
