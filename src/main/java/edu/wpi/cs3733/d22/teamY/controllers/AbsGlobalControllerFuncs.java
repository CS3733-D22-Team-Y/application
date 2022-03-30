package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class AbsGlobalControllerFuncs {
  // Loads a scene into the app
  public void loadScene(String path) throws IOException {
    Scene sceneToLoad =
        new Scene(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(path))));
    App.getInstance().setScene(sceneToLoad);
  }

  void loadSidebar(Pane sidebarPane) throws IOException {
    // Add new scene if scene is not already loaded
    if (sidebarPane.getChildren().size() == 0) {
      Pane newPane = FXMLLoader.load(App.class.getResource("views/sideBar.fxml"));
      sidebarPane.getChildren().add(newPane);
    }
    // Remove loaded scene
    else {
      sidebarPane.getChildren().clear();
    }
  }
}
