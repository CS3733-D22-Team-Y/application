package edu.wpi.cs3733.d22.teamY.controllers;

import javafx.scene.shape.Shape;

public class SceneUtil {
  protected static void startHover(Shape s) {
    s.setOpacity(1);
  }

  protected static void endHover(Shape s) {
    s.setOpacity(0);
  }
}
