package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.controllers.requestTypes.RequestControllerUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class RequestMenuController {

  @FXML AnchorPane bgPane;
  @FXML private ToggleButton creatorToggle;
  @FXML AnchorPane sidebarPane;

  @FXML private MFXButton facilitiesButton;

  @FXML
  private Label creator1,
      creator2,
      creator3,
      creator4,
      creator5,
      creator6,
      creator7,
      creator8,
      creator9,
      creator10;

  public RequestMenuController() {}

  @FXML
  void initialize() throws IOException {
    SceneUtil.requests = this;
    NewSceneLoading.loadSidebar(sidebarPane);
    RequestControllerUtil.initialize();
    facilitiesButton.setVisible(false);
  }

  private void loadRequestScreen(int index) {
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(index).setVisible(true);
  }

  void showMainPane() {
    // loadRequestScreen(MAIN_PAGE_INDEX);
  }

  @FXML
  void loadLabReq() {
    // loadRequestScreen(LAB_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/LabResult.fxml");
  }

  @FXML
  void loadMedEquipReq() {
    // loadRequestScreen(MEDICAL_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/MedicalEquipmentRequest.fxml");
  }

  @FXML
  void loadLaundryReq() {
    // loadRequestScreen(LAUNDRY_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/LaundryRequest.fxml");
  }

  @FXML
  void loadMealReq() {
    // loadRequestScreen(MEAL_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/MealRequest.fxml");
  }

  @FXML
  void loadFloralReq() {
    // loadRequestScreen(FLORAL_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/FloralRequest.fxml");
  }

  @FXML
  void loadSecurityReq() {
    // loadRequestScreen(SECURITY_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/SecurityRequest.fxml");
  }

  @FXML
  void loadTranslatorReq() {
    // loadRequestScreen(TRANSLATOR_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/TranslatorRequest.fxml");
  }

  @FXML
  void loadOtherReq() {
    // loadRequestScreen(MISC_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/MiscRequest.fxml");
  }

  @FXML
  void loadSpecialistReq() {
    NewSceneLoading.loadScene("views/requestTypes/SpecialistRequest.fxml");
  }

  @FXML
  void showCreators() {
    boolean state = creatorToggle.isSelected();

    facilitiesButton.setVisible(state);

    System.out.println(state);

    creator1.setVisible(state);
    creator2.setVisible(state);
    creator3.setVisible(state);
    creator4.setVisible(state);
    creator5.setVisible(state);
    creator6.setVisible(state);
    creator7.setVisible(state);
    creator8.setVisible(state);
    creator9.setVisible(state);
    creator10.setVisible(state);
  }

  @FXML
  void loadFacilities() {
    // loadRequestScreen(FACILITIES_RESULTS_INDEX);
    NewSceneLoading.loadScene("views/requestTypes/FacilitiesRequest.fxml");
  }
}
