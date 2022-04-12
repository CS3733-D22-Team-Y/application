package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.component.MapComponent;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import java.io.IOException;
import java.util.*;
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
  @FXML private Button floorL1Button;
  @FXML private Button floorL2Button;
  @FXML private Button floorL3Button;
  @FXML private Button floorL4Button;
  @FXML private Button floorL5Button;
  @FXML private Button floor1Button;
  @FXML private Button floor2Button;
  @FXML private Button floor3Button;
  @FXML private Button floor4Button;
  @FXML private Button floor5Button;

  @FXML private AnchorPane mapRoot;

  @FXML private ComboBox<MapMode> modeBox;

  @FXML private Pane ll1MainPane;
  @FXML private Pane ll1PopupPane;
  @FXML private Pane ll2PopupPane;
  @FXML private Pane l1PopupPane;
  @FXML private Pane l2PopupPane;
  @FXML private Pane l3PopupPane;
  @FXML private Pane l4PopupPane;
  @FXML private Pane l5PopupPane;

  @FXML private TextField ll1RequestNum;
  @FXML private TextField ll1Bed;
  @FXML private TextField ll1Pump;
  @FXML private TextField ll1Rec;
  @FXML private TextField ll1X;

  @FXML private TextField ll2RequestNum;
  @FXML private TextField ll2Bed;
  @FXML private TextField ll2Pump;
  @FXML private TextField ll2Rec;
  @FXML private TextField ll2X;

  @FXML private TextField l1RequestNum;
  @FXML private TextField l1Bed;
  @FXML private TextField l1Pump;
  @FXML private TextField l1Rec;
  @FXML private TextField l1X;

  @FXML private TextField l2RequestNum;
  @FXML private TextField l2Bed;
  @FXML private TextField l2Pump;
  @FXML private TextField l2Rec;
  @FXML private TextField l2X;

  @FXML private TextField l3RequestNum;
  @FXML private TextField l3Bed;
  @FXML private TextField l3Pump;
  @FXML private TextField l3Rec;
  @FXML private TextField l3X;

  @FXML private TextField l4RequestNum;
  @FXML private TextField l4Bed;
  @FXML private TextField l4Pump;
  @FXML private TextField l4Rec;
  @FXML private TextField l4X;

  @FXML private TextField l5RequestNum;
  @FXML private TextField l5Bed;
  @FXML private TextField l5Pump;
  @FXML private TextField l5Rec;
  @FXML private TextField l5X;

  private TextField[] reqNumLabels;
  private TextField[] bedLabels;
  private TextField[] pumpLabels;
  private TextField[] recLabels;
  private TextField[] xLabels;

  MapComponent mapComponent = new MapComponent();

  private static final int CIRCLE_RADIUS_PX = 10;
  private static final Paint CIRCLE_PAINT = Color.RED;

  // Screen size constants
  private static final int MAP_XMIN = 0;
  private static final int MAP_YMIN = 0;
  private static final int MAP_XMAX = 700;
  private static final int MAP_YMAX = 700;
  private static final int iconDim = 26;
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

  private String iconDecider(Set<String> equipTypes) {
    if (equipTypes.contains("PUMP")) {
      return dotIcons.get("PUMP");
    } else if (equipTypes.contains("RECLINER")) {
      return dotIcons.get("RECLINER");
    } else if (equipTypes.contains("BED")) {
      return dotIcons.get("BED");
    } else {
      return dotIcons.get("none");
    }
  }

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

      for (Location location : locations) {
        // Create a shape for each location
        Circle locationCircle =
            new Circle(location.getXCoord(), location.getYCoord(), 20, Color.RED);
        mapElements.add(locationCircle);
      }

      mapComponent.setContent(newFloor.image, List.of(), mapElements);

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
    floorL1Button.setOnAction(e -> switchMap(Floors.FIRST_FLOOR, mapMode));
    floorL2Button.setOnAction(e -> switchMap(Floors.SECOND_FLOOR, mapMode));
    floorL3Button.setOnAction(e -> switchMap(Floors.THIRD_FLOOR, mapMode));
    floorL4Button.setOnAction(e -> switchMap(Floors.FOURTH_FLOOR, mapMode));
    floorL5Button.setOnAction(e -> switchMap(Floors.FIFTH_FLOOR, mapMode));

    ll1PopupPane.setVisible(true);
    ll1PopupPane.setOpacity(0);

    ll2PopupPane.setVisible(true);
    ll2PopupPane.setOpacity(0);

    l1PopupPane.setVisible(true);
    l1PopupPane.setOpacity(0);

    l2PopupPane.setVisible(true);
    l2PopupPane.setOpacity(0);

    l3PopupPane.setVisible(true);
    l3PopupPane.setOpacity(0);

    l4PopupPane.setVisible(true);
    l4PopupPane.setOpacity(0);

    l5PopupPane.setVisible(true);
    l5PopupPane.setOpacity(0);

    // Load initial floor and mode
    switchMap(lastFloor, MapMode.LOCATION);

    this.reqNumLabels = new TextField[7];
    this.bedLabels = new TextField[7];
    this.pumpLabels = new TextField[7];
    this.recLabels = new TextField[7];
    this.xLabels = new TextField[7];

    this.reqNumLabels[0] = ll1RequestNum;
    this.reqNumLabels[1] = ll2RequestNum;
    this.reqNumLabels[2] = l1RequestNum;
    this.reqNumLabels[3] = l2RequestNum;
    this.reqNumLabels[4] = l3RequestNum;
    this.reqNumLabels[5] = l4RequestNum;
    this.reqNumLabels[6] = l5RequestNum;

    this.bedLabels[0] = ll1Bed;
    this.bedLabels[1] = ll2Bed;
    this.bedLabels[2] = l1Bed;
    this.bedLabels[3] = l2Bed;
    this.bedLabels[4] = l3Bed;
    this.bedLabels[5] = l4Bed;
    this.bedLabels[6] = l5Bed;

    this.pumpLabels[0] = ll1Pump;
    this.pumpLabels[1] = ll2Pump;
    this.pumpLabels[2] = l1Pump;
    this.pumpLabels[3] = l2Pump;
    this.pumpLabels[4] = l3Pump;
    this.pumpLabels[5] = l4Pump;
    this.pumpLabels[6] = l5Pump;

    this.recLabels[0] = ll1Rec;
    this.recLabels[1] = ll2Rec;
    this.recLabels[2] = l1Rec;
    this.recLabels[3] = l2Rec;
    this.recLabels[4] = l3Rec;
    this.recLabels[5] = l4Rec;
    this.recLabels[6] = l5Rec;

    this.xLabels[0] = ll1X;
    this.xLabels[1] = ll2X;
    this.xLabels[2] = l1X;
    this.xLabels[3] = l2X;
    this.xLabels[4] = l3X;
    this.xLabels[5] = l4X;
    this.xLabels[6] = l5X;
  }

  private void updateQuickDash(String floor) {
    int reqNum = DBUtils.getSumOfRequestsOnFloor(floor);
    String[] floorNames = {"L1", "L2", "1", "2", "3", "4", "5"};
    // get the index of the floor
    int index = -1;
    for (int i = 0; i < floorNames.length; i++) {
      if (floorNames[i].equals(floor)) {
        index = i;
        break;
      }
    }
    // set the text of the button
    if (index > this.reqNumLabels.length - 1 || index < 0) {
      return;
    }
    this.reqNumLabels[index].setText(reqNum + "");

    HashMap<String, HashMap<String, Integer>> floorCounts = DBUtils.getEquipFloorCounts();
    this.bedLabels[index].setText(floorCounts.get(floor).get("BED") + "");
    this.pumpLabels[index].setText(floorCounts.get(floor).get("PUMP") + "");
    this.recLabels[index].setText(floorCounts.get(floor).get("RECLINER") + "");
    this.xLabels[index].setText(floorCounts.get(floor).get("XRAY") + "");
  }

  @FXML
  public void LL1Enter() {
    updateQuickDash("L1");
    ll1PopupPane.setOpacity(1);
  }

  @FXML
  public void LL1Exit() {
    ll1PopupPane.setOpacity(0);
  }

  @FXML
  public void LL2Enter() {
    updateQuickDash("L2");
    ll2PopupPane.setOpacity(1);
  }

  @FXML
  public void LL2Exit() {
    ll2PopupPane.setOpacity(0);
  }

  @FXML
  public void L1Enter() {
    updateQuickDash("1");
    l1PopupPane.setOpacity(1);
  }

  @FXML
  public void L1Exit() {
    l1PopupPane.setOpacity(0);
  }

  @FXML
  public void L2Enter() {
    updateQuickDash("2");
    l2PopupPane.setOpacity(1);
  }

  @FXML
  public void L2Exit() {
    l2PopupPane.setOpacity(0);
  }

  @FXML
  public void L3Enter() {
    updateQuickDash("3");
    l3PopupPane.setOpacity(1);
  }

  @FXML
  public void L3Exit() {
    l3PopupPane.setOpacity(0);
  }

  @FXML
  public void L4Enter() {
    updateQuickDash("4");
    l4PopupPane.setOpacity(1);
  }

  @FXML
  public void L4Exit() {
    l4PopupPane.setOpacity(0);
  }

  @FXML
  public void L5Enter() {
    updateQuickDash("5");
    l5PopupPane.setOpacity(1);
  }

  @FXML
  public void L5Exit() {
    l5PopupPane.setOpacity(0);
  }
}
