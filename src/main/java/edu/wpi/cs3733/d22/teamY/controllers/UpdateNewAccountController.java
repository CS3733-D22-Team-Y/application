package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class UpdateNewAccountController {
  @FXML JFXButton backToMainButton;
  @FXML MFXPasswordField newPasswordField;
  @FXML Polygon submitTriangle;
  @FXML Label passwordValidityError;
  public static String user;

  public static void userNameToChange(String userName) {
    user = userName;
  }

  @FXML
  void goToMainPage() throws IOException {
    passwordValidityError.setVisible(false);
    SceneLoading.loadScene("views/Welcome.fxml");
    System.out.println(PersonalSettings.currentEmployee.getUsername());
  }

  @FXML
  void submitAndReturn() throws IOException {
    if (!Employee.isValidNewPassword(newPasswordField.getText())) {
      passwordValidityError.setVisible(true);
      FadeTransition ft = new FadeTransition(Duration.millis(1000), passwordValidityError);
      ft.setFromValue(0.0);
      ft.setToValue(1.0);
      ft.play();
    } else {
      DBUtils.changePassword(
          String.valueOf(user.hashCode()),
          String.valueOf("1234".hashCode()),
          String.valueOf(newPasswordField.getText()));
      System.out.println(newPasswordField.getText());
      SceneLoading.loadScene("views/Welcome.fxml");
    }
    // password must be at least 5 characters long, and contain at least one number and one letter
    // and one special character
  }
}
