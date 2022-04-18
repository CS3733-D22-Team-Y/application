package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class NewSceneLoading {
  // Hashmap of scenes, key: path, content: scene
  private static final HashMap<String, Scene> allScenes = new HashMap<>();
  private static AnchorPane sidebar;
  private static SideBarController sidebarController;

  static {
    try {
      FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SideBar.fxml"));
      sidebar = loader.load();
      sidebarController = loader.getController();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static SideBarController sideBarController;

  // Function to load scene
  public static void addScene(String path) throws IOException {
    if (!allScenes.containsKey(path)) {
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
  public static void reloadScene(String pathToReload) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(pathToReload)));
    Scene newScene = new Scene(root);
    allScenes.remove(pathToReload);
    allScenes.put(pathToReload, newScene);
  }

  public static void loadScene(String path) {
    Scene currScene = allScenes.get(path);
    try {
      addSidebarHelper(currScene);
    } catch (IllegalArgumentException e) {
    } catch (IOException e) {
    }
    App.getInstance().setScene(currScene);
  }

  public static void loadSidebar(AnchorPane sidebarPane) throws IOException {
    sidebarPane.getChildren().clear();
    sidebarPane.getChildren().add(sidebar);
  }

  private static void addSidebarHelper(Scene currScene) throws IOException {
    if (currScene.lookup("#sidebarPane") != null) {
      AnchorPane mainPane = (AnchorPane) currScene.lookup("#sidebarPane");
      mainPane.getChildren().add(sidebar);
      sidebarController.initializeScale(currScene);
    }
  }
}
