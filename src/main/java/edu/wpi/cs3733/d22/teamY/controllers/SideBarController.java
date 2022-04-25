package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.utilTemp.SearchUtil;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SideBarController {

  @FXML AnchorPane mainPane;

  // Scaling
  @FXML private VBox bottomSidebarText;
  @FXML private Rectangle sidebarFrame;
  @FXML private VBox bottomSidebarHiddenButtons;
  @FXML private VBox bottomSidebarRectangles;
  @FXML private VBox menuVBox1;
  @FXML private VBox menuVBox2;
  @FXML private VBox menuVBox3;
  // @FXML private VBox topSidebarText;
  @FXML private Label mapLabel;
  @FXML private Label servicesLabel;
  @FXML private Label equipmentLabel;
  @FXML private Label tasksLabel;
  @FXML private Label homeLabel;
  @FXML private Label inboxLabel;
  @FXML private Label profileLabel;
  @FXML private Label logoutLabel;

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

  // List view stuff
  @FXML private MFXLegacyListView<String> listView;
  private ObservableList keyWords = FXCollections.observableArrayList();

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

    mainPane.setBackground(Background.EMPTY);
    NewSceneLoading.sideBarController = this;

    /*FilteredList filter = new FilteredList(keyWords, s -> true);
    this.searchBar
        .textProperty()
        .addListener(
            obs -> {
              String filterS = this.searchBar.getText();
              if (filterS == null || filterS.length() == 0) {
                filter.setPredicate(s -> true);
              } else {
                filter.setPredicate(s -> ((String) s).contains(filterS.toLowerCase()));
              }
            });*/

    // this.listView.setItems(filter);
    this.listView.setItems(keyWords);
  }

  @FXML
  void initializeScale(Scene currScene) throws IOException {
    // currScene = bottomSidebarText.getScene();
    // Bottom sidebar text
    /*
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

    sidebarBindToLabel(homeHiddenRect, homeButtonHitbox, homeLabel);
    sidebarBindToLabel(inboxHiddenRect, inboxButtonHitbox, inboxLabel);
    sidebarBindToLabel(profileHiddenRect, profileButtonHitbox, profileLabel);
    sidebarBindToLabel(logoutHiddenRect, logoutButtonHitbox, logoutLabel);
     */
  }

  private void resizeMainScreen() {
    /*
    mainScreenPane.scaleYProperty().bind(currScene.heightProperty().subtract(20).divide(780));
    mainScreenPane
        .layoutYProperty()
        .bind(mainScreenPane.scaleYProperty().multiply(390).subtract(380));

    mainScreenPane.scaleXProperty().bind(currScene.widthProperty().subtract(280).divide(920));
    mainScreenPane
        .layoutXProperty()
        .bind(mainScreenPane.scaleXProperty().multiply(460).subtract(190));
     */
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
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    // mainScreenPane.getChildren().get(MAP_LIST_LOCATION).setVisible(true);
    // SceneLoading.loadPopup("views/popups/ReqAbort.fxml", "views/RequestMenu.fxml");
    NewSceneLoading.loadScene("views/Map.fxml");
    setButtonSelected(mapHiddenRect);
  }

  @FXML
  void loadDashboard() throws IOException {
    setButtonSelected(homeHiddenRect);
    NewSceneLoading.loadScene("views/Dashboard.fxml");
  }

  @FXML
  void loadViewServiceRequests() throws IOException {
    // Reset to the main request screen
    /*
    AnchorPane bgPane =
        (AnchorPane) mainScreenPane.getChildren().get(SERVICES_LIST_LOCATION).lookup("#bgPane");
    SceneUtil.hideAllPanes(bgPane.getChildren());
    bgPane.getChildren().get(RequestMenuController.MAIN_PAGE_INDEX).setVisible(true);
    // Main code
    SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    mainScreenPane.getChildren().get(SERVICES_LIST_LOCATION).setVisible(true);
     */
    setButtonSelected(servicesHiddenRect);
    NewSceneLoading.loadScene("views/RequestMenu.fxml");
  }

  @FXML
  void loadEquipment() {
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    // mainScreenPane.getChildren().get(EQUIPMENT_LIST_LOCATION).setVisible(true);
    NewSceneLoading.loadScene("views/MedEquipTable.fxml");
    setButtonSelected(equipmentHiddenRect);
  }

  @FXML
  void loadTasks() {
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    // mainScreenPane.getChildren().get(TASKS_LIST_LOCATION).setVisible(true);
    setButtonSelected(tasksHiddenRect);
    NewSceneLoading.loadScene("views/ActiveServiceRequest.fxml");
  }

  @FXML
  void loadHome() throws IOException {
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    setButtonSelected(homeHiddenRect);
    NewSceneLoading.reloadScene("views/Dashboard.fxml");
    NewSceneLoading.loadScene("views/Dashboard.fxml");
  }

  @FXML
  void loadInbox() throws IOException {
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    // mainScreenPane.getChildren().get(INBOX_LIST_LOCATION).setVisible(true);
    setButtonSelected(inboxHiddenRect);
    NewSceneLoading.addScene("views/ChatSelector.fxml");
    NewSceneLoading.loadScene("views/ChatSelector.fxml");
  }

  @FXML
  void loadProfile() {
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
    // mainScreenPane.getChildren().get(PROFILE_LIST_LOCATION).setVisible(true);
    setButtonSelected(profileHiddenRect);
    NewSceneLoading.loadScene("views/PersonalSettings.fxml");
  }

  @FXML
  void loadHome_noUpdateButton() {
    // SceneUtil.hideAllPanes(mainScreenPane.getChildren());
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
    String goTo = getDirectory(listView.getSelectionModel().getSelectedItem());
    SceneLoading.loadScene(goTo);
  }

  @FXML
  public void getSearchItems() {
    String entry = this.searchBar.getText();
    entry = entry.toLowerCase(Locale.ROOT);

    String[] pages = {
      "Facilities Services",
      "Floral Services",
      "Lab Results",
      "Laundry Services",
      "Maintenance Services",
      "Meal Services",
      "Medical Equipment Services",
      "Miscellaneous Services",
      "Security Services",
      "Specialist Services",
      "Translator Services",
      "Map",
      /*"Inbox",*/
      "Personal Settings",
      "Request Menu"
    };

    if (entry.length() != 0 || entry != null) {
      populateListView(entry, pages);
    } else {
      this.listView.setVisible(false);
    }
  }

  public void populateListView(String entry, String[] pagesArray) {
    ArrayList<String> sorted = SearchUtil.search(entry, pagesArray);
    this.keyWords.clear();
    this.keyWords.addAll(sorted.get(0), sorted.get(1), sorted.get(2));
    this.listView.setVisible(true);
    this.listView.getSelectionModel().selectFirst();
  }

  public String getDirectory(String page) {
    page = page.replaceAll("Services", "Request");
    page = page.replaceAll("Results", "Result");
    page = page.replaceAll(" ", "");
    page = page.replaceAll("ellaneous", "");
    // page = page.replaceAll("Inbox", "")
    page = "views/" + page + ".fxml";
    return page;
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
