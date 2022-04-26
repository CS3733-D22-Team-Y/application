package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class MedEquipTablePageController implements IController {

  @FXML private TableView<MedEquip> medEquipTableView;

  @FXML private AnchorPane sidebarPane;

  @FXML private AnchorPane mainPane;
  @FXML private ImageView bgImage;
  @FXML private Rectangle bgGradient;

  @FXML
  public void initialize() throws IOException {

    // Snag all of the equipment from the database.
    List<MedEquip> medEquips;
    try {
      medEquips = DBManager.getAll(MedEquip.class);
    } catch (Exception e) {
      e.printStackTrace();
      medEquips = Collections.emptyList();
    }

    // Setup columns and how the data is displayed
    TableColumn<MedEquip, String> equipIDCol = new TableColumn<>("Equipment ID");
    equipIDCol.setCellValueFactory(new PropertyValueFactory<>("equipID"));

    TableColumn<MedEquip, String> equipTypeCol = new TableColumn<>("Equipment Type");
    equipTypeCol.setCellValueFactory(new PropertyValueFactory<>("equipType"));

    TableColumn<MedEquip, String> equipLocIdCol = new TableColumn<>("Equipment Location");
    equipLocIdCol.setCellValueFactory(new PropertyValueFactory<>("equipLocId"));

    TableColumn<MedEquip, String> isCleanCol = new TableColumn<>("Clean Status");
    isCleanCol.setCellValueFactory(new PropertyValueFactory<>("isClean"));

    TableColumn<MedEquip, String> statusCol = new TableColumn<>("Equipment Status");
    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Add columns into the tableview
    medEquipTableView.getColumns().add(equipIDCol);
    medEquipTableView.getColumns().add(equipTypeCol);
    medEquipTableView.getColumns().add(equipLocIdCol);
    medEquipTableView.getColumns().add(isCleanCol);
    medEquipTableView.getColumns().add(statusCol);

    // Add data to the tableview
    for (MedEquip e : medEquips) {
      // You can change how the data is displayed here
      medEquipTableView.getItems().add(e);
    }

    NewSceneLoading.loadSidebar(sidebarPane);
  }

  @Override
  public IController getController() {
    return this;
  }

  @Override
  public void initializeScale() {
    Scaling.scaleFullscreenItemAroundTopLeft(mainPane);
    Scaling.scaleBackground(bgImage, bgGradient);
  }
}
