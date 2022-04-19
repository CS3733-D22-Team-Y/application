package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.model.Requestable;
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

  @FXML private VBox alertsBox;

  @FXML private TextArea testAlert;

  private Scene requestMenu = null;

  public DashboardController() {}

  public void initialize() throws IOException {
    // requestBox.setPrefHeight(1000);
    alertsBox.setBackground(Background.EMPTY);
    addToBox("Hello there");

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
