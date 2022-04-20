package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import javafx.scene.layout.Pane;

public class RequestSet {
  private SingularServiceRequestController controller;
  private Pane pane;
  private ServiceRequest request;

  public RequestSet(
      SingularServiceRequestController controller, Pane pane, ServiceRequest request) {
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

  public ServiceRequest getRequest() {
    return request;
  }

  public void setRequest(ServiceRequest request) {
    this.request = request;
  }
}
