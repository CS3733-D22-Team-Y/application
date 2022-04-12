package edu.wpi.cs3733.d22.teamY.controllers;

import javafx.scene.layout.Pane;

public class ReqControllerPanePair {
  SingularServiceRequestController controller;
  Pane pane;

  public ReqControllerPanePair(SingularServiceRequestController controller, Pane pane) {
    this.controller = controller;
    this.pane = pane;
  }

  public SingularServiceRequestController getController() {
    return controller;
  }

  public void setController(SingularServiceRequestController controller) {
    this.controller = controller;
  }

  public Pane getPane() {
    return pane;
  }

  public void setPane(Pane pane) {
    this.pane = pane;
  }
}
