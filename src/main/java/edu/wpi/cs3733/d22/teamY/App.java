package edu.wpi.cs3733.d22.teamY;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {
  private Stage primaryStage;
  private Scene securityServicesRequest;
  private Scene requestMenu;
  private Scene floralRequest;
  private Scene laundryRequest;
  private Scene medicalEquipmentRequest;
  private Scene mealRequest;
  private Scene activeRequests;

  // Methods to change the scene to the corresponding Request
  public void setSceneToRequestMenu() {
    primaryStage.setScene(requestMenu);
  }

  public void setSceneToSecurityServicesRequest() {
    primaryStage.setScene(securityServicesRequest);
  }

  public void setSceneToFloralRequest() {
    primaryStage.setScene(floralRequest);
  }

  public void setSceneToLaundryRequest() {
    primaryStage.setScene(laundryRequest);
  }

  public void setSceneToMedicalEquipmentRequest() {
    primaryStage.setScene(medicalEquipmentRequest);
  }

  public void setSceneToMealRequest() {
    primaryStage.setScene(mealRequest);
  }

  public void setSceneToActiveRequests() {
    primaryStage.setScene(activeRequests);
  }

  private static App instance;
  // Summons instances for scene change implementation
  public static App getInstance() {
    return instance;
  }

  @Override
  public void init() {
    log.info("Starting Up");
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    instance = this; // instantiates instance
    this.primaryStage = primaryStage;
    // Sets the primary scene (currently request menu) and displays it
    Parent root =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/requestMenu.fxml")));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();

    // Creates scenes for the corresponding fxml files
    requestMenu =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));
    securityServicesRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(
                    App.class.getResource("views/securityServicesRequest.fxml"))));
    floralRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/floralRequest.fxml"))));
    laundryRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/laundryRequest.fxml"))));
    mealRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/mealRequest.fxml"))));
    medicalEquipmentRequest =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(
                    App.class.getResource("views/medicalEquipmentRequest.fxml"))));

    activeRequests =
        new Scene(
            FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("views/activeRequests.fxml"))));
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
