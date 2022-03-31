package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DataManager;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class mapPageController extends AbsGlobalControllerFuncs {
  // Base pane for displaying new scenes
  @FXML private Pane mapPane;
  // Sidebar pane
  @FXML private Pane sidebarPane;
  // Hamburger that opens the sidebar
  @FXML private JFXHamburger hamburger;
  // Menu of buttons
  @FXML private VBox buttonBox;
  // Temp button
  @FXML JFXButton tempSidebarButton;

  private static final int CIRCLE_RADIUS_PX = 10;
  private static final Paint CIRCLE_PAINT = Color.RED;

  enum Floors {
    GROUND_FLOOR("GG"),
    FIRST_FLOOR("1"),
    LOWER_LEVEL_1("L1"),
    LOWER_LEVEL_2("L2"),
    SECOND_FLOOR("2"),
    THIRD_FLOOR("3");

    public final String dbKey;

    Floors(String dbKey) {
      this.dbKey = dbKey;
    }
  }

  private Floors lastFloor = Floors.FIRST_FLOOR;

  // Base pane for displaying new scenes
  // @FXML private Pane mapPane;
  private final ImageView imageView = new ImageView();

  private final HashMap<Floors, Image> floorImages = new HashMap<>();
  private final HashMap<Location, Circle> loadedShapes = new HashMap<>();

  /**
   * Shows an edit dialog. Updates the contents of l if the ok button is clicked, otherwise does
   * not.
   *
   * @param l The location object to potentially modify
   */
  private boolean showEditDialog(final Location l) {
    Dialog<Boolean> editDialog = new Dialog<>();
    VBox dialogVbox = new VBox();
    TextField nodeType = new TextField(), longName = new TextField(), shortName = new TextField();
    // Populate text fields with existing data
    nodeType.setText(l.getNodeType());
    longName.setText(l.getLongName());
    shortName.setText(l.getShortName());

    dialogVbox.getChildren().add(new HBox(new Label("Type: "), nodeType));
    dialogVbox.getChildren().add(new HBox(new Label("Long Name: "), longName));
    dialogVbox.getChildren().add(new HBox(new Label("Short Name: "), shortName));

    editDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    editDialog.getDialogPane().setContent(dialogVbox);

    editDialog.setResultConverter(
        button -> {
          if (button == ButtonType.OK) {
            // Update location data from text fields
            l.setNodeType(nodeType.getText());
            l.setLongName(longName.getText());
            l.setShortName(shortName.getText());
            return true;
          }

          return false;
        });

    Optional<Boolean> result = editDialog.showAndWait();
    return result.orElse(false);
  }

  private Location showCreateDialog(int x, int y, String floor, String building) {
    TextInputDialog nodeDialog = new TextInputDialog();
    nodeDialog.setHeaderText("Enter Node ID");
    Optional<String> result = nodeDialog.showAndWait();
    if (result.isPresent()) {
      Location l = new Location(result.get(), x, y, floor, building, "", "", "");
      if (showEditDialog(l)) return l;
    }
    return null;
  }

  private void switchFloor(Floors newFloor) {
    lastFloor = newFloor;
    // Remove all loaded shapes from the pane
    loadedShapes.forEach((l, c) -> mapPane.getChildren().remove(c));

    // Clear the list of loaded locations and shapes
    loadedShapes.clear();

    // Load new locations from DB and create shapes for each
    try {
      DataManager.getLocationDao()
          .getLocationsOnFloor(newFloor.dbKey)
          .forEach(
              (l) -> {
                // Create the circle for this location and add context menu handlers to it
                Circle c = new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX, CIRCLE_PAINT);

                // Create context menu for shape
                ContextMenu rightClickMenu = new ContextMenu();
                MenuItem editItem = new MenuItem("Edit");
                MenuItem deleteItem = new MenuItem("Delete");

                rightClickMenu.getItems().addAll(editItem, deleteItem);
                editItem.setOnAction(
                    e -> {
                      if (showEditDialog(l)) {
                        try {
                          DataManager.getLocationDao().updateLocation(l);
                        } catch (DaoUpdateException ex) {
                          ex.printStackTrace();
                        }
                      }
                    });

                deleteItem.setOnAction(
                    e -> {
                      try {
                        DataManager.getLocationDao().deleteLocation(l);
                      } catch (DaoDeleteException ex) {
                        ex.printStackTrace();
                      }

                      // Reload data from DB to prevent desync
                      switchFloor(lastFloor);
                    });

                c.setOnContextMenuRequested(
                    e -> rightClickMenu.show(c, e.getScreenX(), e.getScreenY()));
                loadedShapes.put(l, c);
                mapPane.getChildren().add(c);
              });
    } catch (DaoGetException e) {
      e.printStackTrace();
    }

    // Double click handler for adding new items
    mapPane.setOnMouseClicked(
        e -> {
          if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
            // TODO add building
            Location created =
                showCreateDialog((int) e.getX(), (int) e.getY(), lastFloor.dbKey, "");
            if (created != null) {
              // The element was created
              try {
                DataManager.getLocationDao().addLocation(created);
              } catch (DaoAddException ex) {
                ex.printStackTrace();
              }
              switchFloor(lastFloor);
            }
          }
        });

    // Switch to new background image
    imageView.setImage(floorImages.get(newFloor));
  }

  public void initialize() {
    floorImages.put(
        Floors.FIRST_FLOOR,
        new Image(App.class.getResource("views/images/firstfloor.png").toString()));
    floorImages.put(
        Floors.GROUND_FLOOR,
        new Image(App.class.getResource("views/images/groundfloor.png").toString()));
    floorImages.put(
        Floors.LOWER_LEVEL_1,
        new Image(App.class.getResource("views/images/lowerlevel1.png").toString()));
    floorImages.put(
        Floors.LOWER_LEVEL_2,
        new Image(App.class.getResource("views/images/lowerlevel2.png").toString()));
    floorImages.put(
        Floors.SECOND_FLOOR,
        new Image(App.class.getResource("views/images/secondfloor.png").toString()));
    floorImages.put(
        Floors.THIRD_FLOOR,
        new Image(App.class.getResource("views/images/thirdfloor.png").toString()));

    mapPane.getChildren().add(imageView);

    // Load initial floor
    switchFloor(lastFloor);
  }

  // back button
  @FXML
  void mainMenu() throws IOException {
    loadScene("views/mainPage.fxml");
  }
  // Loading maps
  @FXML
  void loadGroundFloorMap() {
    switchFloor(Floors.GROUND_FLOOR);
  }

  @FXML
  void loadBasement1Map() {
    switchFloor(Floors.LOWER_LEVEL_1);
  }

  @FXML
  void loadBasement2Map() {
    switchFloor(Floors.LOWER_LEVEL_2);
  }

  @FXML
  void loadFloor1Map() {
    switchFloor(Floors.FIRST_FLOOR);
  }

  @FXML
  void loadFloor2Map() {
    switchFloor(Floors.SECOND_FLOOR);
  }

  @FXML
  void loadFloor3Map() {

    switchFloor(Floors.THIRD_FLOOR);
  }
  // Sidebar
  @FXML
  void autoOpenCloseSidebar() throws IOException {
    if (sidebarPane.getChildren().size() == 0) {
      loadSidebar(sidebarPane);
    } else {
      removeSidebar(sidebarPane);
    }
  }

  @FXML
  void showSidebar() throws IOException {
    hamburger.setVisible(false);
    buttonBox.setLayoutX(250);
    tempSidebarButton.setVisible(true);
    loadSidebar(sidebarPane);
  }

  @FXML
  void hideSidebar() {
    hamburger.setVisible(true);
    buttonBox.setLayoutX(150);
    tempSidebarButton.setVisible(false);
    removeSidebar(sidebarPane);
  }
}
