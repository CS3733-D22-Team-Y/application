package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class RequestMenuController {
  private static final Integer MAIN_PAGE_INDEX = 0;
  private static final Integer LAB_RESULTS_INDEX = 1;

  @FXML AnchorPane bgPane;

  public RequestMenuController() {}

  @FXML
  void initialize() throws IOException {
    SceneUtil.initializePanes(bgPane, "views/requestTypes/LabResult.fxml");
    System.out.println(bgPane.getChildren());
    SceneUtil.hideAllBackgrounds(bgPane.getChildren());
    SceneUtil.hideAllPanes(bgPane.getChildren());
    showMainPane();
  }

  private void showMainPane() {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(MAIN_PAGE_INDEX).setVisible(true);
  }

  @FXML
  void loadLabReq() {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(LAB_RESULTS_INDEX).setVisible(true);
  }

  @FXML
  void loadMedEquipReq() {}

  @FXML
  void loadLaundryReq() {}

  @FXML
  void loadMealReq() {}

  @FXML
  void loadFloralReq() {}

  @FXML
  void loadSecurityReq() {}
}
