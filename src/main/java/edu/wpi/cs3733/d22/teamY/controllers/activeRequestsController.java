package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class activeRequestsController {

  private Scene requestMenu =
      new Scene(
          FXMLLoader.load(Objects.requireNonNull(App.class.getResource("views/requestMenu.fxml"))));

  public activeRequestsController() throws IOException {}

  @FXML
  void backToRequestMenu(ActionEvent event) throws IOException {
    App.getInstance().setScene(requestMenu); // Returns to request menu
  }
}
