package edu.wpi.cs3733.d22.teamY.controllers;

import static org.apache.commons.lang3.RandomStringUtils.*;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Locale;
import java.util.UUID;
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

  private boolean lockOut = false;

  int maxAttempts = 5;
  int attCount = 0;

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
    if (DBUtils.isValidLogin(username.getText(), password.getText()) && !lockOut) {
      yubikeyPrompt();
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

  void yubikeyPrompt() {
    loginPane.setVisible(false);
    yubikeyPane.setVisible(true);
    yubikeyEntry.requestFocus();
  }

  @FXML
  void yubikeyDone() throws Exception {
    if (vaildYubikey(yubikeyEntry.getText())) {
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

  void display2FAOptions() {}

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

  public static boolean vaildYubikey(String key) throws Exception {
    Boolean valid = false;
    UUID randomUUID = UUID.randomUUID();
    String query =
        "https://api.yubico.com/wsapi/2.0/verify?otp="
            + key
            + "&id=73695&nonce="
            + randomAlphabetic(20).toUpperCase(Locale.ROOT);
    if (getHTML(query).equals("OK")) {
      return true;
    }
    return false;
  }

  public static String getHTML(String urlToRead) throws Exception {
    StringBuilder result = new StringBuilder();
    URL url = new URL(urlToRead);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      for (String line; (line = reader.readLine()) != null; ) {
        if (line.contains("status")) {
          result.append(line.replaceAll("status=", ""));
        }
      }
    }
    return result.toString();
  }

  public void testingButton() throws IOException {
    loginAnimation();
  }
}
