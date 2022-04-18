package edu.wpi.cs3733.d22.teamY.controllers.requestTypes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainLoaderResult {
  private FXMLLoader loader;
  private Parent parent;

  public MainLoaderResult(FXMLLoader load, Parent par) {
    loader = load;
    parent = par;
  }

  public FXMLLoader getLoader() {
    return loader;
  }

  public Parent getParent() {
    return parent;
  }
}
