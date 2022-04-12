package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.model.MealRequest;
import edu.wpi.cs3733.d22.teamY.model.Requestable;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ActiveServiceRequestController {
  public static LinkedList<SingularServiceRequestController> requestControllers =
      new LinkedList<>();

  @FXML private VBox requestBox;
  @FXML private MFXScrollPane scrollBox;
  @FXML private JFXTextArea extraInfoBox;

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

    System.out.println(ActiveServiceRequestController.requestControllers.size() + "\n\n\n");

    for (ReqControllerPanePair pair : rqPairs) {
      addToBox(pair);
    }

    SceneUtil.serviceRequests = this;
  }

  public void fillInfoField(String info) {
    extraInfoBox.setText(info);
  }

  private void addRequest(Requestable req) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SingularServiceRequest.fxml"));
    Pane pane = loader.load();
    // SingularServiceRequestController controller = loader.getController();
    SingularServiceRequestController controller =
        ActiveServiceRequestController.requestControllers.getLast();
    System.out.println(ActiveServiceRequestController.requestControllers.getLast());

    rqPairs.add(new ReqControllerPanePair(controller, pane));
  }

  private void addToBox(ReqControllerPanePair rqPair) {
    requestBox.getChildren().add(rqPair.getPane());
  }
}
