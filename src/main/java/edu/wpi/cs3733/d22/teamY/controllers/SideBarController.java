package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SideBarController {
  @FXML AnchorPane mainPane;
  @FXML private Label nameLabel;
  @FXML private Label accessLevel;
  @FXML private ToggleButton dbToggle;
  @FXML Pane mainScreenPane;

  // Scaling
  @FXML private VBox bottomSidebarText;
  @FXML private Rectangle sidebarFrame;

  // Hidden rectangles
  @FXML private Rectangle mapHiddenRect;
  @FXML private Rectangle servicesHiddenRect;
  @FXML private Rectangle equipmentHiddenRect;

  private Pane mapPane;
  private Pane servicesPane;

  private AnchorPane mapAnchor;
  private AnchorPane servicesAnchor;
  ArrayList<Pane> allPanes;

  @FXML
  void initialize() throws IOException {
    mapHiddenRect.setOpacity(0);
    servicesHiddenRect.setOpacity(0);
    equipmentHiddenRect.setOpacity(0);

    mapPane = FXMLLoader.load(App.class.getResource("views/SecondaryMap_TEMPLATE.fxml"));
    servicesPane = FXMLLoader.load(App.class.getResource("views/SubMenu_TEMPLATE.fxml"));

    mainScreenPane.getChildren().add(mapPane);
    mainScreenPane.getChildren().add(servicesPane);

    //Set the background to transparent
    mapAnchor = (AnchorPane) mapPane.lookup("#mainPane");
    mapAnchor.setBackground(Background.EMPTY);
    servicesAnchor = (AnchorPane) servicesPane.lookup("#mainPane");
    servicesAnchor.setBackground(Background.EMPTY);

    allPanes.add(mapPane);
    allPanes.add(servicesPane);
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
    boolean state = dbToggle.isSelected();
    DBUtils.switchDBType(state);
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

  // Placeholder/test
  @FXML
  void loadSecondaryMap() throws IOException {
    // Pane paneToLoad = FXMLLoader.load(App.class.getResource("views/SecondaryMap_TEMPLATE.fxml"));
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mapPane.setVisible(true);
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    // SceneLoading.loadScene("views/SubMenu_TEMPLATE.fxml");
    // Pane paneToLoad = FXMLLoader.load(App.class.getResource("views/SubMenu_TEMPLATE.fxml"));
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    servicesPane.setVisible(true);
  }
}
