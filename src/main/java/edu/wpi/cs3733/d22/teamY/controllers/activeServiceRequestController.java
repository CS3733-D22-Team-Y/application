package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class activeServiceRequestController {
  @FXML
  void mainMenu() throws IOException {
    Scene mainPage =
        new Scene(
            FXMLLoader.load(Objects.requireNonNull(App.class.getResource("views/mainPage.fxml"))));
    App.getInstance().setScene(mainPage);
  }
}
