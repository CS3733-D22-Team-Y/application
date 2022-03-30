package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class mapPageController {
  // Base pane for displaying new scenes
  @FXML private Pane mapPane;

  // Loading maps
  @FXML
  void loadGroundFloorMap() {
    loadMap("views/maps/groundFloor.fxml");
  }

  @FXML
  void loadBasement1Map() {
    loadMap("views/maps/basementFloor1.fxml");
  }

  @FXML
  void loadBasement2Map() {
    loadMap("views/maps/basementFloor2.fxml");
  }

  @FXML
  void loadFloor1Map() {
    loadMap("views/maps/floor1.fxml");
  }

  @FXML
  void loadFloor2Map() {
    loadMap("views/maps/floor2.fxml");
  }

  @FXML
  void loadFloor3Map() {
    loadMap("views/maps/floor3.fxml");
  }

  // Generic map loader
  private void loadMap(String toLoad) {

    try {
      Pane newPane = FXMLLoader.load(App.class.getResource(toLoad));
      // Remove loaded scene
      if (mapPane.getChildren().size() != 0) {
        mapPane.getChildren().remove(0);
      }
      // Add new scene
      mapPane.getChildren().add(newPane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
