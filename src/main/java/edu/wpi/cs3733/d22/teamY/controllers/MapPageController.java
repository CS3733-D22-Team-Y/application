package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.component.MapComponent;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.Requestable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MapPageController<T extends Requestable> {

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

  // req stuff
  @FXML private MFXTextField currReqDisplay;
  @FXML private MFXTextField reqTypeBox;
  @FXML private MFXTextField reqLocationBox;
  @FXML private MFXTextField reqStatusBox;
  @FXML private MFXTextField reqNurseBox;
  @FXML private JFXTextArea reqDescriptionBox;
  @FXML private MFXButton reqSubmit;
  String currentFloor = "1";
  @FXML private Pane reqInfoPane;

  // end req stuff

  @FXML public MFXButton locationSubmit;
  private String fuck = "shit";
  private ArrayList<T> fuck2 = new ArrayList<>();
  private int currReqSelection = 0;
  // @FXML private MFXLegacyComboBox<String> modeBox;
  // @FXML private TextField selectorBoxText;
  @FXML Pane mainPane;
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

  @FXML MFXTextField equipID;
  @FXML MFXTextField equipLocation;
  @FXML MFXTextField equipType;
  @FXML MFXTextField equipClean;
  @FXML Pane equipInfoPane;
  @FXML MFXButton equipSubmit;
  @FXML MFXButton equipUp;
  @FXML MFXButton equipDown;

  @FXML private MFXCheckbox locationsCheckbox;
  @FXML private MFXCheckbox medCheckbox;
  @FXML private MFXCheckbox servicesCheckbox;

  Pane e = new Pane();

  Boolean locationDragStatus = false;

  int locationPinDefaultX = 500;
  int locationPinDefaultY = 500;
  ImageView locationPin;

  Circle locationDot = new Circle(0, 0, 0, Color.RED);

  @FXML private AnchorPane sidebarPane;

  private TextField[] reqNumLabels;
  private TextField[] bedLabels;
  private TextField[] pumpLabels;
  private TextField[] recLabels;
  private TextField[] xLabels;

  private int currentEquip = 0;
  MapComponent mapComponent = new MapComponent();

  private static final int CIRCLE_RADIUS_PX = 10;
  private static final Paint CIRCLE_PAINT = Color.RED;
  private final int pinDim = 100;
  private final int pinDim2 = 25;

  // Screen size constants
  private static final int MAP_XMIN = 0;
  private static final int MAP_YMIN = 0;
  private static final int MAP_XMAX = 700;
  private static final int MAP_YMAX = 700;
  private static final int iconDim = 50;
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
            new Image(App.class.getResource("views/images/floor1.jpg").toString()), 0, 0, 0.05)),
    LOWER_LEVEL_1(
        "L1",
        "Lower Level 1",
        new MapComponent.MapImage(
            new Image(App.class.getResource("views/images/floor-1.jpg").toString()), 0, 0, 0.05)),
    LOWER_LEVEL_2(
        "L2",
        "Lower Level 2",
        new MapComponent.MapImage(
            new Image(App.class.getResource("views/images/floor-2.jpg").toString()), 0, 0, 0.05)),
    SECOND_FLOOR(
        "2",
        "Floor 2",
        new MapComponent.MapImage(
            new Image(App.class.getResource("views/images/floor2.jpg").toString()), 0, 0, 0.05)),
    THIRD_FLOOR(
        "3",
        "Floor 3",
        new MapComponent.MapImage(
            new Image(App.class.getResource("views/images/floor3.jpg").toString()), 0, 0, 0.05)),
    FOURTH_FLOOR(
        "4",
        "Floor 4",
        new MapComponent.MapImage(
            new Image(App.class.getResource("views/images/floor4.jpg").toString()), 0, 0, 0.05)),
    FIFTH_FLOOR(
        "5",
        "Floor 5",
        new MapComponent.MapImage(
            new Image(App.class.getResource("views/images/floor5.jpg").toString()), 0, 0, 0.05));

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

  /**
   * Called whenever the floor or map mode is switched
   *
   * @param newFloor
   * @param newMode
   */
  private void switchMap(Floors newFloor, MapMode newMode) {
    lastFloor = newFloor;

    mapComponent
        .getMapPane()
        .setOnMouseClicked(
            e -> {
              if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                // TODO add building
                Location created =
                    new Location(
                        Integer.toString((int) Math.round(Math.random() * 10000)),
                        (int) Math.round(e.getX()) - (int) (48 * 1.0),
                        (int) Math.round(e.getY()) - (int) (95 * .5),
                        newFloor.dbKey,
                        " ",
                        " ",
                        " ",
                        " ");
                System.out.println((int) Math.round(e.getX()) + " " + (int) Math.round(e.getY()));

                if (created != null) {
                  // The element was created
                  try {
                    DBManager.save(created);
                    switchMap(newFloor, mapMode);
                  } catch (Exception e1) {
                    e1.printStackTrace();
                  }
                }
              }
            });

    // Load new locations from DB and create shapes for each
    try {
      // Get all locations on the floor
      List<Node> mapElements = new ArrayList<>();

      // Gets all locations on the newly selected floor
      DBUtils.getLocationsOnFloor(newFloor.dbKey)
          // And iterates over them
          .forEach(
              (l) -> {
                // System.out.println(l.getXCoord() + "," + l.getYCoord());
                List<MedEquip> equip = DBUtils.getEquipmentAtLocation(l);
                Set<String> equipTypes =
                    equip.stream().map(MedEquip::getEquipType).collect(Collectors.toSet());

                boolean hasEquipment = equip.size() > 0;
                List<T> requests = DBUtils.getAllServiceReqsAtLocation(l);

                // equipTypes: list of equipment types in the location.  if room has
                // multiple of one
                // type, only 1 element is in the list still
                // equip: list of all equipment objects in the location
                // hasEquipment: true if there is 1 or more equipment in location

                // Checks if the point is in a valid position
                // Create the circle for this location and add context menu handlers to it
                Pane i = new Pane();
                Pane j = new Pane();
                Pane k = new Pane();
                // connor why >:(
                //                  Circle c =
                //                      new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX,
                // CIRCLE_PAINT);
                i.setLayoutX(l.getXCoord());
                i.setLayoutY(l.getYCoord() - pinDim / 2);
                ImageView imageView = new ImageView();
                imageView.setImage(
                    new Image(App.class.getResource("views/images/icons/pin.png").toString()));
                imageView.setFitHeight(pinDim);
                imageView.setFitWidth(pinDim);
                //                  imageView.setLayoutX(l.getXCoord() - pinDim / 2);
                //                  imageView.setLayoutY(l.getYCoord() - pinDim / 2);

                //                  Circle frame = new Circle(iconDim / 2, iconDim / 2, iconDim /
                // 2, Color.NAVY);
                i.setPrefWidth(pinDim);
                i.setPrefHeight(pinDim);
                i.getChildren().add(imageView);
                i.visibleProperty().bind(locationsCheckbox.selectedProperty());
                mapElements.add(i);
                // Add equipment bubbles
                if (hasEquipment) {
                  Circle c =
                      new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX, CIRCLE_PAINT);
                  j.setLayoutX(l.getXCoord() + 20);
                  j.setLayoutY(l.getYCoord());
                  Circle frame = new Circle(iconDim / 2, iconDim / 2, iconDim / 2, Color.NAVY);
                  ImageView equipIcon = new ImageView();
                  if (equip.size() < 9) {
                    equipIcon.setImage(
                        new Image(
                            String.valueOf(
                                App.class
                                    .getResource("views/images/icons/" + equip.size() + ".png")
                                    .toString())));
                  } else {
                    equipIcon.setImage(
                        new Image(
                            String.valueOf(
                                App.class.getResource("views/images/icons/9.png").toString())));
                  }
                  equipIcon.setFitWidth(iconDim);
                  equipIcon.setFitHeight(iconDim);
                  j.setPrefWidth(iconDim);
                  j.setPrefHeight(iconDim);
                  j.getChildren().add(frame);
                  j.getChildren().add(equipIcon);
                  j.visibleProperty().bind(medCheckbox.selectedProperty());
                  mapElements.add(j);
                }
                // Add service request bubbles
                if (requests.size() > 0) {
                  Circle c =
                      new Circle(l.getXCoord(), l.getYCoord(), CIRCLE_RADIUS_PX, CIRCLE_PAINT);
                  k.setLayoutX(l.getXCoord() - 20);
                  k.setLayoutY(l.getYCoord());
                  Circle frame = new Circle(iconDim / 2, iconDim / 2, iconDim / 2, Color.RED);
                  k.setPrefWidth(iconDim);
                  k.setPrefHeight(iconDim);
                  k.getChildren().add(frame);
                  k.visibleProperty().bind(servicesCheckbox.selectedProperty());
                  mapElements.add(i);
                }

                i.setOnContextMenuRequested(
                    e -> {
                      if (currentEquip > equip.size() - 1) {
                        currentEquip = 0;
                      }
                      fuck = String.valueOf(l.getNodeID());
                      locationInfoPane.setVisible(true);
                      equipInfoPane.setVisible(false);
                      reqInfoPane.setVisible(false);
                      locationX.setText(String.valueOf(l.getXCoord()));
                      locationY.setText(String.valueOf(l.getYCoord()));
                      locationBuilding.setText(l.getBuilding());
                      locationShort.setText(l.getShortName());
                      locationLong.setText(l.getLongName());
                      locationID.setText(String.valueOf(l.getNodeID()));
                    });
                j.setOnContextMenuRequested(
                    e -> {
                      equipInfoPane.setVisible(true);
                      locationInfoPane.setVisible(false);
                      reqInfoPane.setVisible(false);
                      MedEquip o = equip.get(currentEquip);
                      fuck = String.valueOf(o.getEquipID());
                      equipID.setText(String.valueOf(o.getEquipID()));
                      equipLocation.setText(o.getEquipLocId());
                      equipType.setText(o.getEquipType());
                      equipClean.setText(o.getIsClean());
                    });
                k.setOnContextMenuRequested(
                    e -> {
                      if (requests.size() > 0) {
                        fuck2.clear();
                        for (T r : requests) {
                          fuck2.add(r);
                        }
                        reqInfoPane.setVisible(true);
                        locationInfoPane.setVisible(false);
                        equipInfoPane.setVisible(false);
                        this.currReqSelection %= fuck2.size();
                        currReqDisplay.setText(fuck2.get(this.currReqSelection).getTypeString());
                        this.reqLocationBox.setText(fuck2.get(this.currReqSelection).getLocID());
                        this.reqDescriptionBox.setText(
                            fuck2.get(this.currReqSelection).getAdditionalNotes());
                        this.reqStatusBox.setText(fuck2.get(this.currReqSelection).getStatus());
                        this.reqTypeBox.setText(fuck2.get(this.currReqSelection).getTypeString());
                        this.reqNurseBox.setText(
                            fuck2.get(this.currReqSelection).getAssignedNurse());
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
                      DBManager.update(fuckMe);
                      exit();
                      switchMap(newFloor, mapMode);
                    });

                reqSubmit.setOnMouseClicked(
                    e -> {
                      this.currReqSelection %= this.fuck2.size();
                      T req = fuck2.get(this.currReqSelection);
                      req.setAssignedNurse(reqNurseBox.getText());
                      // req.setStatus(reqStatusBox.getText());

                      DBManager.update(req);
                      // ("Submit");
                    });

                equipUp.setOnMouseClicked(
                    e -> {
                      currentEquip++;
                    });

                equipDown.setOnMouseClicked(
                    e -> {
                      currentEquip--;
                    });

                equipSubmit.setOnMouseClicked(
                    e -> {
                      MedEquip t =
                          new MedEquip(
                              fuck,
                              equipType.getText(),
                              equipLocation.getText(),
                              equipClean.getText(),
                              ""); // TODO fix
                      DBManager.update(t);
                      DBManager.save(t);

                      equip.add(t);
                      switchMap(newFloor, mapMode);
                      System.out.println(
                          fuck
                              + ","
                              + equipLocation.getText()
                              + ","
                              + equipType.getText()
                              + ","
                              + equipClean.getText());
                      System.out.println(equip.size());
                    });

                mapComponent.setContent(newFloor.image, List.of(), mapElements);
              });
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Switch to new background image
    imageView.setImage(floorImages.get(newFloor));
  }

  public void initialize() throws IOException {

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

    /*
    modeBox.setItems(
        FXCollections.observableArrayList("Locations", "Equipment", "Service Requests"));
    modeBox.setValue("Locations");
     */
    // selectorBoxText.setText("Locations");
    mapRoot.getChildren().add(mapComponent.getRootPane());
    // modeBox.setValue("Locations");
    floorLL1Button.setOnAction(
        e -> {
          switchMap(Floors.LOWER_LEVEL_1, mapMode);
          currentFloor = "ll1";
        });
    floorLL2Button.setOnAction(
        e -> {
          switchMap(Floors.LOWER_LEVEL_2, mapMode);
          currentFloor = "ll2";
        });
    floor1Button.setOnAction(
        e -> {
          switchMap(Floors.FIRST_FLOOR, mapMode);
          currentFloor = "1";
        });
    floor2Button.setOnAction(
        e -> {
          switchMap(Floors.SECOND_FLOOR, mapMode);
          currentFloor = "2";
        });
    floor3Button.setOnAction(
        e -> {
          switchMap(Floors.THIRD_FLOOR, mapMode);
          currentFloor = "3";
        });
    floor4Button.setOnAction(
        e -> {
          switchMap(Floors.FOURTH_FLOOR, mapMode);
          currentFloor = "4";
        });
    floor5Button.setOnAction(
        e -> {
          switchMap(Floors.FIFTH_FLOOR, mapMode);
          currentFloor = "5";
        });
    /*
    modeBox.setOnAction(
        e -> {
          selectorBoxText.setText(modeBox.getValue());
          exit();
          switchMap(lastFloor, mapMode);
        });
     */

    // Load initial floor and mode
    switchMap(lastFloor, MapMode.LOCATION);
    equipInfoPane.setVisible(false);
    reqInfoPane.setVisible(false);
    locationInfoPane.setVisible(false);

    NewSceneLoading.loadSidebar(sidebarPane);

    mapComponent
        .getMapPane()
        .setOnMouseReleased(
            e -> {
              System.out.println("Mouse released");
              if (locationDragStatus) {}
              locationDragStatus = false;
            });

    locationPin = new ImageView();
    locationPin.setImage(new Image(App.class.getResource("views/images/icons/pin.png").toString()));
    locationPin.setFitHeight(25);
    locationPin.setFitWidth(25);
    locationPin.setLayoutX(1000);
    locationPin.setLayoutY(30);
    mainPane.getChildren().add(locationPin);
    locationPin.setTranslateY(0);
    locationPin.setTranslateX(0);
    mainPane.getChildren().add(locationDot);

    locationDot.setOnMouseDragEntered(e -> {});

    locationPin.setOnMouseDragged(
        e -> {
          locationPin.setLayoutX(e.getX() + locationPin.getLayoutX() - 48 * .25);
          locationPin.setLayoutY(e.getY() + locationPin.getLayoutY() - 95 * .25);
        });

    locationPin.setOnMouseReleased(
        e -> {
          locationPin.setLayoutX(1000);
          locationPin.setLayoutY(30);
          System.out.println(currentFloor);
          Robot bot = null;
          try {
            bot = new Robot();
          } catch (AWTException ex) {
            ex.printStackTrace();
          }
          int mask = InputEvent.BUTTON1_DOWN_MASK;
          assert bot != null;
          bot.mousePress(mask);
          bot.mouseRelease(mask);

          bot.mousePress(mask);
          bot.mouseRelease(mask);
          System.out.println(e.getX() + " " + e.getY());
        });

    locationsCheckbox.setSelected(true);
    medCheckbox.setSelected(true);
    servicesCheckbox.setSelected(true);
  }

  public void exit() {
    locationInfoPane.setVisible(false);
    equipInfoPane.setVisible(false);
    reqInfoPane.setVisible(false);
  }

  public void right() {
    this.currReqSelection++;
    System.out.println("right" + currReqSelection);
    updateReqInfo();
  }

  public void left() {
    this.currReqSelection--;
    System.out.println("left" + currReqSelection);
    updateReqInfo();
  }

  public void updateReqInfo() {
    this.currReqSelection %= fuck2.size();
    currReqDisplay.setText(fuck2.get(this.currReqSelection).getTypeString());
    this.reqLocationBox.setText(fuck2.get(this.currReqSelection).getLocID());
    this.reqDescriptionBox.setText(fuck2.get(this.currReqSelection).getAdditionalNotes());
    this.reqStatusBox.setText(fuck2.get(this.currReqSelection).getStatus());
    this.reqTypeBox.setText(fuck2.get(this.currReqSelection).getTypeString());
    this.reqNurseBox.setText(fuck2.get(this.currReqSelection).getAssignedNurse());

    equipInfoPane.setVisible(false);
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

  public ArrayList<Point> getHex(int n, double r, Point center) {
    ArrayList<Point> res = new ArrayList<Point>();
    res.add(center);
    if (n != 1) {
      res.addAll(getHexRecursive(--n, r, center, 1));
    }
    return res;
  }

  private ArrayList<Point> getHexRecursive(int n, double r, Point center, int layer) {
    ArrayList<Point> res = new ArrayList<Point>();
    int num = 6 * layer;
    double dAngle = Math.toRadians(360.0 / num);
    for (int i = 0; i < num && n > 0; i++) {
      res.add(
          new Point(
              center.x + (2 * r * layer + 1) * Math.cos(dAngle * i),
              center.y + (2 * r * layer + 1) * Math.sin(dAngle * i)));
      n = n - 1;
    }
    if (n != 0) {
      res.addAll(getHexRecursive(n, r, center, ++layer));
    }
    return res;
  }

  class Point {
    double x;
    double y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
}
