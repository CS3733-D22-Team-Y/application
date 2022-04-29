package edu.wpi.cs3733.d22.teamY.component;

import edu.wpi.cs3733.d22.teamY.App;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;

public class MapComponent {
  public double universalScale = 0.3;

  private static boolean isDraggingPin = false;

  public static class MapImage {
    Image image, image2;
    double initialX, initialY, initialScale;

    public MapImage(Image image, double initialX, double initialY, double initialScale) {
      this.image = image;
      this.initialX = initialX;
      this.initialY = initialY;
      this.initialScale = initialScale;
      this.image2 = new Image(App.class.getResource("views/images/empty.png").toString());
    }

    public MapImage(
        Image image, Image image2, double initialX, double initialY, double initialScale) {
      this.image = image;
      this.initialX = initialX;
      this.initialY = initialY;
      this.initialScale = initialScale;
      this.image2 = image2;
    }
  }

  // Root pane is the top level container that resizes with whatever parent it is given
  private final Pane rootPane = new Pane();

  // Map pane can be translated and scaled within the root pane
  public final Pane mapPane = new Pane();

  // Location in pane where a drag was started
  private double dragX = 0;
  private double dragY = 0;

  public MapComponent() {
    rootPane.getChildren().add(mapPane);

    // Pane click handler
    rootPane.setOnMousePressed(
        e -> {
          // If right button clicked, handle start of drag
          if (e.getButton() == MouseButton.PRIMARY) {
            dragX = e.getX();
            dragY = e.getY();
            e.consume();
          }
        });

    // Pane drag handler
    rootPane.setOnMouseDragged(
        e -> {
          // If right button is down, handle updating drag
          if (e.isPrimaryButtonDown() && !isDraggingPin) {
            double dx = dragX - e.getX();
            double dy = dragY - e.getY();
            dragX = e.getX();
            dragY = e.getY();

            // Translate all children of the root node
            for (Node n : rootPane.getChildren()) {
              if ((7484 * universalScale - 723) > n.getTranslateX() - dx
                  && n.getTranslateX() - dx > 0) {
                n.setTranslateX(n.getTranslateX() - dx);
              }
              System.out.println("TRANSLATE Y: " + n.getTranslateY());
              if ((3987 * universalScale - 271) > n.getTranslateY() - dy
                  && n.getTranslateY() - dy > 0) {
                n.setTranslateY(n.getTranslateY() - dy);
              }
            }

            e.consume();
          }
        });

    rootPane.setOnScroll(
        e -> handleZoom(Math.pow(1.01, e.getDeltaY()), e.getSceneX(), e.getSceneY()));
  }

  private void handleZoom(double delta, double pivotX, double pivotY) {
    double oldScale = mapPane.getScaleX();
    double scale = oldScale * delta;
    if (scale < 0.1) scale = 0.1;
    if (scale > 1) scale = 1;
    // System.out.println("ZOOM: " + scale);

    double f = (scale / oldScale) - 1;

    // Apply the translation resulting from this scaling to all children of the root node
    for (Node n : rootPane.getChildren()) {
      Bounds bounds = n.localToScene(n.getBoundsInLocal());
      double dx = (pivotX - (bounds.getWidth() / 2 + bounds.getMinX()));
      double dy = (pivotY - (bounds.getHeight() / 2 + bounds.getMinY()));
      if (n.getTranslateX() - f * dx > 0
          && n.getTranslateX() - f * dx > 0
          && (7484 * scale - 723) > n.getTranslateX() - f * dx
          && (3987 * scale - 271) > n.getTranslateY() - f * dy) {
        n.setTranslateX(n.getTranslateX() - f * dx);
        n.setTranslateY(n.getTranslateY() - f * dy);
        mapPane.setScaleX(scale);
        mapPane.setScaleY(scale);
        universalScale = scale;
      }
    }
  }

  /** @return The main map pane that can be displayed in the parent component */
  public Pane getRootPane() {
    return rootPane;
  }

  /**
   * Updates the contents of the pane
   *
   * @param image The new map image to display
   * @param elements The new set of elements to display on the map
   * @param attachedElements The new set of attached elements (elements that scale with the map) to
   *     display
   */
  public void setContent(MapImage image, List<Node> elements, List<Node> attachedElements) {
    // Get all elements from the root that are not the map pane
    List<Node> toRemove = rootPane.getChildren().filtered(e -> e != mapPane);
    // Remove the elements from the root
    rootPane.getChildren().clear();
    rootPane.getChildren().add(mapPane);

    // Remove all elements from the map pane (attached elements)
    mapPane.getChildren().clear();

    Image i = image.image;
    Image i2 = image.image2;

    // Update background image
    mapPane.setBackground(
        new Background(
            new BackgroundImage(
                i,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    ImageView imageTwo = new ImageView(i2);
    imageTwo.setLayoutX(4090);
    imageTwo.setLayoutY(1753);
    mapPane.getChildren().add(imageTwo);

    // Resize to image and reset translation and scale
    mapPane.setScaleX(image.initialScale);
    mapPane.setScaleY(image.initialScale);
    mapPane.setTranslateX(image.initialX);
    mapPane.setTranslateY(image.initialY);
    elements.forEach(
        (e) -> {
          e.setTranslateX(image.initialX);
          e.setTranslateY(image.initialY);
        });
    attachedElements.forEach(
        (e) -> {
          e.setTranslateX(image.initialX);
          e.setTranslateY(image.initialY);
        });
    mapPane.setPrefSize(i.getWidth(), i.getHeight());

    // Add all children
    rootPane.getChildren().addAll(elements);
    mapPane.getChildren().addAll(attachedElements);

    for (Node n : rootPane.getChildren()) {
      n.setTranslateX(n.getTranslateX() + 570);
      n.setTranslateY(n.getTranslateY() + 700);
    }
    mapPane.setScaleX(0.3);
    mapPane.setScaleY(0.3);
  }

  public Pane getMapPane() {
    return mapPane;
  }

  public static boolean getIsDraggingPin() {
    return isDraggingPin;
  }

  public static void setIsDraggingPin(boolean newValue) {
    isDraggingPin = newValue;
  }
}
