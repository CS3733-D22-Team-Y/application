package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class activeRequestsController {
  Scene mainMenu = null;

  public activeRequestsController() throws IOException {}

  @FXML
  void mainMenu(ActionEvent event) throws IOException {
    mainMenu =
        new Scene(
            FXMLLoader.load(Objects.requireNonNull(App.class.getResource("views/mainPage.fxml"))));

    App.getInstance().setScene(mainMenu); // Returns to request menu
  }
}
