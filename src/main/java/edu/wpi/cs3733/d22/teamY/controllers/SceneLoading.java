package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.ThemeType;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneLoading {
  private static String currentTheme = null;

  public static void loadScene(String path) throws IOException {
    Scene sceneToLoad =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(path))));
    App.getInstance().setScene(sceneToLoad);
    sceneToLoad.getStylesheets().clear();
    sceneToLoad
        .getStylesheets()
        .add(
            Objects.requireNonNull(App.class.getResource("views/css/NormalTheme.css"))
                .toExternalForm());
  }

  public static void loadPopup(String popPath, String parentPath) throws IOException {
    Stage stage = new Stage();
    Scene sceneToLoad =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(popPath))));
    Scene parentScene =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(parentPath))));
    stage.setScene(sceneToLoad);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(parentScene.getWindow());
    stage.showAndWait();
  }

  public static void setCurrentTheme(String theme) {
    currentTheme = ThemeType.convertTheme(theme);
  }

  public static String getCurrentTheme() {
    if (currentTheme == null) {
      return "views/css/NormalTheme.css";
    } else {
      return currentTheme;
    }
  }
}
