package edu.wpi.cs3733.d22.teamY.component;

import java.util.List;
import java.util.function.Supplier;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;

public class MapComponent {
  public static class MapImage {
    Supplier<Image> imageSupplier;
    double initialX, initialY, initialScale;

    public MapImage(
        Supplier<Image> imageSupplier, double initialX, double initialY, double initialScale) {
      this.imageSupplier = imageSupplier;
      this.initialX = initialX;
      this.initialY = initialY;
      this.initialScale = initialScale;
    }
  }

  // Root pane is the top level container that resizes with whatever parent it is given
  private final Pane rootPane = new Pane();

  // Map pane can be translated and scaled within the root pane
  private final Pane mapPane = new Pane();

  // Location in pane where a drag was started
  private double dragX = 0;
  private double dragY = 0;

  public MapComponent() {
    rootPane.getChildren().add(mapPane);

    // Pane click handler
    rootPane.setOnMousePressed(
        e -> {
          System.out.println("PRESSED");
          // If right button clicked, handle start of drag
          if (e.getButton() == MouseButton.SECONDARY) {
            dragX = e.getX();
            dragY = e.getY();
            e.consume();
          }
        });

    // Pane drag handler
    rootPane.setOnMouseDragged(
        e -> {
          // If right button is down, handle updating drag
          if (e.isSecondaryButtonDown()) {
            double dx = dragX - e.getX();
            double dy = dragY - e.getY();
            dragX = e.getX();
            dragY = e.getY();

            // Translate all children of the root node
            for (Node n : rootPane.getChildren()) {
              n.setTranslateX(n.getTranslateX() - dx);
              n.setTranslateY(n.getTranslateY() - dy);
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
    if (scale < 0.05) scale = 0.05;
    if (scale > 50) scale = 50;
    mapPane.setScaleX(scale);
    mapPane.setScaleY(scale);

    double f = (scale / oldScale) - 1;

    // Apply the translation resulting from this scaling to all children of the root node
    for (Node n : rootPane.getChildren()) {
      Bounds bounds = n.localToScene(n.getBoundsInLocal());
      double dx = (pivotX - (bounds.getWidth() / 2 + bounds.getMinX()));
      double dy = (pivotY - (bounds.getHeight() / 2 + bounds.getMinY()));

      n.setTranslateX(n.getTranslateX() - f * dx);
      n.setTranslateY(n.getTranslateY() - f * dy);
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

    Image i = image.imageSupplier.get();

    // Update background image
    mapPane.setBackground(
        new Background(
            new BackgroundImage(
                i,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

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
  }
}
