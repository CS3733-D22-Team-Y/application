package edu.wpi.cs3733.d22.teamY.controllers;

import static org.apache.commons.lang3.RandomStringUtils.*;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.Auth;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class WelcomePageController {

  @FXML private TextField username;
  @FXML private PasswordField password;
  @FXML private Pane loginPane;
  @FXML private Pane failedLoginPane;
  @FXML private Label attemptsRemaining;
  @FXML private Label Welcome;
  @FXML Pane loading;
  @FXML Pane yubikeyPane;
  @FXML TextField yubikeyEntry;
  @FXML Label yubikeyInstruct;
  @FXML Pane faYubikeyPane;
  @FXML Pane faPane;
  @FXML JFXButton faYubikeyButton;
  @FXML TextField codeEntryField;
  @FXML Pane codeEntryPane;
  @FXML Pane faPushPane;
  @FXML JFXButton faPushButton;
  @FXML Pane faEmailPane;
  @FXML JFXButton faEmailButton;
  @FXML Pane faSmsPane;
  @FXML JFXButton faSmsButton;
  @FXML Label codeEntryLabel;
  private boolean lockOut = false;

  int maxAttempts = 5;
  int attCount = 0;

  String universalCode;

  void initialize() throws IOException {
    loginPane.setVisible(true);
    loading.setVisible(false);
    yubikeyPane.setVisible(false);
  }

  @FXML
  void mainPage() throws IOException {
    SceneLoading.loadScene("views/Map.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    Platform.exit();
  }

  @FXML
  void loginToMainPage() throws IOException, InterruptedException {
    //     if (DBUtils.isValidLogin(username.getText(), password.getText())
    //         && !lockOut
    //         && Auth.doAuth(username.getText())) {
    //       loginAnimation();
    if (DBUtils.isValidLogin(username.getText(), password.getText()) && !lockOut) {
      display2FAOptions();
    } else {
      failedLoginPane.setOpacity(0.0);
      failedLoginPane.setVisible(true);
      FadeTransition ft = new FadeTransition(Duration.millis(1000), failedLoginPane);
      ft.setFromValue(0.0);
      ft.setToValue(1.0);
      FadeTransition ft2 = new FadeTransition(Duration.millis(1000), failedLoginPane);
      ft2.setFromValue(1.0);
      if (attCount >= maxAttempts) {
        lockOut = true;
        attemptsRemaining.setText("No Remaining Attempts");
        ft2.setToValue(1.0);
      } else {
        attemptsRemaining.setText((maxAttempts - attCount) + " Attempts Remain");
        ft2.setToValue(0.0);
      }
      Timeline tl =
          new Timeline(
              new KeyFrame(Duration.seconds(0), (e) -> ft.play()),
              new KeyFrame(Duration.seconds(4), (e) -> {}),
              new KeyFrame(Duration.seconds(5), (e) -> ft2.play()));
      tl.play();
      attCount++;
    }
  }

  public void yubikeyPrompt() {
    faPane.setVisible(false);
    faYubikeyPane.setVisible(false);
    loginPane.setVisible(false);
    yubikeyPane.setVisible(true);
    yubikeyEntry.requestFocus();
  }

  @FXML
  public void yubikeyDone() throws Exception {
    if (Auth.vaildYubikey(yubikeyEntry.getText())) {
      yubikeyPane.setVisible(false);
      loginAnimation();
    } else {
      Timeline tl =
          new Timeline(
              new KeyFrame(
                  Duration.seconds(0), (e) -> yubikeyInstruct.setText("Yubikey Login Failed")),
              new KeyFrame(
                  Duration.seconds(2),
                  (e) -> {
                    try {
                      SceneLoading.loadScene("views/Welcome.fxml");
                    } catch (IOException ex) {
                      ex.printStackTrace();
                    }
                  }));
      tl.play();
    }
  }

  void display2FAOptions() {
    String[] auth = Auth.getKeys(username.getText());
    if (Arrays.asList(auth).contains("yubikey")) {
      faYubikeyButton.setVisible(true);
      faYubikeyPane.setOpacity(1);
    }
    if (Arrays.asList(auth).contains("pushbullet")) {
      faPushButton.setVisible(true);
      faPushPane.setOpacity(1);
    }
    if (Arrays.asList(auth).contains("email")) {
      faEmailButton.setVisible(true);
      faEmailPane.setOpacity(1);
    }
    if (Arrays.asList(auth).contains("sms")) {
      faSmsButton.setVisible(true);
      faSmsPane.setOpacity(1);
    }

    loginPane.setVisible(false);
    faPane.setVisible(true);
  }

  @FXML
  public void sendPushBullet() {
    String code = getCode();
    universalCode = code;
    Auth.doPushBulletAuth(code, Auth.getAtts("pushbullet", username.getText())[0]);
    codePrompt();
  }

  @FXML
  public void sendEmail() {
    String code = getCode();
    universalCode = code;
    Auth.doMailAuth(code, Auth.getAtts("email", username.getText())[0]);
    codePrompt();
  }

  @FXML
  public void sendSms() {
    String code = getCode();
    universalCode = code;
    Auth.doTwilioAuth(code, Auth.getAtts("sms", username.getText())[0]);
    codePrompt();
  }

  @FXML
  public void codePrompt() {
    faPane.setVisible(false);
    codeEntryPane.setVisible(true);
  }

  @FXML
  public void codeChecker() throws IOException {
    if (codeEntryField.getText().equals(universalCode)) {
      codeEntryPane.setVisible(false);
      loginAnimation();
    } else {
      codeEntryField.setText("");
      Timeline failed2FA =
          new Timeline(
              new KeyFrame(Duration.seconds(0), (e) -> codeEntryLabel.setText("Incorrect Code")),
              new KeyFrame(
                  Duration.seconds(2),
                  (e) -> {
                    try {
                      SceneLoading.loadScene("views/Welcome.fxml");
                    } catch (IOException ex) {
                      ex.printStackTrace();
                    }
                  }));
      failed2FA.play();
    }
  }

  @FXML
  void loginAnimation() throws IOException {
    Image loadingGif =
        new Image(
            App.class.getResource("views/images/loading.gif").toString(), 959, 601, false, false);
    ImageView ugh = new ImageView(loadingGif);
    loading.getChildren().add(ugh);
    Timeline loginTimeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0),
                (e) -> Welcome.setText("Welcome, " + DBUtils.getNameFromID(username.getText()))),
            new KeyFrame(Duration.seconds(0.01), (e) -> loginPane.setVisible(false)),
            new KeyFrame(Duration.seconds(0.02), (e) -> loading.setVisible(true)),
            new KeyFrame(
                Duration.seconds(1),
                (e) -> {
                  try {
                    mainPage();
                  } catch (IOException ex) {
                    ex.printStackTrace();
                  }
                }));
    loginTimeline.play();
  }

  public void testingButton() throws IOException {
    loginAnimation();
  }

  public static String getCode() {
    return String.format("%06d", new Random().nextInt(999999));
  }
}
