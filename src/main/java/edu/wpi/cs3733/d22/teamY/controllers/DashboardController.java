package edu.wpi.cs3733.d22.teamY.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.wpi.cs3733.d22.teamY.*;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import edu.wpi.cs3733.d22.teamY.model.Requestable;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javax.sound.sampled.*;

public class DashboardController implements IController {

  public class EquipmentMonitor {
    private EquipmentSubject thingToWatch;

    EquipmentMonitor(EquipmentSubject watch) {
      thingToWatch = watch;
      thingToWatch.attachObserver(this);
    }

    public void update() {
      updateEquipment();
    }
  }

  public interface EquipmentSubject {
    void attachObserver(EquipmentMonitor monitor);
  }

  // Radio Buttons
  public static LinkedList<String> alerts = new LinkedList<>();
  // Text inputs

  // Side bar
  private static final String key = "2cad081d3a66daa8ee82b4abeaabb308";

  @FXML private Label timeDisplay;
  @FXML private Label dateDisplay;

  @FXML private Label fahrenheit;
  @FXML private Label celsius;

  @FXML private ImageView weatherIcon;

  @FXML private AnchorPane sidebarPane;

  @FXML private HBox alertsBox;

  @FXML private TextArea testAlert;

  @FXML private Label clean1;
  @FXML private Label clean2;
  @FXML private Label clean3;
  @FXML private Label clean4;
  @FXML private Label clean5;

  @FXML private Label dirty1;
  @FXML private Label dirty2;
  @FXML private Label dirty3;
  @FXML private Label dirty4;
  @FXML private Label dirty5;

  @FXML private Group clean_l1Popup;
  @FXML private Group clean_l2Popup;
  @FXML private Group clean_l3Popup;
  @FXML private Group clean_l4Popup;
  @FXML private Group clean_l5Popup;
  @FXML private Group dirty_l1Popup;
  @FXML private Group dirty_l2Popup;
  @FXML private Group dirty_l3Popup;
  @FXML private Group dirty_l4Popup;
  @FXML private Group dirty_l5Popup;

  @FXML private TextField dirty_l1Bed;
  @FXML private TextField dirty_l1Pump;
  @FXML private TextField dirty_l1Rec;
  @FXML private TextField dirty_l1X;
  @FXML private TextField dirty_l2Bed;
  @FXML private TextField dirty_l2Pump;
  @FXML private TextField dirty_l2Rec;
  @FXML private TextField dirty_l2X;
  @FXML private TextField dirty_l3Bed;
  @FXML private TextField dirty_l3Pump;
  @FXML private TextField dirty_l3Rec;
  @FXML private TextField dirty_l3X;
  @FXML private TextField dirty_l4Bed;
  @FXML private TextField dirty_l4Pump;
  @FXML private TextField dirty_l4Rec;
  @FXML private TextField dirty_l4X;
  @FXML private TextField dirty_l5Bed;
  @FXML private TextField dirty_l5Pump;
  @FXML private TextField dirty_l5Rec;
  @FXML private TextField dirty_l5X;

  @FXML private TextField clean_l1Bed;
  @FXML private TextField clean_l1Pump;
  @FXML private TextField clean_l1Rec;
  @FXML private TextField clean_l1X;
  @FXML private TextField clean_l2Bed;
  @FXML private TextField clean_l2Pump;
  @FXML private TextField clean_l2Rec;
  @FXML private TextField clean_l2X;
  @FXML private TextField clean_l3Bed;
  @FXML private TextField clean_l3Pump;
  @FXML private TextField clean_l3Rec;
  @FXML private TextField clean_l3X;
  @FXML private TextField clean_l4Bed;
  @FXML private TextField clean_l4Pump;
  @FXML private TextField clean_l4Rec;
  @FXML private TextField clean_l4X;
  @FXML private TextField clean_l5Bed;
  @FXML private TextField clean_l5Pump;
  @FXML private TextField clean_l5Rec;
  @FXML private TextField clean_l5X;

  @FXML private Label activeRequestCount;

  @FXML private ImageView dirtyWarning1;
  @FXML private ImageView dirtyWarning2;
  @FXML private ImageView dirtyWarning3;
  @FXML private ImageView dirtyWarning4;
  @FXML private ImageView dirtyWarning5;
  @FXML private CategoryAxis categoryAxis = new CategoryAxis();
  @FXML private NumberAxis numberAxis = new NumberAxis();

  @FXML
  private BarChart<String, Integer> barChart =
      new BarChart<String, Integer>(categoryAxis, (Axis) numberAxis);

  private static boolean paused;
  private static boolean playing;
  private static boolean loaded = false;
  private static boolean shuffle = false;
  private static boolean next = false;
  private static boolean prev = false;

  // TODO add music Bitch!

  static AudioInputStream audioInputStream;
  static ArrayList<String> songs = new ArrayList<>();

  static String a = "src/main/resources/edu/wpi/cs3733/d22/teamY/Music/FurElise30.wav";
  static String b = "src/main/resources/edu/wpi/cs3733/d22/teamY/Music/MoltoVivace30.wav";
  static String c = "src/main/resources/edu/wpi/cs3733/d22/teamY/Music/Symp530.wav";

  static Clip clip;

  @FXML private AnchorPane mainPane;
  @FXML private ImageView bgImage;
  @FXML private Rectangle bgGradient;

  private Label[] floorsClean;
  private Label[] floorsDirty;
  private ImageView[] floorsDirtyWarning;

  @FXML private VBox requestBox;
  @FXML private MFXScrollPane scrollBox;

  private ArrayList<RequestSet> rqPairs;

  private Scene requestMenu = null;

  public DashboardController() {}

  private EquipmentMonitor monitor = null;

  public EquipmentMonitor initMonitor(EquipmentSubject watch) {
    monitor = new EquipmentMonitor(watch);
    return monitor;
  }

  public EquipmentMonitor getMonitor() {
    return monitor;
  }

  public void initialize() throws IOException {
    barChart.setLegendVisible(false);
    initMonitor(DBHandler.getInstance());

	checkForAlerts();

    scrollBox.setBackground(Background.EMPTY);

    rqPairs = new ArrayList<>();
    List<ServiceRequest> reqs = DBManager.getAll(ServiceRequest.class);
    if (reqs != null) {
      for (ServiceRequest req : reqs) {
        if (req.getAssignedNurse().equals(PersonalSettings.currentEmployee.getName())) {
          addRequest(req);
        }
      }
    }

    rqPairs.sort(Comparator.comparingInt(rs -> (10 - rs.getRequest().getRequestPriority())));

    for (RequestSet pair : rqPairs) {
      addToBox(pair);
    }

    // categoryAxis.setLabel("Country");
    // categoryAxis.setTickLabelRotation(90);
    int[] data = DBUtils.getAllServiceRequestsPriority();

    XYChart.Series series1 = new XYChart.Series();

    series1.getData().add(new XYChart.Data("Very High Priority", data[0]));
    series1.getData().add(new XYChart.Data("High Priority", data[1]));
    series1.getData().add(new XYChart.Data("Medium Priority", data[2]));
    series1.getData().add(new XYChart.Data("Low Priority", data[3]));
    series1.getData().add(new XYChart.Data("Very Low Priority", data[4]));
    series1.setName("Priorities");
    // Node fill = series1.getNode().lookup(".chart-series-bar-fill"); // only for AreaChart
    // Node line = series1.getNode().lookup(".chart-series-bar-line");

    // series1.getNode().setStyle("-fx-bar-fill: green;");

    barChart.getData().addAll(series1);
    songs.add(a);
    songs.add(b);
    songs.add(c);

    activeRequestCount.setText(String.valueOf(DBUtils.getRequestCount()));
    floorsClean =
        new Label[] {
          clean1, clean2, clean3, clean4, clean5,
        };

    floorsDirty =
        new Label[] {
          dirty1, dirty2, dirty3, dirty4, dirty5,
        };

    floorsDirtyWarning =
        new ImageView[] {
          dirtyWarning1, dirtyWarning2, dirtyWarning3, dirtyWarning4, dirtyWarning5,
        };

    AnimationTimer timer =
        new AnimationTimer() {
          private long lastUpdate = 0;

          @Override
          public void handle(long now) {
            timeDisplay.setText(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mma")).toLowerCase());
            dateDisplay.setText(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, LLL d")));
            if (now - lastUpdate >= 60000000000L) {
              updateWeather();
              lastUpdate = now;
            }
          }
        };
    updateWeather();
    updateEquipment();
    timer.start();

    // requestBox.setPrefHeight(1000);
    alertsBox.setBackground(Background.EMPTY);

    NewSceneLoading.loadSidebar(sidebarPane);
  }

  private void addRequest(ServiceRequest req) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/DashboardServiceRequest.fxml"));
    Pane pane = loader.load();
    // SingularServiceRequestController controller = loader.getController();
    SingularServiceRequestController controller =
        ActiveServiceRequestController.requestControllers.getLast();
    // System.out.println(ActiveServiceRequestController.requestControllers.getLast());

    rqPairs.add(new RequestSet(controller, pane, req));
  }

  private void addToBox(RequestSet rqSet) {
    requestBox.getChildren().add(rqSet.getPane());
    rqSet.getController().populateFromRequest(rqSet.getRequest());
  }

  private void checkForAlerts() {
    if (DBUtils.checkAvailableEquipmentOnFloor("3", "PUMP") < 5) {

      addToAlertsBox("Floor 3 has less than 5 Infusion Pumps");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "supply an infusion pump to floor 3",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"PUMP"}));
    }
    if (DBUtils.checkAvailableEquipmentOnFloor("4", "PUMP") < 5) {

      addToAlertsBox("Floor 4 has less than 5 Infusion Pumps");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "supply an infusion pump to floor 4",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"PUMP"}));
    }
    if (DBUtils.checkAvailableEquipmentOnFloor("5", "PUMP") < 5) {

      addToAlertsBox("Floor 5 has less than 5 Infusion Pumps");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "supply an infusion pump to floor 5",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"PUMP"}));
    }

    if (DBUtils.checkUnavailableEquipmentOnFloor("3", "PUMP") >= 10) {

      addToAlertsBox("Floor 3 has 10 dirty Infusion Pumps");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "clean an infusion pump on floor 3",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"PUMP"}));
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("4", "PUMP") >= 10) {

      addToAlertsBox("Floor 4 has 10 dirty Infusion Pumps");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "clean an infusion pump on floor 4",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"PUMP"}));
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("5", "PUMP") >= 10) {

      addToAlertsBox("Floor 5 has 10 dirty Infusion Pumps");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "clean an infusion pump on floor 5",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"PUMP"}));
    }

    if (DBUtils.checkUnavailableEquipmentOnFloor("3", "BED") >= 6) {

      addToAlertsBox("Floor 3 has 6 dirty beds");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "clean a bed on floor 3",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"BED"}));
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("4", "BED") >= 6) {

      addToAlertsBox("Floor 4 has 6 dirty beds");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "clean a bed on floor 4",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"BED"}));
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("5", "BED") >= 6) {

      addToAlertsBox("Floor 5 has 6 dirty beds");
      DBManager.save(
          new ServiceRequest(
              RequestTypes.MEDEQUIP,
              "",
              "",
              "clean a bed on floor 5",
              1,
              RequestStatus.INCOMPLETE,
              new String[] {"BED"}));
    }
  }

  private void updateEquipment() {
    TextField[][] cleanFields =
        new TextField[][] {
          {clean_l1Bed, clean_l1Pump, clean_l1Rec, clean_l1X},
          {clean_l2Bed, clean_l2Pump, clean_l2Rec, clean_l2X},
          {clean_l3Bed, clean_l3Pump, clean_l3Rec, clean_l3X},
          {clean_l4Bed, clean_l4Pump, clean_l4Rec, clean_l4X},
          {clean_l5Bed, clean_l5Pump, clean_l5Rec, clean_l5X}
        };
    TextField[][] dirtyFields =
        new TextField[][] {
          {dirty_l1Bed, dirty_l1Pump, dirty_l1Rec, dirty_l1X},
          {dirty_l2Bed, dirty_l2Pump, dirty_l2Rec, dirty_l2X},
          {dirty_l3Bed, dirty_l3Pump, dirty_l3Rec, dirty_l3X},
          {dirty_l4Bed, dirty_l4Pump, dirty_l4Rec, dirty_l4X},
          {dirty_l5Bed, dirty_l5Pump, dirty_l5Rec, dirty_l5X}
        };

    for (int i = 0; i < floorsClean.length; i++) {
      List<MedEquip> cleanEq = DBUtils.findAllOfStatusOnFloor(Integer.toString(i + 1), "1");
      List<MedEquip> dirtyEq = DBUtils.findAllOfStatusOnFloor(Integer.toString(i + 1), "0");

      floorsClean[i].setText(String.valueOf(cleanEq.size()));
      floorsDirty[i].setText(String.valueOf(dirtyEq.size()));

      floorsDirtyWarning[i].setVisible(dirtyEq.size() >= cleanEq.size() && dirtyEq.size() >= 5);

      setPopupText(cleanFields, i, cleanEq);
      setPopupText(dirtyFields, i, dirtyEq);
    }
  }

  private void setPopupText(TextField[][] fields, int i, List<MedEquip> list) {
    fields[i][0].setText(String.valueOf(numberOfTypeFromList(list, "BED")));
    fields[i][1].setText(String.valueOf(numberOfTypeFromList(list, "PUMP")));
    fields[i][2].setText(String.valueOf(numberOfTypeFromList(list, "RECLINER")));
    fields[i][3].setText(String.valueOf(numberOfTypeFromList(list, "XRAY")));
  }

  private int numberOfTypeFromList(List<MedEquip> list, String type) {
    int count = 0;
    for (MedEquip m : list) {
      if (Objects.equals(m.getEquipType(), type)) {
        count++;
      }
    }

    return count;
  }

  private void updateWeather() {
    try {
      URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Boston&appid=" + key);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.connect();

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder resp = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        resp.append(inputLine);
      }
      in.close();

      JsonObject json = JsonParser.parseString(resp.toString()).getAsJsonObject();

      int weatherID =
          json.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
      float temperature = json.get("main").getAsJsonObject().get("temp").getAsFloat();
      long sRise = json.get("sys").getAsJsonObject().get("sunrise").getAsLong();
      long sSet = json.get("sys").getAsJsonObject().get("sunset").getAsLong();

      long tFah = Math.round((((temperature - 273.15) * 9) / 5) + 32);
      long tCel = Math.round(temperature - 273.15);

      fahrenheit.setText(String.valueOf(tFah));
      celsius.setText(String.valueOf(tCel));

      LocalDateTime ldt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

      Date now = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
      Date sunRise = new java.util.Date(sRise * 1000);
      Date sunSet = new java.util.Date(sSet * 1000);

      boolean isDay = now.after(sunRise) && now.before(sunSet);

      weatherIcon.setImage(
          new Image(String.valueOf(App.class.getResource(weatherImagePath(weatherID, isDay)))));

      // System.out.println("Weather updated successfully!!!");
    } catch (Exception ignored) {
      // ignored.printStackTrace();
    }
  }

  public static void reloadDashboard() {}

  private void addRequest(Requestable req) throws IOException {

    // System.out.println(ActiveServiceRequestController.requestControllers.getLast());

    // rqPairs.add(new RequestSet(controller, pane, req));
  }

  private void addToAlertsBox(String inputText) {

    // Label test = new Label("test");
    // test.setBackground(new Background(new BackgroundFill(Paint.TRANSLUCENT, CornerRadii.EMPTY,
    // Insets.EMPTY)));
    Label testLabel = new Label(inputText);
    testLabel.setStyle("-fx-font-size: 20");
    testLabel.setStyle("-fx-border-color: black;");
    testLabel.setMinWidth(262);

    alertsBox.getChildren().add(testLabel);

    // rqSet.getController().populateFromRequestable(rqSet.getRequest());

  }

  private String weatherImagePath(int id, boolean daytime) {
    String path = "views/images/icons/dash/weather/";
    String im = "unknown";
    String timeStr = daytime ? "_day" : "_night";
    switch (id / 100) {
      case 2:
        im = "thunder";
        break;
      case 3:
        im = "rain";
        break;
      case 5:
        switch (id / 10) {
          case 50:
            im = ("rain" + timeStr);
            break;
          case 51:
            im = "snow";
            break;
          case 52:
          case 53:
            im = "rain";
            break;
        }
        break;
      case 6:
        im = "snow";
        break;
      case 7:
        im = "fog";
        break;
      case 8:
        switch (id) {
          case 800:
            im = ("clear" + timeStr);
            break;
          case 801:
            im = ("partlycloudy" + timeStr);
            break;
          case 802:
            im = "cloudy";
            break;
          case 803:
          case 804:
            im = "stormclouds";
            break;
        }
        break;
    }
    return path + im + ".png";
  }

  @FXML
  public void play() {
    if (shuffle) {
      shuffle = false;
      playSong(getRanSong());
    } else if (next) {
      next = false;
      playSong(getNext());
    } else if (prev) {
      prev = false;
      playSong(getBack());
    } else {
      playSong(b);
    }
  }

  @FXML
  public void pause() {
    clip.stop();
    playing = false;
    paused = true;
  }

  @FXML
  public void next() {
    next = true;
    if (playing) {
      clip.stop();
      clip.close();
      playing = false;
    }
    loaded = false;
    play();
  }

  @FXML
  public void back() {
    prev = true;
    if (playing) {
      clip.stop();
      clip.close();
      playing = false;
    }
    loaded = false;
    play();
  }

  @FXML
  public void shuffle() {
    shuffle = true;
    if (playing) {
      clip.stop();
      clip.close();
      playing = false;
    }
    loaded = false;
    play();
  }

  public static void playSong(String p) {
    try {
      File file = new File(p);
      if (file.exists()) {
        if (!playing && !loaded) {
          playing = true;
          audioInputStream = AudioSystem.getAudioInputStream(file);
          clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          audioInputStream.close();
          clip.start();
          loaded = true;
        } else if (!playing) {
          clip.start();
          playing = true;
        }
      } else System.out.println("File Not Found");
    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
      ex.printStackTrace();
    }
  }

  private static int positionChosenBefore = 0;
  private static int ran = 0;

  public static String getRanSong() {
    while (ran == positionChosenBefore) {
      ran = (int) (Math.random() * (songs.size()));
    }

    positionChosenBefore = ran;
    return songs.get(ran);
  }

  public static String getBack() {
    if (positionChosenBefore - 1 < songs.indexOf(a)) {
      positionChosenBefore = 2;
      return c;
    } else {
      positionChosenBefore--;
    }
    return songs.get(positionChosenBefore);
  }

  public static String getNext() {
    if (positionChosenBefore + 1 > songs.indexOf(c)) {
      positionChosenBefore = 0;
      return a;
    } else {
      positionChosenBefore++;
    }
    return songs.get(positionChosenBefore);
  }

  private void updateQuickDash() {
    HashMap<String, HashMap<String, Integer>> floorCounts = DBUtils.getEquipFloorCounts();

    /*this.bedLabels[index].setText(floorCounts.get(floor).get("BED") + "");
    this.pumpLabels[index].setText(floorCounts.get(floor).get("PUMP") + "");
    this.recLabels[index].setText(floorCounts.get(floor).get("RECLINER") + "");
    this.xLabels[index].setText(floorCounts.get(floor).get("XRAY") + "");*/
  }

  @FXML
  public void L1DirtyEnter() {
    updateQuickDash();
    dirty_l1Popup.setVisible(true);
  }

  @FXML
  public void L1DirtyExit() {
    dirty_l1Popup.setVisible(false);
  }

  @FXML
  public void L2DirtyEnter() {
    updateQuickDash();
    dirty_l2Popup.setVisible(true);
  }

  @FXML
  public void L2DirtyExit() {
    dirty_l2Popup.setVisible(false);
  }

  @FXML
  public void L3DirtyEnter() {
    updateQuickDash();
    dirty_l3Popup.setVisible(true);
  }

  @FXML
  public void L3DirtyExit() {
    dirty_l3Popup.setVisible(false);
  }

  @FXML
  public void L4DirtyEnter() {
    updateQuickDash();
    dirty_l4Popup.setVisible(true);
  }

  @FXML
  public void L4DirtyExit() {
    dirty_l4Popup.setVisible(false);
  }

  @FXML
  public void L5DirtyEnter() {
    updateQuickDash();
    dirty_l5Popup.setVisible(true);
  }

  @FXML
  public void L5DirtyExit() {
    dirty_l5Popup.setVisible(false);
  }

  @FXML
  public void L1CleanEnter() {
    updateQuickDash();
    clean_l1Popup.setVisible(true);
  }

  @FXML
  public void L1CleanExit() {
    clean_l1Popup.setVisible(false);
  }

  @FXML
  public void L2CleanEnter() {
    updateQuickDash();
    clean_l2Popup.setVisible(true);
  }

  @FXML
  public void L2CleanExit() {
    clean_l2Popup.setVisible(false);
  }

  @FXML
  public void L3CleanEnter() {
    updateQuickDash();
    clean_l3Popup.setVisible(true);
  }

  @FXML
  public void L3CleanExit() {
    clean_l3Popup.setVisible(false);
  }

  @FXML
  public void L4CleanEnter() {
    updateQuickDash();
    clean_l4Popup.setVisible(true);
  }

  @FXML
  public void L4CleanExit() {
    clean_l4Popup.setVisible(false);
  }

  @FXML
  public void L5CleanEnter() {
    updateQuickDash();
    clean_l5Popup.setVisible(true);
  }

  @FXML
  public void L5CleanExit() {
    clean_l5Popup.setVisible(false);
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
