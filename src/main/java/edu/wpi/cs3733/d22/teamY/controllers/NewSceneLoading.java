package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class NewSceneLoading {
  // Hashmap of scenes, key: path, content: scene
  private static final HashMap<String, Scene> allScenes = new HashMap<>();
  private static final HashMap<String, IController> allControllers = new HashMap<>();
  private static AnchorPane sidebar;
  private static SideBarController sidebarController;
  public static Window activeWindow;
  private static Scene currScene;

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
      FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
      Parent root = loader.load();
      Scene newScene = new Scene(root);
      IController controller = loader.getController();
      allControllers.put(path, controller);
      allScenes.put(path, newScene);
    }
  }

  public static void removeScene(String path) {
    if (allScenes.containsKey(path)) {
      allScenes.remove(path);
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
    currScene = allScenes.get(path);
    try {
      addSidebarHelper(currScene);
    } catch (IllegalArgumentException | IOException e) {
    }
    IController controller = allControllers.get(path);

    // Preserve height and width of scene
    double height = activeWindow.getHeight();
    double width = activeWindow.getWidth();

    App.getInstance().setScene(currScene);
    // controller.initializeScale();

    // Preserve height and width of scene
    activeWindow.setHeight(height);
    activeWindow.setWidth(width);
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
