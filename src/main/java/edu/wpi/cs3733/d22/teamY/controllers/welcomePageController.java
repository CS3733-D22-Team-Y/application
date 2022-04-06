package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class welcomePageController {

  @FXML private TextField username;
  @FXML private PasswordField password;
  @FXML private Pane loginPane;
  @FXML private Pane failedLoginPane;
  @FXML private Label attemptsRemaining;
  @FXML private Label Welcome;
  @FXML Pane loading;

  private boolean lockOut = false;

  int maxAttempts = 5;
  int attCount = 0;

  void initialize() throws IOException {
    loginPane.setVisible(true);
    loading.setVisible(false);
  }

  @FXML
  void mainPage() throws IOException {
    SceneLoading.loadScene("views/mapPage.fxml");
  }

  @FXML
  void killApplication() throws IOException {
    System.out.println("pressed button");
    SceneLoading.loadPopup("views/ConfirmClose.fxml", "views/welcomePage.fxml");
  }

  @FXML
  void loginToMainPage() throws IOException, InterruptedException {
    if (DBUtils.isValidLogin(username.getText(), password.getText()) && !lockOut) {
      loginAnimation();
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
                Duration.seconds(3),
                (e) -> {
                  try {
                    mainPage();
                  } catch (IOException ex) {
                    ex.printStackTrace();
                  }
                }));
    loginTimeline.play();
  }
}
