package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class RequestMenuController {
  protected static final Integer MAIN_PAGE_INDEX = 0;
  protected static final Integer LAB_RESULTS_INDEX = 1;
  protected static final Integer FLORAL_RESULTS_INDEX = 2;
  protected static final Integer LAUNDRY_RESULTS_INDEX = 3;
  protected static final Integer TRANSLATOR_RESULTS_INDEX = 4;
  protected static final Integer SECURITY_RESULTS_INDEX = 5;
  protected static final Integer MEDICAL_RESULTS_INDEX = 6;
  protected static final Integer MEAL_RESULTS_INDEX = 7;

  @FXML AnchorPane bgPane;
  @FXML private ToggleButton creatorToggle;

  @FXML
  private Label creator1, creator2, creator3, creator4, creator5, creator6, creator7, creator8;

  public RequestMenuController() {}

  @FXML
  void initialize() throws IOException {
    SceneUtil.initializePanes(
        bgPane,
        "views/requestTypes/LabResult.fxml",
        "views/requestTypes/FloralRequest.fxml",
        "views/requestTypes/LaundryRequest.fxml",
        "views/requestTypes/TranslatorRequest.fxml",
        "views/requestTypes/SecurityRequest.fxml",
        "views/requestTypes/MedicalEquipmentRequest.fxml",
        "views/requestTypes/MealRequest.fxml");
    SceneUtil.hideAllBackgrounds(bgPane.getChildren());
    SceneUtil.hideAllPanes(bgPane.getChildren());
    showMainPane();
  }

  private void loadRequestScreen(int index) {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(index).setVisible(true);
  }

  private void showMainPane() {
    loadRequestScreen(MAIN_PAGE_INDEX);
  }

  @FXML
  void loadLabReq() {
    loadRequestScreen(LAB_RESULTS_INDEX);
  }

  @FXML
  void loadMedEquipReq() {
    loadRequestScreen(MEDICAL_RESULTS_INDEX);
  }

  @FXML
  void loadLaundryReq() {
    loadRequestScreen(LAUNDRY_RESULTS_INDEX);
  }

  @FXML
  void loadMealReq() {
    loadRequestScreen(MEAL_RESULTS_INDEX);
  }

  @FXML
  void loadFloralReq() {
    loadRequestScreen(FLORAL_RESULTS_INDEX);
  }

  @FXML
  void loadSecurityReq() {
    loadRequestScreen(SECURITY_RESULTS_INDEX);
  }

  @FXML
  void loadTranslatorReq() {
    loadRequestScreen(TRANSLATOR_RESULTS_INDEX);
  }

  @FXML
  void loadOtherReq() {}

  @FXML
  void showCreators() {
    boolean state = creatorToggle.isSelected();
    System.out.println(state);

    creator1.setVisible(state);
    creator2.setVisible(state);
    creator3.setVisible(state);
    creator4.setVisible(state);
    creator5.setVisible(state);
    creator6.setVisible(state);
    creator7.setVisible(state);
    creator8.setVisible(state);
  }
}
