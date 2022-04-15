package edu.wpi.cs3733.d22.teamY;

import com.google.firebase.database.*;
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

  // changes active scene
  public void setScene(Scene scene) {
    primaryStage.setScene(scene);
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
  public void start(Stage primaryStage) throws Exception {
    instance = this; // instantiates instance
    primaryStage.setMinWidth(900);
    primaryStage.setMinHeight(600);
    this.primaryStage = primaryStage;
    // Sets the primary scene (currently request menu) and displays it
    Parent root =
        FXMLLoader.load(Objects.requireNonNull(App.class.getResource("views/Welcome.fxml")));
    Scene scene = new Scene(root);
    scene
        .getStylesheets()
        .add(
            "https://fonts.googleapis.com/css2?family=Nunito:wght@700&family=Varela+Round&display=swap");
    scene.getStylesheets().add("http://fonts.googleapis.com/css2?family=Varela+Round");
    scene
        .getStylesheets()
        .add(Objects.requireNonNull(App.class.getResource("views/css/Blank.css")).toExternalForm());
    scene
        .getStylesheets()
        .add(Objects.requireNonNull(App.class.getResource("views/css/Fonts.css")).toExternalForm());
    primaryStage.setTitle("Bringham and Women's Hospital Equipment Tracker");
    primaryStage.setScene(scene);
    primaryStage.show();
    // camera.newPfp();
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
