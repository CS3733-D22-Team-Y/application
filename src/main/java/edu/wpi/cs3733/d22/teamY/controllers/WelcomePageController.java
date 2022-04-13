package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.Auth;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.controllers.requestTypes.MainLoaderResult;
import edu.wpi.cs3733.d22.teamY.controllers.requestTypes.RequestControllerUtil;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class WelcomePageController {

  @FXML private MFXTextField username;
  @FXML private MFXPasswordField password;
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
  @FXML JFXToggleButton dbSwitcherToggle;
  @FXML JFXComboBox<String> dbMenu;

  private boolean lockOut = false;

  private boolean loadingRightNow = false;

  int maxAttempts = 5;
  int attCount = 0;

  String universalCode;

  String[] dbOptions = new String[] {"Embedded", "Client-Server", "GoogleCloud"};

  @FXML
  void initialize() throws IOException {
    loginPane.setVisible(true);
    loading.setVisible(false);
    yubikeyPane.setVisible(false);
    dbMenu.getItems().addAll(dbOptions);

    loadingRightNow = false;
  }

  @FXML
  public void mainPage() throws IOException {
    if (loadingRightNow) return;
    loadingRightNow = true;

    SceneUtil.welcomePage = this;
    RequestControllerUtil.initialize();

    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SideBar.fxml"));
    App.getInstance().setScene(new Scene(loader.load()));
    SideBarController controller = loader.getController();
    try {
      controller.initializeScale();
      controller.loadViewServiceRequests();
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    loadingRightNow = false;
  }

  public void mainPageThreaded() throws IOException {
    if (loadingRightNow) return;
    loadingRightNow = true;

    SceneUtil.welcomePage = this;
    RequestControllerUtil.initialize();

    loadMainTask.setOnSucceeded(
        e -> {
          App.getInstance().setScene(new Scene(loadMainTask.getValue().getParent()));
          SideBarController controller = loadMainTask.getValue().getLoader().getController();
          try {
            controller.initializeScale();
            controller.loadViewServiceRequests();
          } catch (IOException ex) {
            ex.printStackTrace();
          }

          loadingRightNow = false;
        });

    Thread t = new Thread(loadMainTask);
    t.setDaemon(true);
    t.start();
  }

  Task<MainLoaderResult> loadMainTask =
      new Task<>() {
        @Override
        protected MainLoaderResult call() throws IOException {
          FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SideBar.fxml"));
          return new MainLoaderResult(loader, loader.load());
        }
      };

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
    if (username.getText().length() <= 0 || password.getText().length() <= 0) {
      showLoginFail(false);
      return;
    }

    if (DBUtils.isValidLogin(username.getText(), password.getText()) && !lockOut) {
      if (DBUtils.checkDefaultPassword(password.getText().hashCode())) {
        UpdateNewAccountController.userNameToChange(username.getText());
        SceneLoading.loadScene("views/AccountUpdate.fxml");
      }
      display2FAOptions();
    } else {
      showLoginFail(true);
    }
  }

  private void showLoginFail(boolean deduct) {
    failedLoginPane.setOpacity(0.0);
    failedLoginPane.setVisible(true);
    FadeTransition ft = new FadeTransition(Duration.millis(1000), failedLoginPane);
    ft.setFromValue(0.0);
    ft.setToValue(1.0);
    FadeTransition ft2 = new FadeTransition(Duration.millis(1000), failedLoginPane);
    ft2.setFromValue(1.0);
    if (deduct) {
      if (attCount >= maxAttempts) {
        lockOut = true;
        attemptsRemaining.setText("Too many login attempts.\nTry again later.");
        ft2.setToValue(1.0);
      } else {
        attemptsRemaining.setText(
            "Incorrect username or password. Attempts left: " + (maxAttempts - attCount));
        ft2.setToValue(0.0);
      }
      attCount++;
    } else {
      attemptsRemaining.setText("Please enter a valid login.");
    }

    Timeline tl =
        new Timeline(
            new KeyFrame(Duration.seconds(0), (e) -> ft.play()),
            new KeyFrame(Duration.seconds(4), (e) -> {}));
    tl.play();
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

  void display2FAOptions() throws IOException {
    String[] auth = Auth.getKeys(username.getText());
    if (username.getText().equals("admin")) {
      faPane.setVisible(false);
      loginPane.setVisible(false);
      loginAnimation();
    }
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
  void loginAnimation() {
    loginPane.setVisible(false);
    loading.setVisible(true);
    Welcome.setText("Welcome, " + DBUtils.getNameFromID(username.getText()));

    try {
      mainPageThreaded();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void databaseSwitch() {
    DBUtils.switchDBType(dbMenu.getValue());
  }

  public void testingButton() throws IOException {
    loginAnimation();
  }

  public static String getCode() {
    return String.format("%06d", new Random().nextInt(999999));
  }

  public void createNewUser() throws Exception {
    try {
      SceneLoading.loadScene("views/CreateAccount.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
