package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class mapPageController {
  // Base pane for displaying new scenes
  @FXML private Pane mapPane;
  // Sidebar pane
  @FXML private Pane sidebarPane;
  // Hamburger that opens the sidebar
  @FXML private JFXHamburger hamburger;
  // Menu of buttons
  @FXML private VBox buttonBox;
  @FXML private JFXButton closeSidebarHiddenButton;
  @FXML private JFXHamburger sidebarHamburger;

  AnchorPane sidebar = null;

  private static final int CIRCLE_RADIUS_PX = 10;
  private static final Paint CIRCLE_PAINT = Color.RED;

  // Screen size constants
  private static final int MAP_XMIN = 0;
  private static final int MAP_YMIN = 0;
  private static final int MAP_XMAX = 700;
  private static final int MAP_YMAX = 700;

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

  // Checks if the location is in bounds
  private Boolean isValidPlacement(Location l) {
    return !(l.getXCoord() > MAP_XMAX
        || l.getXCoord() < MAP_XMIN
        || l.getYCoord() > MAP_YMAX
        || l.getYCoord() < MAP_YMIN);
  }

  private void switchFloor(Floors newFloor) {
    lastFloor = newFloor;
    // Remove all loaded shapes from the pane
    List<Node> shapes =
        mapPane.getChildren().stream()
            .filter((c) -> c instanceof Shape)
            .collect(Collectors.toList());
    mapPane.getChildren().removeAll(shapes);

    // Creates a shape
    Rectangle r = new Rectangle(50, 50, 50, 50);
    r.setFill(Color.GREEN);

    // Do something when the shape is clicked
    r.setOnMouseClicked((e) -> System.out.println("yeah yeah woo"));

    // Add the shape to the map
    mapPane.getChildren().add(r);

    // Load new locations from DB and create shapes for each
    try {
      // Gets all locations on the newly selected floor
      DBUtils.getLocationsOnFloor(newFloor.dbKey)
          // And iterates over them
          .forEach(
              (l) -> {
                List<MedEquip> equip = DBUtils.getEquipmentAtLocation(l);
                Set<String> equipTypes =
                    equip.stream().map(MedEquip::getEquipType).collect(Collectors.toSet());

                boolean hasEquipment = equip.size() > 0;

                // equipTypes: list of equipment types in the location.  if room has multiple of one
                // type, only 1 element is in the list still
                // equip: list of all equipment objects in the location
                // hasEquipment: true if there is 1 or more equipment in location

                // Checks if the point is in a valid position
                if (isValidPlacement(l)) {
                  // Create the circle for this location and add context menu handlers to it
                  Circle c =
                      new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX, CIRCLE_PAINT);
                  mapPane.getChildren().add(c);

                  // Create context menu for shape
                  ContextMenu rightClickMenu = new ContextMenu();
                  MenuItem editItem = new MenuItem("Edit");
                  MenuItem deleteItem = new MenuItem("Delete");
                  MenuItem showEquipment = new MenuItem("Show Equipment");

                  rightClickMenu.getItems().addAll(editItem, deleteItem, showEquipment);
                  editItem.setOnAction(
                      e -> {
                        if (showEditDialog(l)) {
                          DBManager.update(l);
                        }
                      });

                  deleteItem.setOnAction(
                      e -> {
                        DBManager.delete(l);
                        // Reload data from DB to prevent desync
                        switchFloor(lastFloor);
                      });

                  showEquipment.setOnAction(
                      e -> {
                        // TODO do something better with this
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText(equip.toString());
                        a.show();
                      });

                  c.setOnContextMenuRequested(
                      e -> rightClickMenu.show(c, e.getScreenX(), e.getScreenY()));
                }
              });
    } catch (Exception e) {
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
                DBManager.save(created);
              } catch (Exception ex) {
                ex.printStackTrace();
              }
              switchFloor(lastFloor);
            }
          }
        });

    // Switch to new background image
    imageView.setImage(floorImages.get(newFloor));
  }

  public void initialize() throws IOException {
    // Load floors
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

    // Load sidebar
    sidebar = SidebarUtil.initializeSidebar(sidebarPane);
  }

  // back button
  @FXML
  void mainMenu() throws IOException {
    SceneLoading.loadScene("views/mainPage.fxml");
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
  void openSidebarLayout() {
    SidebarUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
    buttonBox.setLayoutX(250);
  }

  @FXML
  void closeSidebarLayout() {
    SidebarUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
    buttonBox.setLayoutX(150);
  }
}
