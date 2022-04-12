package edu.wpi.cs3733.d22.teamY.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SideBarController {
  // Constant locations
  protected static final Integer MAP_LIST_LOCATION = 0;
  protected static final Integer SERVICES_LIST_LOCATION = 1;
  protected static final Integer EQUIPMENT_LIST_LOCATION = 2;
  protected static final Integer TASKS_LIST_LOCATION = 3;
  protected static final Integer INBOX_LIST_LOCATION = 4;
  protected static final Integer PROFILE_LIST_LOCATION = 5;

  @FXML AnchorPane mainPane;
  @FXML Pane mainScreenPane;

  // Scaling
  @FXML private VBox bottomSidebarText;
  @FXML private Rectangle sidebarFrame;
  @FXML private VBox bottomSidebarHiddenButtons;
  @FXML private VBox bottomSidebarRectangles;
  // @FXML private VBox topSidebarText;
  @FXML private Label mapLabel;
  @FXML private Label servicesLabel;
  @FXML private Label equipmentLabel;
  @FXML private Label tasksLabel;

  // Hidden rectangles
  @FXML private Rectangle mapHiddenRect;
  @FXML private Rectangle servicesHiddenRect;
  @FXML private Rectangle equipmentHiddenRect;
  @FXML private Rectangle tasksHiddenRect;
  @FXML private Rectangle homeHiddenRect;
  @FXML private Rectangle inboxHiddenRect;
  @FXML private Rectangle profileHiddenRect;
  @FXML private Rectangle logoutHiddenRect;
  // Hitboxes
  @FXML private Rectangle mapButtonHitbox;
  @FXML private Rectangle servicesButtonHitbox;
  @FXML private Rectangle equipmentButtonHitbox;
  @FXML private Rectangle tasksButtonHitbox;
  @FXML private Rectangle homeButtonHitbox;
  @FXML private Rectangle inboxButtonHitbox;
  @FXML private Rectangle profileButtonHitbox;
  @FXML private Rectangle logoutButtonHitbox;

  Scene currScene;

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
        "views/Map.fxml",
        "views/RequestMenu.fxml",
        "views/MedEquipTable.fxml",
        "views/ActServReqTable.fxml",
        "views/ActiveServiceRequest.fxml",
        "views/PersonalSettings.fxml");
    SceneUtil.hideAllBackgrounds(mainScreenPane.getChildren());
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
  }

  public void reloadSidebar() throws IOException {
    mainScreenPane.getChildren().clear();
    SceneUtil.initializePanes(
        mainScreenPane,
        "views/Map.fxml",
        "views/RequestMenu.fxml",
        "views/ActServReqTable.fxml",
        "views/ActiveServiceRequest.fxml",
        "views/ActiveServiceRequest.fxml",
        "views/PersonalSettings.fxml");
    SceneUtil.hideAllBackgrounds(mainScreenPane.getChildren());
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(SERVICES_LIST_LOCATION).setVisible(true);
  }

  @FXML
  void initializeScale() throws IOException {

    currScene = bottomSidebarText.getScene();
    // Bottom sidebar text
    bottomSidebarText.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    bottomSidebarHiddenButtons.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    bottomSidebarRectangles.layoutYProperty().bind(currScene.heightProperty().subtract(200));
    // Sidebar Rectangle
    sidebarFrame.scaleYProperty().bind(currScene.heightProperty().subtract(20).divide(780));
    sidebarFrame.layoutYProperty().bind(sidebarFrame.scaleYProperty().multiply(390).subtract(380));
    // Top sidebar text
    // System.out.println(currScene.heightProperty());
    // Sidebar label adjusting
    sidebarTopDynamicScale(mapLabel, 150, 50);
    sidebarTopDynamicScale(servicesLabel, 101, 40.5);
    sidebarTopDynamicScale(equipmentLabel, 52, 31);
    sidebarTopDynamicScale(tasksLabel, 3, 21.5);
    // Top sidebar buttons
    sidebarBindToHeight(
        mapHiddenRect,
        servicesHiddenRect,
        equipmentHiddenRect,
        tasksHiddenRect,
        mapButtonHitbox,
        servicesButtonHitbox,
        equipmentButtonHitbox,
        tasksButtonHitbox);

    sidebarBindToLabel(mapHiddenRect, mapButtonHitbox, mapLabel);
    sidebarBindToLabel(servicesHiddenRect, servicesButtonHitbox, servicesLabel);
    sidebarBindToLabel(equipmentHiddenRect, equipmentButtonHitbox, equipmentLabel);
    sidebarBindToLabel(tasksHiddenRect, tasksButtonHitbox, tasksLabel);
  }

  private void resizeMainScreen() {
    mainScreenPane.scaleYProperty().bind(currScene.heightProperty().subtract(20).divide(780));
    mainScreenPane
        .layoutYProperty()
        .bind(mainScreenPane.scaleYProperty().multiply(390).subtract(380));

    mainScreenPane.scaleXProperty().bind(currScene.widthProperty().subtract(280).divide(920));
    mainScreenPane
        .layoutXProperty()
        .bind(mainScreenPane.scaleXProperty().multiply(460).subtract(190));
  }

  private void sidebarBindToHeight(Shape... shapes) {
    resizeMainScreen();
    Scene currScene = shapes[0].getScene();
    for (Shape currShape : shapes) {
      currShape.scaleYProperty().bind(currScene.heightProperty().divide(800));
    }
  }

  private void sidebarBindToLabel(Rectangle hiddenRect, Rectangle buttonHitbox, Label label) {
    hiddenRect.layoutYProperty().bind(label.layoutYProperty());
    buttonHitbox.layoutYProperty().bind(label.layoutYProperty());
  }

  private void sidebarTopDynamicScale(
      Label label, double multiplyProperty, double subtractProperty) {
    Scene currScene = label.getScene();
    label
        .layoutYProperty()
        .bind(
            currScene
                .heightProperty()
                .divide(4)
                .subtract(sidebarFrame.scaleYProperty().multiply(multiplyProperty))
                .subtract(subtractProperty));
  }

  @FXML
  void killApplication() throws IOException {
    SceneLoading.loadPopup("views/popups/ConfirmClose.fxml", "views/SideBar.fxml");
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

  private void setButtonSelected(Shape toSelect) {
    SceneUtil.removeSelection(
        mapHiddenRect,
        servicesHiddenRect,
        equipmentHiddenRect,
        tasksHiddenRect,
        homeHiddenRect,
        profileHiddenRect,
        inboxHiddenRect,
        logoutHiddenRect);
    SceneUtil.setSelection(toSelect);
  }

  @FXML
  void loadMap() throws IOException {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(MAP_LIST_LOCATION).setVisible(true);
    setButtonSelected(mapHiddenRect);
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    // Reset to the main request screen
    AnchorPane bgPane =
        (AnchorPane) mainScreenPane.getChildren().get(SERVICES_LIST_LOCATION).lookup("#bgPane");
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(RequestMenuController.MAIN_PAGE_INDEX).setVisible(true);
    // Main code
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(SERVICES_LIST_LOCATION).setVisible(true);
    setButtonSelected(servicesHiddenRect);
  }

  @FXML
  void loadEquipment() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(EQUIPMENT_LIST_LOCATION).setVisible(true);
    setButtonSelected(equipmentHiddenRect);
  }

  @FXML
  void loadTasks() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(TASKS_LIST_LOCATION).setVisible(true);
    setButtonSelected(tasksHiddenRect);
  }

  @FXML
  void loadHome() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    setButtonSelected(homeHiddenRect);
  }

  @FXML
  void loadInbox() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(INBOX_LIST_LOCATION).setVisible(true);
    setButtonSelected(inboxHiddenRect);
  }

  @FXML
  void loadProfile() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(PROFILE_LIST_LOCATION).setVisible(true);
    setButtonSelected(profileHiddenRect);
  }

  @FXML
  void loadHome_noUpdateButton() {
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    SceneUtil.removeSelection(
        mapHiddenRect,
        servicesHiddenRect,
        equipmentHiddenRect,
        tasksHiddenRect,
        homeHiddenRect,
        profileHiddenRect,
        inboxHiddenRect,
        logoutHiddenRect);
  }

  @FXML private TextField searchBar;
  /**
   * Performs a search given key words these words are processed through a series of RegEx's and
   * filters to fit a standardized form these strings are then appended with other key words in
   * order to switch to that respective page TODO: add error handling, levenshtein distance, error
   * page, history page?
   */
  @FXML
  public void doSearch() throws IOException {
    String entry = searchBar.getText();

    // Valid pages: !!! NEEDS TO UPDATE EVERYTIME NEW PAGE IS ADDED !!!
    String[] pages = {
      "floral",
      "lab",
      "laundry",
      "meal",
      "medical",
      "security",
      "map",
      "settings",
      "menu",
      "request",
      "profile",
      "translator",
      "tasks"
    };

    // The following lines of code filters and processes search request
    entry = entry.toLowerCase();
    entry.replaceAll(" ", "");
    entry.replaceAll("active", "table");
    entry.replaceAll("current", "table");
    entry.replaceAll("equipment", "medical");

    // Will see if this request exists
    String isValid = getPage(entry, pages);
    if (isValid == "ERROR") {
      // TODO: Levenshtein distance computations
    } else {
      entry = isValid;
    }
  }

  /** Helper method for doSearch Will determine if a valid page exists */
  public String getPage(String entry, String[] pages) throws IOException {
    // for (int i = 0; i < pages.length; i++) {
    // if (entry.contains(pages[i])) {
    // i = pages.length;
    if (entry.contains("medical")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadMedEquipReq();
    } else if (entry.contains("laundry")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadLaundryReq();
    } else if (entry.contains("meal")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadMealReq();
    } else if (entry.contains("security")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadSecurityReq();
    } else if (entry.contains("floral")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadFloralReq();
    } else if (entry.contains("translator")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadTranslatorReq();
    } else if (entry.contains("settings") || entry.contains("profile")) {
      loadProfile();
    } else if (entry.contains("security")) {
      loadViewServiceRequests();
      SceneUtil.requests.loadSecurityReq();
    } else if (entry.contains("map")) {
      loadMap();
    } else if (entry.contains("request")) {
      loadViewServiceRequests();
      SceneUtil.requests.showMainPane();
    } else if (entry.contains("equipment")) {
      loadEquipment();
    } else if (entry.contains("tasks")) {
      loadTasks();
    }
    // } else {
    //  entry = entry.substring(0).toUpperCase();
    //  entry = entry + "Request.fxml";
    // }
    // entry = "views/" + entry;
    // }
    return "";
  }
}
