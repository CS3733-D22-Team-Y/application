package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.AuthTypes;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.EntryType;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class CreateAccountController {

  @FXML MFXTextField username;
  @FXML MFXTextField password;
  @FXML MFXTextField legalName;
  @FXML MFXTextField prefName;
  @FXML MFXTextField role;
  @FXML MFXTextField email;
  @FXML MFXTextField dob;
  @FXML MFXTextField pronouns;
  @FXML MFXTextField phone;
  @FXML MFXButton applyButton;
  @FXML MFXButton backButton;
  @FXML Label userExistsDisplay;
  @FXML Label invalidInputsDisplay;
  @FXML Label addUserSuccessDisplay;

  public void initialize() {
    invalidInputsDisplay.setVisible(false);
    userExistsDisplay.setVisible(false);
    addUserSuccessDisplay.setVisible(false);
  }

  @FXML
  public void createUser() {
    String defaultAccess = "1";
    String defaultFloor = "1";
    String id = String.valueOf(DBUtils.getNextRequestNum(EntryType.EMPLOYEE));
    String defaultPass = "1234";

    if (checkValid() && checkUserExists(username.getText().hashCode())) {
      userExistsDisplay.setVisible(false);
      invalidInputsDisplay.setVisible(false);
      Employee createdEmployee =
          new Employee(
              id,
              legalName.getText(),
              role.getText(),
              defaultAccess,
              defaultFloor,
              String.valueOf(username.getText().hashCode()),
              String.valueOf(defaultPass.hashCode()),
              Integer.parseInt(defaultAccess),
              null,
              prefName.getText(),
              email.getText(),
              phone.getText(),
              dob.getText(),
              pronouns.getText());

      String args[] = {};
      createdEmployee.addAuthMode(AuthTypes.NONE, args);

      DBManager.save(createdEmployee);

      addUserSuccessDisplay.setVisible(true);
      FadeTransition ft = new FadeTransition(Duration.millis(1000), addUserSuccessDisplay);
      ft.setFromValue(0.0);
      ft.setToValue(1.0);
      ft.play();

      System.out.println("Saved employee");
    } else {
      System.out.println("Invalid Inputs");
    }
  }

  @FXML
  void goBackToMain() {
    try {
      SceneLoading.loadScene("views/Welcome.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check if All Fields are filled out
   *
   * @return false if empty true if filled.
   */
  private boolean checkValid() {
    boolean state =
        !(username.getText().isEmpty()
            || legalName.getText().isEmpty()
            || prefName.getText().isEmpty()
            || role.getText().isEmpty()
            || email.getText().isEmpty()
            || dob.getText().isEmpty()
            || pronouns.getText().isEmpty()
            || phone.getText().isEmpty());
    if (!state) {
      invalidInputsDisplay.setVisible(true);
      FadeTransition ft = new FadeTransition(Duration.millis(1000), invalidInputsDisplay);
      ft.setFromValue(0.0);
      ft.setToValue(1.0);
      ft.play();
    }

    return state;
  }

  private boolean checkUserExists(int hashedUser) {
    boolean state = DBUtils.doesUserExist(hashedUser);
    if (!state) {
      userExistsDisplay.setVisible(true);
      FadeTransition ft = new FadeTransition(Duration.millis(1000), userExistsDisplay);
      ft.setFromValue(0.0);
      ft.setToValue(1.0);
      ft.play();
    }
    return state;
  }
}
