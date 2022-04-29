package edu.wpi.cs3733.d22.teamY.controllers;

import static edu.wpi.cs3733.d22.teamY.controllers.RequestMenuController.printResults;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class OtherTeamAPIs {

  @FXML private MFXButton backButton;

  @FXML
  void backButton() {
    Stage stage;
    stage = (Stage) backButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void loadTeamB() {
    System.out.println("Loading Team B");
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "TeamBAPI.jar");
    pb.directory(new File("src/main/resources/edu/wpi/cs3733/d22/teamY/APIs/TeamB"));
    try {
      Process p = pb.start();
      printResults(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void loadTeamC() {
    System.out.println("Loading Team C");
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "teamC.jar");
    pb.directory(new File("libs"));
    try {
      Process p = pb.start();
      printResults(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void loadTeamY() {
    System.out.println("Loading Team Y");
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "API.jar");
    pb.directory(new File("libs"));
    try {
      Process p = pb.start();
      printResults(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
