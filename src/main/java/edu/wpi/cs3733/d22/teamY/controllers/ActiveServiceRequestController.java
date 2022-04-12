package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.model.MealRequest;
import edu.wpi.cs3733.d22.teamY.model.Requestable;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ActiveServiceRequestController {
  @FXML private VBox requestBox;
  @FXML private MFXScrollPane scrollBox;

  ArrayList<ReqControllerPanePair> rqPairs;

  public void initialize() throws IOException {
    // requestBox.setPrefHeight(1000);
    scrollBox.setBackground(Background.EMPTY);

    rqPairs = new ArrayList<>();

    int sampleNumberReqs = 10;

    for (int i = 0; i < sampleNumberReqs; i++) {
      addRequest(new MealRequest());
      rqPairs.get(i).getController().setColor(SingularServiceRequestController.priorityColor(i));
    }

    for (ReqControllerPanePair pair : rqPairs) {
      addToBox(pair);
    }
  }

  private void addRequest(Requestable req) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SingularServiceRequest.fxml"));
    Pane pane = loader.load();
    SingularServiceRequestController controller = loader.getController();

    if (controller == null) {
      System.out.println("BRUUUUUH");
    }

    rqPairs.add(new ReqControllerPanePair(controller, pane));
  }

  private void addToBox(ReqControllerPanePair rqPair) {
    requestBox.getChildren().add(rqPair.getPane());
  }
}
