package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.component.MapComponent;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MapPageController {
  private List<Location> getLocationsForFloor(Floors floor) {
    return DBUtils.getLocationsOnFloor(floor.dbKey);
  }

  private List<MedEquip> getEquipmentAtLocation(Location location) {
    return DBUtils.getEquipmentAtLocation(location);
  }

  private int getNumberOfServiceRequestsAtLocation(Location location) {
    return 0;
  }

  enum MapMode {
    LOCATION("Locations"),
    EQUIPMENT("Equipment"),
    SERVICE_REQUESTS("Service Requests");

    private final String name;

    MapMode(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  private MapMode mapMode = MapMode.LOCATION;

  @FXML private Button floorLL1Button;
  @FXML private Button floorLL2Button;
  @FXML private Button floor1Button;
  @FXML private Button floor2Button;
  @FXML private Button floor3Button;
  @FXML private Button floor4Button;
  @FXML private Button floor5Button;
  @FXML private AnchorPane mapRoot;
  @FXML private MFXTextField locationID;
  @FXML private MFXTextField locationShort;
  @FXML private MFXTextField locationLong;
  @FXML private MFXTextField locationX;
  @FXML private MFXTextField locationY;
  @FXML private MFXTextField locationBuilding;
  @FXML private Pane locationInfoPane;
  @FXML public MFXButton locationSubmit;
  private String fuck = "shit";
  @FXML private MFXLegacyComboBox<MapMode> modeBox;

  MapComponent mapComponent = new MapComponent();

  private static final int CIRCLE_RADIUS_PX = 10;
  private static final Paint CIRCLE_PAINT = Color.RED;

  // Screen size constants
  private static final int MAP_XMIN = 0;
  private static final int MAP_YMIN = 0;
  private static final int MAP_XMAX = 700;
  private static final int MAP_YMAX = 700;
  private static final int iconDim = 50;
  private static final int logoDim = 20;
  public static final Map<String, String> equipNames =
      Map.of(
          "BED", "Bed",
          "PUMP", "Infusion Pump",
          "RECLINER", "Recliner");

  final Map<String, String> dotIcons =
      Map.of(
          "BED", "bedlogo.png",
          "PUMP", "pumplogo.png",
          "RECLINER", "reclinerlogo.png");

  enum Floors {
    FIRST_FLOOR(
        "1",
        "Floor 1",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor1.jpg").toString()),
            0,
            0,
            0.05)),
    LOWER_LEVEL_1(
        "L1",
        "Lower Level 1",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor-1.jpg").toString()),
            0,
            0,
            0.05)),
    LOWER_LEVEL_2(
        "L2",
        "Lower Level 2",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor-2.jpg").toString()),
            0,
            0,
            0.05)),
    SECOND_FLOOR(
        "2",
        "Floor 2",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor2.jpg").toString()),
            0,
            0,
            0.05)),
    THIRD_FLOOR(
        "3",
        "Floor 3",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor3.jpg").toString()),
            0,
            0,
            0.05)),
    FOURTH_FLOOR(
        "4",
        "Floor 4",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor4.jpg").toString()),
            0,
            0,
            0.05)),
    FIFTH_FLOOR(
        "5",
        "Floor 5",
        new MapComponent.MapImage(
            () -> new Image(App.class.getResource("views/images/floor5.jpg").toString()),
            0,
            0,
            0.05));

    public final String dbKey;
    public final String name;
    public final MapComponent.MapImage image;

    Floors(String dbKey, String name, MapComponent.MapImage image) {
      this.dbKey = dbKey;
      this.name = name;
      this.image = image;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  private Floors lastFloor = Floors.FIRST_FLOOR;

  // Base pane for displaying new scenes
  // @FXML private Pane mapPane;
  private final ImageView imageView = new ImageView();

  private final HashMap<Floors, Image> floorImages = new HashMap<>();

  //  private String iconDecider(Set<String> equipTypes) {
  //    if (equipTypes.contains("PUMP")) {
  //      return dotIcons.get("PUMP");
  //    } else if (equipTypes.contains("RECLINER")) {
  //      return dotIcons.get("RECLINER");
  //    } else if (equipTypes.contains("BED")) {
  //      return dotIcons.get("BED");
  //    } else {
  //      return dotIcons.get("none");
  //    }
  //  }

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

  /**
   * Called whenever the floor or map mode is switched
   *
   * @param newFloor
   * @param newMode
   */
  private void switchMap(Floors newFloor, MapMode newMode) {
    lastFloor = newFloor;

    // Load new locations from DB and create shapes for each
    try {
      // Get all locations on the floor
      List<Location> locations = getLocationsForFloor(newFloor);
      List<Node> mapElements = new ArrayList<>();

      //      for (Location location : locations) {
      //        // Create a shape for each location
      //        Circle locationCircle =
      //            new Circle(location.getXCoord(), location.getYCoord(), 1000, Color.RED);
      //        mapElements.add(locationCircle);
      //      }

      // Gets all locations on the newly selected floor
      DBUtils.getLocationsOnFloor(newFloor.dbKey)
          // And iterates over them
          .forEach(
              (l) -> {
                System.out.println(l.getXCoord() + "," + l.getYCoord());
                List<MedEquip> equip = DBUtils.getEquipmentAtLocation(l);
                Set<String> equipTypes =
                    equip.stream().map(MedEquip::getEquipType).collect(Collectors.toSet());

                boolean hasEquipment = equip.size() > 0;

                // equipTypes: list of equipment types in the location.  if room has
                // multiple of one
                // type, only 1 element is in the list still
                // equip: list of all equipment objects in the location
                // hasEquipment: true if there is 1 or more equipment in location

                // Checks if the point is in a valid position
                // Create the circle for this location and add context menu handlers to it
                Circle c = new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX, CIRCLE_PAINT);
                Pane i = new Pane();
                i.setLayoutX(l.getXCoord());
                i.setLayoutY(l.getYCoord());
                Circle frame = new Circle(iconDim / 2, iconDim / 2, iconDim / 2, Color.NAVY);
                i.setPrefWidth(iconDim);
                i.setPrefHeight(iconDim);
                i.getChildren().add(frame);
                mapElements.add(i);
                // ImageView iconView = new ImageView(icon);
                // iconView.setTranslateX((Integer) ((iconDim - logoDim) / 2));
                // iconView.setTranslateY((Integer) ((iconDim - logoDim) / 2));
                // i.getChildren().add(iconView);
                // Create context menu for shape
                ContextMenu rightClickMenu = new ContextMenu();
                MenuItem editItem = new MenuItem("Edit");
                MenuItem deleteItem = new MenuItem("Delete");
                MenuItem showEquipment = new MenuItem("Show Equipment");

                if (hasEquipment) {
                  rightClickMenu.getItems().addAll(editItem, deleteItem, showEquipment);
                } else {
                  rightClickMenu.getItems().addAll(editItem, deleteItem);
                }
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
                      switchMap(lastFloor, mapMode);
                    });

                i.setOnContextMenuRequested(
                    e -> {
                      if (modeBox.getValue().equals("Locations")) {
                        fuck = String.valueOf(l.getNodeID());
                        locationInfoPane.setVisible(true);
                        locationX.setText(String.valueOf(l.getXCoord()));
                        locationY.setText(String.valueOf(l.getYCoord()));
                        locationBuilding.setText(l.getBuilding());
                        locationShort.setText(l.getShortName());
                        locationLong.setText(l.getLongName());
                        locationID.setText(String.valueOf(l.getNodeID()));
                      }
                    });

                locationSubmit.setOnMouseClicked(
                    e -> {
                      Location fuckMe =
                          new Location(
                              fuck,
                              Integer.parseInt(locationX.getText()),
                              Integer.parseInt(locationY.getText()),
                              l.getFloor(),
                              locationBuilding.getText(),
                              l.getNodeType(),
                              locationLong.getText(),
                              locationShort.getText());
                      System.out.println("fuck");
                      // l.setShortName(locationShort.getText());
                      // l.setLongName(locationLong.getText());
                      System.out.println(l.getShortName());
                      // l.setBuilding(locationBuilding.getText());
                      // l.setXCoord(Integer.parseInt(locationX.getText()));
                      // l.setYCoord(Integer.parseInt(locationY.getText()));
                      //  System.out.println(l.toStringArray().toString());

                      ///  System.out.println(l.getNodeID());System.out.println(l.getShortName());
                      //   System.out.println("fuck me" + fuckMe.getNodeID());
                      //   System.out.println("fuck me" + fuckMe.getShortName());
                      //   System.out.println(l.getShortName());
                      DBManager.update(fuckMe);
                      switchMap(newFloor, mapMode);
                    });
              });
      ;
      System.out.println("FUCCCKCKCKCKCKCKCKCKCMCKn");
      mapComponent.setContent(newFloor.image, List.of(), mapElements);
      System.out.println("FUCCCKCKCKCKCKCKCKCKCMCKn");

      // Gets all locations on the newly selected floor
      //      DBUtils.getLocationsOnFloor(newFloor.dbKey)
      //          // And iterates over them
      //          .forEach(
      //              (l) -> {
      //                List<MedEquip> equip = DBUtils.getEquipmentAtLocation(l);
      //                Set<String> equipTypes =
      //                    equip.stream().map(MedEquip::getEquipType).collect(Collectors.toSet());
      //
      //                boolean hasEquipment = equip.size() > 0;
      //
      //                // equipTypes: list of equipment types in the location.  if room has
      // multiple of one
      //                // type, only 1 element is in the list still
      //                // equip: list of all equipment objects in the location
      //                // hasEquipment: true if there is 1 or more equipment in location
      //
      //                // Checks if the point is in a valid position
      //                if (isValidPlacement(l)) {
      //                  // Create the circle for this location and add context menu handlers to it
      //                  Circle c =
      //                      new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX,
      // CIRCLE_PAINT);
      //
      //                  Pane i = new Pane();
      //                  Circle frame = new Circle(iconDim / 2, iconDim / 2, iconDim / 2,
      // Color.NAVY);
      //
      //                  i.setPrefWidth(iconDim);
      //                  i.setPrefHeight(iconDim);
      //                  i.setTranslateX(l.getXCoord() - iconDim / 2);
      //                  i.setTranslateY(l.getYCoord() - iconDim / 2);
      //                  i.setTranslateY(l.getYCoord() - iconDim / 2);
      //                  i.getChildren().add(frame);
      //
      //                  // ImageView iconView = new ImageView(icon);
      //                  // iconView.setTranslateX((Integer) ((iconDim - logoDim) / 2));
      //                  // iconView.setTranslateY((Integer) ((iconDim - logoDim) / 2));
      //                  // i.getChildren().add(iconView);
      //                  // Create context menu for shape
      //                  ContextMenu rightClickMenu = new ContextMenu();
      //                  MenuItem editItem = new MenuItem("Edit");
      //                  MenuItem deleteItem = new MenuItem("Delete");
      //                  MenuItem showEquipment = new MenuItem("Show Equipment");
      //
      //                  if (hasEquipment) {
      //                    rightClickMenu.getItems().addAll(editItem, deleteItem, showEquipment);
      //                  } else {
      //                    rightClickMenu.getItems().addAll(editItem, deleteItem);
      //                  }
      //                  editItem.setOnAction(
      //                      e -> {
      //                        if (showEditDialog(l)) {
      //                          DBManager.update(l);
      //                        }
      //                      });
      //
      //                  deleteItem.setOnAction(
      //                      e -> {
      //                        DBManager.delete(l);
      //                        // Reload data from DB to prevent desync
      //                        switchMap(lastFloor, mapMode);
      //                      });
      //
      //                  showEquipment.setOnAction(
      //                      e -> {
      //                        // TODO do something better with this
      //
      //                        Alert a = new Alert(Alert.AlertType.INFORMATION);
      //                        a.setContentText(equip.toString());
      //                        a.setHeaderText("Equipment at this location");
      //                        a.show();
      //                        //                        ContextMenu equipmentAtLocation = new
      //                        // ContextMenu();
      //                        //                        for (MedEquip med : equip) {
      //                        //
      // equipmentAtLocation.getItems().addAll(new
      //                        // MenuItem(med.toString()));
      //                        //                        }
      //                        //                        equipmentAtLocation.show(i,
      // e.getScreenX(),
      //                        // e.getScreenY());
      //                      });
      //
      //                  i.setOnContextMenuRequested(
      //                      e -> rightClickMenu.show(i, e.getScreenX(), e.getScreenY()));
      //                }
      //              });
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Switch to new background image
    imageView.setImage(floorImages.get(newFloor));
  }

  public void initialize() throws IOException {

    modeBox.setItems(FXCollections.observableArrayList(MapMode.values()));
    modeBox.setValue(MapMode.LOCATION);
    modeBox.setOnAction((e) -> System.out.println(modeBox.getValue()));

    mapRoot.getChildren().add(0, mapComponent.getRootPane());

    floorLL1Button.setOnAction(e -> switchMap(Floors.LOWER_LEVEL_1, mapMode));
    floorLL2Button.setOnAction(e -> switchMap(Floors.LOWER_LEVEL_2, mapMode));
    floor1Button.setOnAction(e -> switchMap(Floors.FIRST_FLOOR, mapMode));
    floor2Button.setOnAction(e -> switchMap(Floors.SECOND_FLOOR, mapMode));
    floor3Button.setOnAction(e -> switchMap(Floors.THIRD_FLOOR, mapMode));
    floor4Button.setOnAction(e -> switchMap(Floors.FOURTH_FLOOR, mapMode));
    floor5Button.setOnAction(e -> switchMap(Floors.FIFTH_FLOOR, mapMode));

    // Load initial floor and mode
    switchMap(lastFloor, MapMode.LOCATION);
  }

  public void exit() {
    locationInfoPane.setVisible(false);
  }
}
