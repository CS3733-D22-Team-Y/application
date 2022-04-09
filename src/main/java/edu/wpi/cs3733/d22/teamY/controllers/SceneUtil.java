package edu.wpi.cs3733.d22.teamY.controllers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class SceneUtil {
  protected static void startHover(Shape s) {
    s.setOpacity(1);
  }

  protected static void endHover(Shape s) {
    s.setOpacity(0);
  }

  protected static void hideAllPanes(ObservableList<Node> panes) {
    for(Node thisPane : panes) {
      thisPane.setVisible(false);
    }
  }
}
