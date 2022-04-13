package edu.wpi.cs3733.d22.teamY;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    // Initialize Chromium.
    Engine engine = Engine.newInstance(HARDWARE_ACCELERATED);

    // Create a Browser instance.
    Browser browser = engine.newBrowser();

    // Load the required web page.
    browser.navigation().loadUrl("https://html5test.com");

    // Create and embed JavaFX BrowserView component to display web content.
    BrowserView view = BrowserView.newInstance(browser);

    Scene scene = new Scene(new BorderPane(view), 1280, 800);
    primaryStage.setTitle("JxBrowser JavaFX");
    primaryStage.setScene(scene);
    primaryStage.show();

    // Shutdown Chromium and release allocated resources.
    primaryStage.setOnCloseRequest(event -> engine.close());

    /*instance = this; // instantiates instance
    primaryStage.setMinWidth(900);
    primaryStage.setMinHeight(600);
    this.primaryStage = primaryStage;
    // Sets the primary scene (currently request menu) and displays it
    Parent root =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/Welcome.fxml")));
    Scene scene = new Scene(root);
    scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Fredoka+One");
    scene.getStylesheets().add("http://fonts.googleapis.com/css2?family=Varela+Round");
    primaryStage.setTitle("Bringham and Women's Hospital Equipment Tracker");
    primaryStage.setScene(scene);
    primaryStage.show();
    // camera.newPfp();
    */
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
