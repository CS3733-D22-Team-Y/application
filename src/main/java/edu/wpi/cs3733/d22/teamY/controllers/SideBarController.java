package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SideBarController {
  // Constant locations
  private static final Integer MAP_LIST_LOCATION = 0;
  private static final Integer SERVICES_LIST_LOCATION = 1;
  private static final Integer EQUIPMENT_LIST_LOCATION = 2;
  private static final Integer TASKS_LIST_LOCATION = 3;
  private static final Integer INBOX_LIST_LOCATION = 4;
  private static final Integer PROFILE_LIST_LOCATION = 5;

  @FXML AnchorPane mainPane;
  @FXML Pane mainScreenPane;

  // Scaling
  @FXML private VBox bottomSidebarText;
  @FXML private Rectangle sidebarFrame;
  @FXML private VBox bottomSidebarHiddenButtons;
  @FXML private VBox bottomSidebarRectangles;

  // Hidden rectangles
  @FXML private Rectangle mapHiddenRect;
  @FXML private Rectangle servicesHiddenRect;
  @FXML private Rectangle equipmentHiddenRect;
  @FXML private Rectangle tasksHiddenRect;
  @FXML private Rectangle homeHiddenRect;
  @FXML private Rectangle inboxHiddenRect;
  @FXML private Rectangle profileHiddenRect;
  @FXML private Rectangle logoutHiddenRect;

  @FXML
  void initialize() throws IOException {
    SceneUtil.removeOpacity(
        mapHiddenRect,
        servicesHiddenRect,
        equipmentHiddenRect,
        tasksHiddenRect,
        homeHiddenRect,
        profileHiddenRect,
        inboxHiddenRect,
        logoutHiddenRect);

    SceneUtil.initializePanes(
        mainScreenPane,
        "views/SecondaryMap_TEMPLATE.fxml",
        "views/SubMenu_TEMPLATE.fxml",
        "views/ActServReqTable.fxml",
        "views/ActiveServiceRequest.fxml",
        "views/ActiveServiceRequest.fxml",
        "views/PersonalSettings.fxml");

    // Set the background to transparent
    SceneUtil.hideAllBackgrounds(mainScreenPane.getChildren());
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
  }

  @FXML
  void initializeScale() {
    Scene currScene = bottomSidebarText.getScene();
    bottomSidebarText.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    bottomSidebarHiddenButtons.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    bottomSidebarRectangles.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    sidebarFrame.scaleYProperty().bind(currScene.heightProperty().divide(800));
    sidebarFrame.layoutYProperty().bind(sidebarFrame.scaleYProperty().multiply(390).subtract(380));
  }

  @FXML
  void killApplication() throws IOException {
    SceneLoading.loadPopup("views/ConfirmClose.fxml", "views/SideBar.fxml");
  }

  @FXML
  void dbSwitcherToggle() {
    // boolean state = dbToggle.isSelected();
    // DBUtils.switchDBType(state);
  }

  @FXML
  void startMapButtonHover() {
    SceneUtil.startHover(mapHiddenRect);
  }

  @FXML
  void endMapButtonHover() {
    SceneUtil.endHover(mapHiddenRect);
  }

  @FXML
  void startServicesButtonHover() {
    SceneUtil.startHover(servicesHiddenRect);
  }

  @FXML
  void endServicesButtonHover() {
    SceneUtil.endHover(servicesHiddenRect);
  }

  @FXML
  void startEquipmentButtonHover() {
    SceneUtil.startHover(equipmentHiddenRect);
  }

  @FXML
  void endEquipmentButtonHover() {
    SceneUtil.endHover(equipmentHiddenRect);
  }

  @FXML
  void startTasksButtonHover() {
    SceneUtil.startHover(tasksHiddenRect);
  }

  @FXML
  void endTasksButtonHover() {
    SceneUtil.endHover(tasksHiddenRect);
  }

  @FXML
  void startHomeButtonHover() {
    SceneUtil.startHover(homeHiddenRect);
  }

  @FXML
  void endHomeButtonHover() {
    SceneUtil.endHover(homeHiddenRect);
  }

  @FXML
  void startInboxButtonHover() {
    SceneUtil.startHover(inboxHiddenRect);
  }

  @FXML
  void endInboxButtonHover() {
    SceneUtil.endHover(inboxHiddenRect);
  }

  @FXML
  void startProfileButtonHover() {
    SceneUtil.startHover(profileHiddenRect);
  }

  @FXML
  void endProfileButtonHover() {
    SceneUtil.endHover(profileHiddenRect);
  }

  @FXML
  void startLogoutButtonHover() {
    SceneUtil.startHover(logoutHiddenRect);
  }

  @FXML
  void endLogoutButtonHover() {
    SceneUtil.endHover(logoutHiddenRect);
  }

  // Placeholder/test
  @FXML
  void loadMap() throws IOException {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(MAP_LIST_LOCATION).setVisible(true);
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(SERVICES_LIST_LOCATION).setVisible(true);
  }

  @FXML
  void loadEquipment() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(EQUIPMENT_LIST_LOCATION).setVisible(true);
  }

  @FXML
  void loadTasks() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(TASKS_LIST_LOCATION).setVisible(true);
  }

  @FXML
  void loadHome() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    // mainScreenPane.getChildren().get(4).setVisible(true);
  }

  @FXML
  void loadInbox() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(INBOX_LIST_LOCATION).setVisible(true);
  }

  @FXML
  void loadProfile() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(PROFILE_LIST_LOCATION).setVisible(true);
  }
  /*
  [0]: map
      [1]: services
      *[2]: equipment
      [3]: active service requests (tasks)
      *[4]: inbox
      *[5]: profile
      [3-4]?: home
   */
}
