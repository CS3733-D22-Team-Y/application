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
    loadSceneInPane(sidebarPane, "views/sideBar.fxml");
  }

  void removeSidebar(Pane sidebarPane) {
    sidebarPane.getChildren().clear();
  }

  void loadSceneInPane(Pane paneToLoadInto, String path) throws IOException {
    Pane paneToLoad = FXMLLoader.load(App.class.getResource(path));
    // Optional, but good to include just to be safe. Removes all other scenes in the pane/
    paneToLoadInto.getChildren().clear();
    // Add the new pane
    paneToLoadInto.getChildren().add(paneToLoad);
  }
}
