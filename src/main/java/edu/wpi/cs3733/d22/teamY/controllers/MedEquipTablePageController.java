package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MedEquipTablePageController {

  @FXML private static TableView<MedEquip> medEquipTableView;

  @FXML private TextArea equipmentTable;

  @FXML Pane sidebarPane;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  @FXML
  public void initialize() throws IOException {
    List<MedEquip> medEquips;
    try {
      medEquips = DBManager.getAll(MedEquip.class);
    } catch (Exception e) {
      e.printStackTrace();
      medEquips = Collections.emptyList();
    }
    String[] totalText = new String[4];
    String totalResult = "";
    for (int i = 0; i < medEquips.size(); i++) {

      String shortName = DBUtils.convertIDToName(medEquips.get(i).getEquipLocId());
      String clean;
      if (medEquips.get(i).isClean().equals("0")) {
        clean = "used";
      } else {
        clean = "clean";
      }

      totalText[0] = medEquips.get(i).getEquipID();
      totalText[1] = medEquips.get(i).getEquipType();
      totalText[2] = shortName;
      totalText[3] = clean;

      String[] stdOutputOptions = new String[4];
      stdOutputOptions[0] = "                       "; // 23 spaces
      stdOutputOptions[1] = "                 "; // 17 spaces
      stdOutputOptions[2] = "                         "; // 25 spaces
      stdOutputOptions[3] = "          ";

      char[] stdOutput;
      for (int j = 0; j < 4; j++) {
        stdOutput = stdOutputOptions[j].toCharArray();
        for (int k = 0; k < totalText[j].length(); k++) {
          stdOutput[k] = totalText[j].charAt(k);
        }
        for (int f = 0; f < stdOutput.length; f++) {
          totalResult += stdOutput[f];
        }
      }

      totalResult += "\n";
    }

    equipmentTable.setText(totalResult);
  }

  public static class ChangeTheme {
    // Base pane for displaying new scenes
    @FXML private Pane mapPane;
    // Sidebar
    @FXML private Pane sidebarPane;
    @FXML private JFXButton closeSidebarHiddenButton;
    @FXML private JFXHamburger sidebarHamburger;
    AnchorPane sidebar = null;

    // Sidebar
    @FXML
    void openSidebarLayout() {
      SidebarUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
    }

    @FXML
    void closeSidebarLayout() {
      SidebarUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
    }
  }
}
