package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class RequestMenuController {
  protected static final Integer MAIN_PAGE_INDEX = 0;
  protected static final Integer LAB_RESULTS_INDEX = 1;
  protected static final Integer FLORAL_RESULTS_INDEX = 2;
  protected static final Integer LAUNDRY_RESULTS_INDEX = 3;
  protected static final Integer TRANSLATOR_RESULTS_INDEX = 4;

  @FXML AnchorPane bgPane;

  public RequestMenuController() {}

  @FXML
  void initialize() throws IOException {
    SceneUtil.initializePanes(
        bgPane,
        "views/requestTypes/LabResult.fxml",
        "views/requestTypes/FloralRequest.fxml",
        "views/requestTypes/LaundryRequest.fxml",
        "views/requestTypes/TranslatorRequest.fxml");
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
  void loadLaundryReq() {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(LAUNDRY_RESULTS_INDEX).setVisible(true);
  }

  @FXML
  void loadMealReq() {}

  @FXML
  void loadFloralReq() {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(FLORAL_RESULTS_INDEX).setVisible(true);
  }

  @FXML
  void loadSecurityReq() {}

  @FXML
  void loadTranslatorReq() {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(TRANSLATOR_RESULTS_INDEX).setVisible(true);
  }

  @FXML
  void loadOtherReq() {}
}
