package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class mainPageController {
  @FXML
  void backToWelcomePage(ActionEvent event) throws IOException {
    // Setting it to request menu for now but will make it to welcome page
    App.getInstance().setSceneToRequestMenu();
  }

  void createServiceRequest(ActionEvent event) throws IOException{
    //Sets scene to service request page
    App.getInstance().setSceneToRequestMenu();
  }

  void viewServiceRequest(ActionEvent event) throws IOException{
    //Sets scene to view service request
    App.getInstance().setSceneToRequestMenu();
  }
}
