package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class NewSceneLoading {
  // Hashmap of scenes, key: path, content: scene
  private static final HashMap<String, Scene> allScenes = new HashMap<>();
  private static Node sidebarNode = testLoad();

  public static SideBarController sideBarController;

  private static Node testLoad() {
    try {
      return FXMLLoader.load(App.class.getResource("views/SideBar.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  // Function to load scene
  public static void addScene(String path) throws IOException {
    if (!allScenes.containsKey(path)) {
      // FXMLLoader loader = new FXMLLoader(App.class.getResource("views/Welcome.fxml"));
      // Scene newScene = new Scene(loader.load());
      Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(path)));
      Scene newScene = new Scene(root);
      allScenes.put(path, newScene);
    }
  }

  public static void addMultipleScenes(String... paths) throws IOException {
    for (String currPath : paths) {
      addScene(currPath);
    }
  }

  // Function to reload scene
  public static void reloadScene(String path) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
    Scene newScene = loader.load();
    allScenes.remove(path);
    allScenes.put(path, newScene);
  }

  public static void loadScene(String path) {
    App.getInstance().setScene(allScenes.get(path));
  }

  public static void loadSidebar(AnchorPane sidebarPane) throws IOException {
    sidebarNode = testLoad();
    sidebarPane.getChildren().clear();
    sidebarPane.getChildren().add(sidebarNode);
    sideBarController.initializeScale();
  }
}
