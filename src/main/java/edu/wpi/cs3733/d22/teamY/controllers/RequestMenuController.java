package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.controllers.requestTypes.RequestControllerUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class RequestMenuController implements IController {

  @FXML AnchorPane bgPane;
  @FXML private ToggleButton creatorToggle;
  @FXML AnchorPane sidebarPane;

  @FXML private MFXButton facilitiesButton;

  @FXML
  private Label creator01,
      creator02,
      creator03,
      creator04,
      creator05,
      creator06,
      creator07,
      creator08,
      creator09,
      creator10,
      creator11;

  public RequestMenuController() {}

  @FXML
  void initialize() throws IOException {
    SceneUtil.requests = this;
    NewSceneLoading.loadSidebar(sidebarPane);
    RequestControllerUtil.initialize();

    creator01.setVisible(false);
    creator02.setVisible(false);
    creator03.setVisible(false);
    creator04.setVisible(false);
    creator05.setVisible(false);
    creator06.setVisible(false);
    creator07.setVisible(false);
    creator08.setVisible(false);
    creator09.setVisible(false);
    creator10.setVisible(false);
    creator11.setVisible(false);
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
  void loadMaintenanceReq() {
    NewSceneLoading.loadScene("views/requestTypes/MaintenanceRequest.fxml");
  }

  @FXML
  void loadFacilitiesReq() {
    NewSceneLoading.loadScene("views/requestTypes/FacilitiesRequest.fxml");
  }

  @FXML
  void showCreators() {
    boolean state = creatorToggle.isSelected();
    System.out.println(state);

    creator01.setVisible(state);
    creator02.setVisible(state);
    creator03.setVisible(state);
    creator04.setVisible(state);
    creator05.setVisible(state);
    creator06.setVisible(state);
    creator07.setVisible(state);
    creator08.setVisible(state);
    creator09.setVisible(state);
    creator10.setVisible(state);
    creator11.setVisible(state);
  }

  @FXML
  void openHelp() throws IOException {
    SceneLoading.loadPopup("views/popups/ServiceHelp.fxml", "views/SideBar.fxml");
  }

  @FXML
  void loadOtherTeams() throws IOException {
    SceneLoading.loadPopup("views/popups/OtherTeamAPIs.fxml", "views/SideBar.fxml");
  }

  public static void printResults(Process process) throws IOException {
    System.out.println("Process Print Results");
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line = "";
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleFullscreenItemAroundTopLeft(bgPane);
  }
}
