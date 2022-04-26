package edu.wpi.cs3733.d22.teamY.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class Scaling {
  private static double WINDOW_DEFAULT_WIDTH;
  private static double WINDOW_DEFAULT_HEIGHT;

  private static ReadOnlyDoubleProperty currHeight = NewSceneLoading.activeWindow.heightProperty();
  private static ReadOnlyDoubleProperty currWidth = NewSceneLoading.activeWindow.widthProperty();

  /**
   * Initializes the window to the current height and width. Should be run when the very first
   * window opens (in this case, in App.java).
   */
  public static void initialize() {
    WINDOW_DEFAULT_WIDTH = NewSceneLoading.activeWindow.getWidth();
    WINDOW_DEFAULT_HEIGHT = NewSceneLoading.activeWindow.getHeight();
  }

  /**
   * Scales an item that is intended to be fullscreen around the top left corner. The item is scaled
   * so that all of it will always be visible and the aspect ratio is preserved.
   *
   * @param itemToScale The item to scale.
   */
  public static void scaleFullscreenItemAroundTopLeft(Region itemToScale) {
    double prefHeight = itemToScale.getHeight();
    double prefWidth = itemToScale.getWidth();

    // Scales the item according to the size of the screen.
    // Figure out the minimum scale to ensure the given images are always fully in the window.
    NumberBinding minScale =
        Bindings.min(
            currWidth.divide(WINDOW_DEFAULT_WIDTH), currHeight.divide(WINDOW_DEFAULT_HEIGHT));
    // Bind the scale.
    itemToScale.scaleXProperty().bind(minScale);
    itemToScale.scaleYProperty().bind(minScale);

    // Changes the layout of the item to account for the scale around the center.
    // Not perfect, but good enough for now
    double widthFactor = 2.2f;
    double heightFactor = 2.2f;
    System.out.println("Width factor: " + widthFactor);
    System.out.println("Height Factor: " + heightFactor);
    // Set the layout.
    itemToScale
        .layoutXProperty()
        .bind(
            minScale
                .multiply(currWidth.divide(widthFactor))
                .subtract(currWidth.divide(widthFactor)));
    itemToScale
        .layoutYProperty()
        .bind(
            minScale
                .multiply(currHeight.divide(heightFactor))
                .subtract(currHeight.divide(heightFactor)));
  }

  /**
   * Scales a list of items, all intended to be fullscreen, around the top left. The items are
   * scaled so that all of them will always be visible and the aspect ratio is preserved.
   *
   * @param items The items to scale.
   */
  public static void scaleFullscreenItemAroundTopLeft(ObservableList<Node> items) {
    for (Node currItem : items) {
      try {
        scaleFullscreenItemAroundTopLeft((Region) currItem);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Scales the desired background to always fill the screen.
   *
   * @param image The ImageView to be scaled
   */
  public static void scaleBackground(ImageView image) {
    double windowCurrWidth = NewSceneLoading.activeWindow.getWidth();
    double windowCurrHeight = NewSceneLoading.activeWindow.getHeight();

    NumberBinding maxScale =
        Bindings.max(
            currWidth.divide(WINDOW_DEFAULT_WIDTH / 2),
            currHeight.divide(WINDOW_DEFAULT_HEIGHT / 2));

    image.scaleXProperty().bind(maxScale);
    image.scaleYProperty().bind(maxScale);

    /*
    image
        .layoutXProperty()
        .bind(maxScale.multiply(windowCurrWidth / 2).subtract(windowCurrWidth / 2));

    image
        .layoutYProperty()
        .bind(maxScale.multiply(windowCurrHeight / 2).subtract(windowCurrHeight / 2));
     */
  }

  public static void scaleBackground(ImageView image, Rectangle gradient) {
    double windowCurrWidth = NewSceneLoading.activeWindow.getWidth();
    double windowCurrHeight = NewSceneLoading.activeWindow.getHeight();

    NumberBinding maxScale =
        Bindings.max(
            currWidth.divide(WINDOW_DEFAULT_WIDTH / 2),
            currHeight.divide(WINDOW_DEFAULT_HEIGHT / 2));

    image.scaleXProperty().bind(maxScale);
    image.scaleYProperty().bind(maxScale);

    gradient.scaleXProperty().bind(maxScale);
    gradient.scaleYProperty().bind(maxScale);
  }
}
