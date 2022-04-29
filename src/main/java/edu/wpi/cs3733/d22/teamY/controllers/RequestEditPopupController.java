package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.d22.teamY.DBHandler;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RequestEditPopupController implements IController {

  public static ServiceRequest selected;
  @FXML private JFXButton stayButton;

  @FXML
  void dismissRequest() throws IOException {
    DBHandler.getInstance().handleDelete(selected);
    System.out.println("Deleted: " + selected.getInfoBoxText());
    Stage stage;
    stage = (Stage) stayButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void stayIn() {
    Stage stage;
    stage = (Stage) stayButton.getScene().getWindow();
    stage.close();
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {}
}
