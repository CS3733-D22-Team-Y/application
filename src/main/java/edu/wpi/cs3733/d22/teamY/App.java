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
  public void start(Stage primaryStage) throws IOException {
    instance = this; // instantiates instance
    this.primaryStage = primaryStage;
    // Sets the primary scene (currently request menu) and displays it
    Parent root =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/welcomePage.fxml")));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
