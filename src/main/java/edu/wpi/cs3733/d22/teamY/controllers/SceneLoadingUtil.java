package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SceneLoadingUtil {
  public static void loadScene(String path) throws IOException {
    Scene sceneToLoad =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(path))));
    App.getInstance().setScene(sceneToLoad);
  }
}
