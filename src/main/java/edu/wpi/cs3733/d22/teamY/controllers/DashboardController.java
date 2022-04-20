package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.RequestTypes;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import edu.wpi.cs3733.d22.teamY.model.Requestable;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DashboardController {
  // Radio Buttons
  public static LinkedList<String> alerts = new LinkedList<>();
  // Text inputs

  // Side bar
  @FXML private AnchorPane sidebarPane;

  @FXML private HBox alertsBox;

  @FXML private TextArea testAlert;

  private Scene requestMenu = null;

  public DashboardController() {}

  public void initialize() throws IOException {
    // requestBox.setPrefHeight(1000);
    alertsBox.setBackground(Background.EMPTY);

    if (DBUtils.checkAvailableEquipmentOnFloor("3", "PUMP") < 5) {

      addToBox("Floor 3 has less than 5 Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "supply an infusion pump to floor 3",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkAvailableEquipmentOnFloor("4", "PUMP") < 5) {

      addToBox("Floor 4 has less than 5 Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "supply an infusion pump to floor 4",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkAvailableEquipmentOnFloor("5", "PUMP") < 5) {

      addToBox("Floor 5 has less than 5 Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "supply an infusion pump to floor 5",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }

    if (DBUtils.checkUnavailableEquipmentOnFloor("3", "PUMP") >= 10) {

      addToBox("Floor 3 has 10 dirty Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean an infusion pump on floor 3",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("4", "PUMP") >= 10) {

      addToBox("Floor 4 has 10 dirty Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean an infusion pump on floor 4",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("5", "PUMP") >= 10) {

      addToBox("Floor 5 has 10 dirty Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean an infusion pump on floor 5",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }

    if (DBUtils.checkUnavailableEquipmentOnFloor("3", "BED") >= 6) {

      addToBox("Floor 3 has 6 dirty beds");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean a bed on floor 3",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("4", "BED") >= 6) {

      addToBox("Floor 4 has 6 dirty beds");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean a bed on floor 4",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("5", "BED") >= 6) {

      addToBox("Floor 5 has 6 dirty beds");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean a bed on floor 5",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }

    // rqPairs = new ArrayList<>();
    /*
    for (Class<? extends Requestable> rqClass : requestables) {

      List<Requestable> reqs = DBManager.getAll(rqClass);
      if (reqs != null) {
        for (Requestable req : reqs) {
          addRequest(req);
        }
      }
    }





    for (RequestSet pair : rqPairs) {
      addToBox(pair);
    }

    nothingToSeeHere.setVisible(rqPairs.isEmpty());

    SceneUtil.serviceRequests = this;
    */
    NewSceneLoading.loadSidebar(sidebarPane);
  }

  public static void reloadDashboard() {}

  private void addRequest(Requestable req) throws IOException {

    // System.out.println(ActiveServiceRequestController.requestControllers.getLast());

    // rqPairs.add(new RequestSet(controller, pane, req));
  }

  private void addToBox(String inputText) {

    // Label test = new Label("test");
    // test.setBackground(new Background(new BackgroundFill(Paint.TRANSLUCENT, CornerRadii.EMPTY,
    // Insets.EMPTY)));
    Label testLabel = new Label(inputText);
    testLabel.setStyle("-fx-font-size: 20");
    testLabel.setStyle("-fx-border-color: black;");
    testLabel.setMinWidth(262);

    alertsBox.getChildren().add(testLabel);

    // rqSet.getController().populateFromRequestable(rqSet.getRequest());
  }

  // Called when the submit button is pressed.

  // Returns the database name of the selected radio button.

}
