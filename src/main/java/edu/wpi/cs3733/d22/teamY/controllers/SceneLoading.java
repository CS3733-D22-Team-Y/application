package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneLoading {
  public static void loadScene(String path) throws IOException {
    Scene sceneToLoad =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(path))));
    App.getInstance().setScene(sceneToLoad);
  }

  public static void loadPopup(String popPath, String parentPath) throws IOException {
    Stage stage = new Stage(StageStyle.UNDECORATED);
    Scene sceneToLoad =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(popPath))));
    Scene parentScene =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(parentPath))));
    stage.setScene(sceneToLoad);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(parentScene.getWindow());
    stage.showAndWait();
  }
}
