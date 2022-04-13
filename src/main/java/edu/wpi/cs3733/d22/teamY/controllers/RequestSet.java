package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.model.Requestable;
import javafx.scene.layout.Pane;

public class RequestSet {
  private SingularServiceRequestController controller;
  private Pane pane;
  private Requestable request;

  public RequestSet(SingularServiceRequestController controller, Pane pane, Requestable request) {
    this.controller = controller;
    this.pane = pane;
    this.request = request;
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

  public Requestable getRequest() {
    return request;
  }

  public void setRequest(Requestable request) {
    this.request = request;
  }
}
