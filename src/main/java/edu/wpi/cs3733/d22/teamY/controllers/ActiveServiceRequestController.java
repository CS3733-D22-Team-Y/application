package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.*;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
  @FXML private JFXTextArea extraInfoText;

  private ArrayList<RequestSet> rqPairs;

  private static final List<Class<? extends Requestable>> requestables =
      Arrays.asList(
          LabRequest.class,
          MedEquipReq.class,
          MealRequest.class,
          TranslatorRequest.class,
          FloralRequest.class,
          LaundryRequest.class,
          SecurityServiceRequest.class,
          MiscRequest.class);

  public void initialize() throws IOException {
    // requestBox.setPrefHeight(1000);
    scrollBox.setBackground(Background.EMPTY);

    rqPairs = new ArrayList<>();

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

    SceneUtil.serviceRequests = this;
  }

  public void fillInfoField(String info) {
    extraInfoText.setText(info);
  }

  private void addRequest(Requestable req) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SingularServiceRequest.fxml"));
    Pane pane = loader.load();
    // SingularServiceRequestController controller = loader.getController();
    SingularServiceRequestController controller =
        ActiveServiceRequestController.requestControllers.getLast();
    System.out.println(ActiveServiceRequestController.requestControllers.getLast());

    rqPairs.add(new RequestSet(controller, pane, req));
  }

  private void addToBox(RequestSet rqSet) {
    requestBox.getChildren().add(rqSet.getPane());
    rqSet.getController().populateFromRequestable(rqSet.getRequest());
  }
}
