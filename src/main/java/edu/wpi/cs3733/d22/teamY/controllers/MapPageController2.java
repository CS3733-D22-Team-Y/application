package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.Floor;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class MapPageController2 {

  @FXML ImageView mapArea;
  @FXML Pane mapClickArea;
  private Floor currFloor;

  private double xStart = 0;
  private double yStart = 0;
  private boolean dragging = false;

  public void initialize() {
    mapClickArea.setOnMouseDragged(this::drag);
    mapClickArea.setOnMouseReleased(this::release);
    mapClickArea.setOnMousePressed(this::pressed);

    mapClickArea.setOnScroll(this::scroll);

    setFloor(Floor.F1);
    updateMap();
  }

  private void pressed(MouseEvent mouseEvent) {
    xStart = mouseEvent.getX();
    yStart = mouseEvent.getY();
  }

  private void release(MouseEvent mouseEvent) {
    System.out.println("released");
    System.out.println(Camera.toMapCoords(mouseEvent.getX(), mouseEvent.getY()));
  }

  private void scroll(ScrollEvent scrollEvent) {
    int delta = (int) scrollEvent.getDeltaY();
    Camera.changeScale(delta > 0 ? 1 : -1);
    updateMap();
  }

  private void drag(MouseEvent event) {
    double dx = event.getX() - xStart;
    double dy = event.getY() - yStart;
    Camera.moveScreenCoords(dx, dy);
    System.out.println(dx + " " + dy);
    updateMap();
    xStart = event.getX();
    yStart = event.getY();
  }

  public void setFloor(Floor floor) {
    currFloor = floor;
    mapArea.setImage(currFloor.getImage());
  }

  private void updateMap() {
    mapArea.setTranslateX(Camera.xOff);
    mapArea.setTranslateY(Camera.yOff);
    mapArea.setScaleX(Camera.scale);
    mapArea.setScaleY(Camera.scale);
    System.out.println(Camera.xOff + " " + Camera.yOff + " " + Camera.scale);
  }
}
