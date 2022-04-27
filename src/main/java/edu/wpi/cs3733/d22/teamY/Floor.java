package edu.wpi.cs3733.d22.teamY;

import javafx.scene.image.Image;

public enum Floor {
  F1("Floor 1", "views/images/floor1.png"),
  F2("Floor 2", "views/images/floor2.png"),
  F3("Floor 3", "views/images/floor3.png"),
  F4("Floor 4", "views/images/floor4.png"),
  F5("Floor 5", "views/images/floor5.png"),
  FL1("Floor L1", "views/images/floor-1.png"),
  FL2("Floor L2", "views/images/floor-2.png");

  private Image image;
  private String name;

  Floor(String name, String path) {
    this.image = new Image(App.class.getResource(path).toString());
    this.name = name;
  }

  public Image getImage() {
    return image;
  }

  public String getName() {
    return name;
  }
}
