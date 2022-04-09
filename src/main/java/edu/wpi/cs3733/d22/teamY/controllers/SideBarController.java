package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SideBarController {
  @FXML AnchorPane mainPane;
  @FXML Pane mainScreenPane;

  // Scaling
  @FXML private VBox bottomSidebarText;
  @FXML private Rectangle sidebarFrame;

  // Hidden rectangles
  @FXML private Rectangle mapHiddenRect;
  @FXML private Rectangle servicesHiddenRect;
  @FXML private Rectangle equipmentHiddenRect;
  @FXML private Rectangle tasksHiddenRect;
  @FXML private Rectangle homeHiddenRect;
  @FXML private Rectangle inboxHiddenRect;
  @FXML private Rectangle profileHiddenRect;
  @FXML private Rectangle logoutHiddenRect;

  private Pane mapPane;
  private Pane servicesPane;
  private Pane tasksPane;
  private Pane settingsPane;

  private AnchorPane mapAnchor;
  private AnchorPane servicesAnchor;
  private AnchorPane tasksAnchor;
  private AnchorPane settingsAnchor;

  @FXML
  void initialize() throws IOException {
    mapHiddenRect.setOpacity(0);
    servicesHiddenRect.setOpacity(0);
    equipmentHiddenRect.setOpacity(0);
    tasksHiddenRect.setOpacity(0);
    homeHiddenRect.setOpacity(0);
    profileHiddenRect.setOpacity(0);
    inboxHiddenRect.setOpacity(0);
    logoutHiddenRect.setOpacity(0);

    mapPane = FXMLLoader.load(App.class.getResource("views/SecondaryMap_TEMPLATE.fxml"));
    servicesPane = FXMLLoader.load(App.class.getResource("views/SubMenu_TEMPLATE.fxml"));
    tasksPane = FXMLLoader.load(App.class.getResource("views/PersonalSettings.fxml"));
    settingsPane = FXMLLoader.load(App.class.getResource("views/PersonalSettings.fxml"));

    mainScreenPane.getChildren().add(mapPane);
    mainScreenPane.getChildren().add(servicesPane);
    mainScreenPane.getChildren().add(tasksPane);
    mainScreenPane.getChildren().add(settingsPane);

    // Set the background to transparent
    SceneUtil.hideAllBackgrounds(mapPane, servicesPane, tasksPane, settingsPane);

    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
  }

  @FXML
  void initializeScale() {
    Scene currScene = bottomSidebarText.getScene();
    bottomSidebarText.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    sidebarFrame.scaleYProperty().bind(currScene.heightProperty().divide(800));
    sidebarFrame.layoutYProperty().bind(sidebarFrame.scaleYProperty().multiply(390).subtract(380));
  }

  @FXML
  void loadMainPage() throws IOException {
    SceneLoading.loadScene("views/Map.fxml");
  }

  @FXML
  void loadViewMap() throws IOException {
    SceneLoading.loadScene("views/Map.fxml");
  }

  @FXML
  void loadCreateServiceRequest() throws IOException {
    SceneLoading.loadScene("views/RequestMenu.fxml");
  }

  @FXML
  void loadLocationTable() throws IOException {
    SceneLoading.loadScene("views/LocTable.fxml");
  }

  @FXML
  void loadMedEquipmentRequests() throws IOException {
    SceneLoading.loadScene("views/ActServReqTable.fxml");
  }

  @FXML
  void loadChangeTheme() throws IOException {
    SceneLoading.loadScene("views/ChangeTheme.fxml");
  }

  @FXML
  void loadPersonalSettings() throws IOException {
    SceneLoading.loadScene("views/PersonalSettings.fxml");
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
  void loadSecondaryMap() throws IOException {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mapPane.setVisible(true);
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    servicesPane.setVisible(true);
  }

  @FXML
  void loadTasks() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    tasksPane.setVisible(true);
  }
}
