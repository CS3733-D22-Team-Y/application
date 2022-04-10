package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class SceneUtil {
  protected static void startHover(Shape s) {
    s.setOpacity(1);
  }

  protected static void endHover(Shape s) {
    s.setOpacity(0);
  }

  /**
   * Hides all the given panes.
   *
   * @param panes The list of panes to hide.
   */
  protected static void hideAllPanes(ObservableList<Node> panes) {
    for (Node thisPane : panes) {
      thisPane.setVisible(false);
    }
  }

  /**
   * Makes the backgrounds of all the given items transparent, provided it is an AnchorPane with
   * fx:id "mainPane".
   *
   * @param panes The list of all the nodes to pass through.
   */
  protected static void hideAllBackgrounds(ObservableList<javafx.scene.Node> panes) {
    for (Node currPane : panes) {
      AnchorPane currAnchor = (AnchorPane) currPane.lookup("#mainPane");
      currAnchor.setBackground(Background.EMPTY);
    }
  }

  /**
   * Loads all of the given panes into the children of a single pane.
   *
   * @param mainScreenPane The pane to load everything into.
   * @param paths The paths to the FXMLs to load into the main pane.
   * @throws IOException
   */
  protected static void initializePanes(Pane mainScreenPane, String... paths) throws IOException {
    for (String currPath : paths) {
      Pane currPane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(currPath)));
      mainScreenPane.getChildren().add(currPane);
    }
  }

  /**
   * WSets the opacity of the given shapes to 0.
   *
   * @param shapes The shapes.
   */
  protected static void removeOpacity(Shape... shapes) {
    for (Shape currShape : shapes) {
      currShape.setOpacity(0);
    }
  }
}
