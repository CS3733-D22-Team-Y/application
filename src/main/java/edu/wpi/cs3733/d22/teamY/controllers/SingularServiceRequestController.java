package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SingularServiceRequestController implements IController {

  @FXML private Group colorGizmo;

  @FXML private Rectangle reqRectangle;

  @FXML private Label assignedNurse;
  @FXML private Label reqType;
  @FXML private Label mapLocation;

  @FXML private JFXTextArea extraInfoText;
  private String additional;
  private int priority;

  private ServiceRequest selected;

  @FXML
  void initialize() {
    ActiveServiceRequestController.requestControllers.add(this);
  }

  public void populateFromRequest(ServiceRequest req) {
    additional = req.getInfoBoxText();
    priority = req.getRequestPriority();
    setColor(priorityColor(priority));

    assignedNurse.setText(req.getAssignedNurse());
    reqType.setText(req.getType().getFriendlyName());
    mapLocation.setText(DBUtils.convertIDToName(req.getLocationID()));
    fillInfoField(additional);
    selected = req;
  }

  public int getPriority() {
    return priority;
  }

  private void setColor(Color color) {
    for (Node child : colorGizmo.getChildren()) {
      ((Shape) child).setFill(color);
    }
  }

  public static Color priorityColor(int priority) {
    int p = Math.min(Math.max(priority, 0), 10);
    return Color.hsb((10 - p) * 12.8, 0.36, 0.98);
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {}

  public void fillInfoField(String info) {
    extraInfoText.setText(info);
  }

  @FXML
  private void openReqPopup() throws IOException {
    RequestEditPopupController.selected = selected;
    System.out.println("Selected Request: " + RequestEditPopupController.selected.getInfoBoxText());
    SceneLoading.loadPopup("views/popups/RequestEdit.fxml", "views/SideBar.fxml");
  }
}
