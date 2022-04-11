package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBUtils;
import java.io.IOException;
import java.util.Locale;

import edu.wpi.cs3733.d22.teamY.utilTemp.SearchUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class SideBarController {
  @FXML AnchorPane mainPane;
  @FXML private Label nameLabel;
  @FXML private Label accessLevel;
  @FXML private ToggleButton dbToggle;
  @FXML private TextField searchBar;

  @FXML
  void initialize() {
    accessLevel.setText("Access Level: " + PersonalSettings.currentEmployee.getAccessLevel());
    nameLabel.setText(PersonalSettings.currentEmployee.getName());
    mainPane.setVisible(true);
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
  void loadViewServiceRequests() throws IOException {
    SceneLoading.loadScene("views/ActiveServiceRequest.fxml");
  }

  @FXML
  void loadLaundryRequest() throws IOException {
    SceneLoading.loadScene("views/requestTypes/LaundryRequest.fxml");
  }

  @FXML
  void loadMedicalEquipment() throws IOException {
    SceneLoading.loadScene("views/requestTypes/MedicalEquipmentRequest.fxml");
  }

  @FXML
  void loadMealDelivery() throws IOException {
    SceneLoading.loadScene("views/requestTypes/MealRequest.fxml");
  }

  @FXML
  void loadFloralDelivery() throws IOException {
    SceneLoading.loadScene("views/requestTypes/FloralRequest.fxml");
  }

  @FXML
  void loadSecurity() throws IOException {
    SceneLoading.loadScene("views/requestTypes/SecurityServicesRequest.fxml");
  }

  @FXML
  void loadLab() throws IOException {
    SceneLoading.loadScene("views/requestTypes/LabRequest.fxml");
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

  /**
   * Performs a search given key words
   * these words are processed through a series of RegEx's and filters to fit a standardized form
   * these strings are then appended with other key words in order to switch to that respective page
   * TODO: add error handling, levenshtein distance, error page, history page?
   */
  @FXML
  public void doSearch() throws IOException {
    String entry = this.searchBar.getText();

    //Valid pages: !!! NEEDS TO UPDATE EVERYTIME NEW PAGE IS ADDED !!!
    String[] pages = {"floral",
            "lab",
            "laundry",
            "meal",
            "medical",
            "security",
            "map",
            "settings",
            "menu",
            "request"};

    //The following lines of code filters and processes search request
    entry = entry.toLowerCase();
    entry.replaceAll(" ", "");
    entry.replaceAll("active", "table");
    entry.replaceAll("current", "table");
    entry.replaceAll("equipment", "medical");

    //Will see if this request exists
    String isValid = getPage(entry, pages);
    if(isValid == "ERROR") {
      //TODO: Levenshtein distance computations
    }
    else{
      entry = isValid;
      SceneLoading.loadPopup(entry, "views/SideBar.fxml");
    }
  }

  /**
   * Helper method for doSearch
   * Will determine if a valid page exists
   */
  public String getPage(String entry, String[] pages) {
    boolean isGood = false;
    for (int i = 0; i < pages.length; i++) {
      if (entry.contains(pages[i])) {
        i = pages.length;
        if (entry.contains("medical")) {
          entry = "MedicalEquipmentRequest.fxml";
        } else if (entry.contains("table")) {
          entry = "ActiveServiceRequest.fxml";
        } else if (entry.contains("security")) {
          entry = "SecurityServicesRequest.fxml";
        } else {
          entry = entry.substring(0).toUpperCase();
          entry = entry + "Request.fxml";
        }
        entry = "views/" + entry;
      }
      isGood = true;
    }
    if(!isGood){
      entry = "ERROR";
    }
    return entry;
  }
}
