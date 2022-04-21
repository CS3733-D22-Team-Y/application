package edu.wpi.cs3733.d22.teamY.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class Scaling {
  private static double WINDOW_DEFAULT_WIDTH;
  private static double WINDOW_DEFAULT_HEIGHT;

  public static void initialize() {
    WINDOW_DEFAULT_WIDTH = NewSceneLoading.activeWindow.getWidth();
    WINDOW_DEFAULT_HEIGHT = NewSceneLoading.activeWindow.getHeight();
    System.out.println(WINDOW_DEFAULT_WIDTH);
    System.out.println(WINDOW_DEFAULT_HEIGHT);
  }

  public static void scaleItemAroundCenter(Region itemToScale) {
    double prefX = itemToScale.getLayoutX();
    double prefY = itemToScale.getLayoutY();
    double prefHeight = itemToScale.getHeight();
    double prefWidth = itemToScale.getWidth();
    double widthDiff = WINDOW_DEFAULT_WIDTH - prefWidth;
    double heightDiff = WINDOW_DEFAULT_HEIGHT - prefHeight;
    ReadOnlyDoubleProperty currHeight = NewSceneLoading.activeWindow.heightProperty();
    ReadOnlyDoubleProperty currWidth = NewSceneLoading.activeWindow.widthProperty();

    NumberBinding minScale =
        Bindings.min(
            currWidth.subtract(widthDiff).divide(WINDOW_DEFAULT_WIDTH),
            currHeight.subtract(heightDiff).divide(WINDOW_DEFAULT_HEIGHT));
    itemToScale.scaleXProperty().bind(minScale);
    itemToScale.scaleYProperty().bind(minScale);

    itemToScale.layoutXProperty().bind(minScale.multiply(410).subtract(440));
    itemToScale.layoutYProperty().bind(minScale.multiply(800 / 3).subtract(800 / 3 + 10));

    // itemToScale.layoutYProperty().bind(minScale.multiply(390).subtract(380));

    // sidebarFrame.layoutYProperty().bind(sidebarFrame.scaleYProperty().multiply(390).subtract(380));

    System.out.println("Scaling complete.");
  }

  public static void scaleItemAroundCenter(ObservableList<Node> items) {
    for (Node currItem : items) {
      try {
        scaleItemAroundCenter((Region) currItem);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static double test() {
    System.out.println("hi");
    return 10;
  }
}
