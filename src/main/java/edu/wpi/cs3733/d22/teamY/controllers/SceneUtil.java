package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
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

  protected static void hideAllPanes(ObservableList<Node> panes) {
    for (Node thisPane : panes) {
      thisPane.setVisible(false);
    }
  }

  protected static void hideAllBackgrounds(ObservableList<javafx.scene.Node> panes) {
    for (Node currPane : panes) {
      AnchorPane currAnchor = (AnchorPane) currPane.lookup("#mainPane");
      currAnchor.setBackground(Background.EMPTY);
    }
  }

  protected static void initializePanes(Pane mainScreenPane, String... paths) throws IOException {
    for (String currPath : paths) {
      Pane currPane = FXMLLoader.load(App.class.getResource(currPath));
      mainScreenPane.getChildren().add(currPane);
    }
  }
}
