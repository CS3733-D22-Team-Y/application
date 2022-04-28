package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.utilTemp.SearchUtil;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SideBarController {

  @FXML AnchorPane mainPane;

  // Scaling
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

  // List view stuff
  @FXML private MFXLegacyListView<String> listView;
  private ObservableList keyWords = FXCollections.observableArrayList();

  // About Us stuff
  @FXML private Label aboutUsButton;
  AboutUsController About = new AboutUsController();

  Scene currScene;
  ArrayList<String> searchContent = new ArrayList<String>();
  String[] pages;

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

    this.listView.setItems(keyWords);

    pages =
        new String[] {
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

    searchContent.addAll(List.of(pages));
  }

  @FXML
  void initializeScale(Scene currScene) throws IOException {}

  private void resizeMainScreen() {}

  @FXML
  void killApplication() throws IOException {
    SceneLoading.loadPopup("views/popups/ConfirmClose.fxml", "views/SideBar.fxml");
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
    NewSceneLoading.loadScene("views/RequestMenu.fxml");
    setButtonSelected(servicesHiddenRect);
  }

  @FXML
  void loadEquipment() throws IOException {
    NewSceneLoading.reloadScene("views/MedEquipTable.fxml");
    NewSceneLoading.loadScene("views/MedEquipTable.fxml");
    setButtonSelected(equipmentHiddenRect);
  }

  @FXML
  void loadTasks() {
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
    setButtonSelected(inboxHiddenRect);
    NewSceneLoading.addScene("views/ChatSelector.fxml");
    NewSceneLoading.loadScene("views/ChatSelector.fxml");
  }

  @FXML
  void loadProfile() {
    setButtonSelected(profileHiddenRect);
    NewSceneLoading.loadScene("views/PersonalSettings.fxml");
  }

  @FXML
  void loadHome_noUpdateButton() {
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

  @FXML
  private void openAboutUs() throws IOException {
    SceneLoading.loadPopup("views/popups/AboutUs.fxml", "views/SideBar.fxml");
  }

  @FXML
  private void openCredits() throws IOException {
    SceneLoading.loadPopup("views/popups/Credits.fxml", "views/SideBar.fxml");
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
    NewSceneLoading.reloadScene(goTo);
    NewSceneLoading.loadScene(goTo);
    this.searchBar.setText("");
    this.listView.setVisible(false);
  }

  @FXML
  public void getSearchItems() {

    String entry = this.searchBar.getText();
    entry = entry.toLowerCase(Locale.ROOT);

    if (entry.length() != 0 || entry != null) {
      populateListView(entry, pages);
    } else {
      this.listView.setVisible(false);
    }
  }

  public void populateListView(String entry, String[] pagesArray) {
    System.out.println("----------------------------------------" + entry);
    searchContent.sort(
        Comparator.comparingInt(o -> SearchUtil.getMatchScore(o.toLowerCase(), entry)));
    this.keyWords.clear();
    this.keyWords.addAll(searchContent.get(0), searchContent.get(1), searchContent.get(2));
    this.listView.setVisible(true);
    this.listView.getSelectionModel().selectFirst();
  }

  public String getDirectory(String page) {
    page = page.replaceAll("Services", "Request");
    page = page.replaceAll("Results", "Result");
    page = page.replaceAll(" ", "");
    page = page.replaceAll("ellaneous", "");

    if (page.toLowerCase().contains("map") || page.toLowerCase().contains("menu")) {
      page = "views/" + page + ".fxml";
    } else {
      page = "views/requestTypes/" + page + ".fxml";
    }
    return page;
  }
}
