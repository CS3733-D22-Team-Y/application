package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.model.Requestable;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class SingularServiceRequestController {
  @FXML private Group colorGizmo;

  public void populateFromRequestable(Requestable req) {}

  @FXML
  void displayDetailedInfo() {
    SceneUtil.serviceRequests.fillInfoField("Tada!!!!!!!\n\nYou clicked a button. Big whoop");
  }

  public void setColor(Color color) {
    for (Node child : colorGizmo.getChildren()) {
      ((Shape) child).setFill(color);
    }
  }

  public static Color priorityColor(int priority) {
    int p = Math.min(Math.max(priority, 0), 10);
    return Color.hsb((10 - p) * 12.8, 0.36, 0.98);
  }
}
