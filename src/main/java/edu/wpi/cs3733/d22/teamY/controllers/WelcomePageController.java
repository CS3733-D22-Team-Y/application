package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.Auth;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.Messaging.Firebase;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class WelcomePageController implements IController {

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
  @FXML AnchorPane mainPane;

  private boolean lockOut = false;

  private boolean loadingRightNow = false;

  int maxAttempts = 5;
  int attCount = 0;

  String universalCode;

  String[] dbOptions = new String[] {"Embedded", "Client-Server"};

  @FXML
  void initialize() throws IOException {
    NewSceneLoading test = new NewSceneLoading();
    loginPane.setVisible(true);
    loading.setVisible(false);
    yubikeyPane.setVisible(false);
    dbMenu.getItems().addAll(dbOptions);

    loadingRightNow = false;

    NewSceneLoading.addMultipleScenes(
        "views/PersonalSettings.fxml",
        "views/MedEquipTable.fxml",
        "views/ActiveServiceRequest.fxml",
        "views/AccountUpdate.fxml",
        "views/CreateAccount.fxml",
        "views/MedEquipTable.fxml",
        "views/PersonalSettings.fxml",
        "views/RequestMenu.fxml",
        "views/requestTypes/FloralRequest.fxml",
        "views/requestTypes/LabResult.fxml",
        "views/requestTypes/LaundryRequest.fxml",
        "views/requestTypes/MealRequest.fxml",
        "views/requestTypes/MedicalEquipmentRequest.fxml",
        "views/requestTypes/MiscRequest.fxml",
        "views/requestTypes/SecurityRequest.fxml",
        "views/requestTypes/TranslatorRequest.fxml",
        "views/Map.fxml",
        "views/requestTypes/SpecialistRequest.fxml",
        "views/requestTypes/MaintenanceRequest.fxml",
        "views/requestTypes/FacilitiesRequest.fxml",
        "views/Dashboard.fxml");
  }

  @FXML
  public void mainPage() throws IOException {
    if (loadingRightNow) return;
    loadingRightNow = true;

    SceneUtil.welcomePage = this;
    // RequestControllerUtil.initialize();

    /*
    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SideBar.fxml"));
    App.getInstance().setScene(new Scene(loader.load()));
    SideBarController controller = loader.getController();
    try {
      controller.initializeScale();
      controller.loadViewServiceRequests();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
     */
    // NewSceneLoading.loadScene("views/ActiveServiceRequest.fxml");

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
          SideBarController controller = NewSceneLoading.sideBarController;
          try {
            // controller.initializeScale();
            controller.loadDashboard();
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
          FXMLLoader loader = new FXMLLoader(App.class.getResource("views/Dashboard.fxml"));
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
        NewSceneLoading.loadScene("views/AccountUpdate.fxml");
      }
      display2FAOptions();

    } else {
      showLoginFail(true);
    }

    // NewSceneLoading.loadScene("views/SideBar.fxml");
    // NewSceneLoading.loadScene("views/ActiveServiceRequest.fxml");

    /*
    try {
      //controller.initializeScale();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
     */
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
                    NewSceneLoading.loadScene("views/Welcome.fxml");
                  }));
      tl.play();
    }
  }

  void display2FAOptions() throws IOException {
    String[] auth = Auth.getKeys(username.getText());
    boolean didAuth = false;
    if (username.getText().equals("admin")) {
      faPane.setVisible(false);
      loginPane.setVisible(false);
      loginAnimation();
    }
    if (Arrays.asList(auth).contains("yubikey")) {
      faYubikeyButton.setVisible(true);
      faYubikeyPane.setOpacity(1);
      didAuth = true;
    }
    if (Arrays.asList(auth).contains("pushbullet")) {
      faPushButton.setVisible(true);
      faPushPane.setOpacity(1);
      didAuth = true;
    }
    if (Arrays.asList(auth).contains("email")) {
      faEmailButton.setVisible(true);
      faEmailPane.setOpacity(1);
      didAuth = true;
    }
    if (Arrays.asList(auth).contains("sms")) {
      faSmsButton.setVisible(true);
      faSmsPane.setOpacity(1);
      didAuth = true;
    }

    loginPane.setVisible(false);
    faPane.setVisible(true);

    if (!didAuth) {
      System.out.println("No 2FA methods available!");
      loginAnimation();
    }
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
                    NewSceneLoading.loadScene("views/Welcome.fxml");
                  }));
      failed2FA.play();
    }
  }

  @FXML
  void loginAnimation() throws IOException {
    loginPane.setVisible(false);
    loading.setVisible(true);
    String name = DBUtils.getPrefNameFromID(username.getText());
    Welcome.setText("Welcome, " + (name.trim().equals("") ? "Guest" : name));
    Firebase.initListeners();
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

  public static String getCode() {
    return String.format("%06d", new Random().nextInt(999999));
  }

  public void createNewUser() throws Exception {
    NewSceneLoading.loadScene("views/CreateAccount.fxml");
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleFullscreenItemAroundTopLeft(mainPane);
  }
}
