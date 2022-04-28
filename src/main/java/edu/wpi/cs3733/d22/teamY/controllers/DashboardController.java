package edu.wpi.cs3733.d22.teamY.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.wpi.cs3733.d22.teamY.App;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.RequestTypes;
import edu.wpi.cs3733.d22.teamY.model.RequestStatus;
import edu.wpi.cs3733.d22.teamY.model.Requestable;
import edu.wpi.cs3733.d22.teamY.model.ServiceRequest;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javax.sound.sampled.*;

public class DashboardController {

  public class EquipmentMonitor {
    private Object thingToWatch;

    EquipmentMonitor(Object watch) {
      thingToWatch = watch;
    }

    public void update() {
      updateEquipment();
    }
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

  @FXML private Label activeRequestCount;

  @FXML private ImageView dirtyWarning1;
  @FXML private ImageView dirtyWarning2;
  @FXML private ImageView dirtyWarning3;
  @FXML private ImageView dirtyWarning4;
  @FXML private ImageView dirtyWarning5;

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

  private Label[] floorsClean;
  private Label[] floorsDirty;
  private ImageView[] floorsDirtyWarning;

  private Scene requestMenu = null;

  public DashboardController() {}

  private EquipmentMonitor monitor = null;

  public EquipmentMonitor initMonitor(Object watch) {
    monitor = new EquipmentMonitor(watch);
    return monitor;
  }

  public EquipmentMonitor getMonitor() {
    return monitor;
  }

  public void initialize() throws IOException {
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

    // updateEquipment();

    // requestBox.setPrefHeight(1000);
    alertsBox.setBackground(Background.EMPTY);

    if (DBUtils.checkAvailableEquipmentOnFloor("3", "PUMP") < 5) {

      addToBox("Floor 3 has less than 5 Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "supply an infusion pump to floor 3",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkAvailableEquipmentOnFloor("4", "PUMP") < 5) {

      addToBox("Floor 4 has less than 5 Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "supply an infusion pump to floor 4",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkAvailableEquipmentOnFloor("5", "PUMP") < 5) {

      addToBox("Floor 5 has less than 5 Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "supply an infusion pump to floor 5",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }

    if (DBUtils.checkUnavailableEquipmentOnFloor("3", "PUMP") >= 10) {

      addToBox("Floor 3 has 10 dirty Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean an infusion pump on floor 3",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("4", "PUMP") >= 10) {

      addToBox("Floor 4 has 10 dirty Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean an infusion pump on floor 4",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("5", "PUMP") >= 10) {

      addToBox("Floor 5 has 10 dirty Infusion Pumps");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean an infusion pump on floor 5",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }

    if (DBUtils.checkUnavailableEquipmentOnFloor("3", "BED") >= 6) {

      addToBox("Floor 3 has 6 dirty beds");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean a bed on floor 3",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("4", "BED") >= 6) {

      addToBox("Floor 4 has 6 dirty beds");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean a bed on floor 4",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }
    if (DBUtils.checkUnavailableEquipmentOnFloor("5", "BED") >= 6) {

      addToBox("Floor 5 has 6 dirty beds");
      new ServiceRequest(
          RequestTypes.MEDEQUIP,
          "",
          "",
          "clean a bed on floor 5",
          1,
          RequestStatus.INCOMPLETE,
          new String[] {"PUMP"});
    }

    NewSceneLoading.loadSidebar(sidebarPane);
  }

  private void updateEquipment() {
    for (int i = 0; i < floorsClean.length; i++) {
      int cleanEq = DBUtils.findAllOfStatusOnFloor(Integer.toString(i + 1), "1").size();
      int dirtyEq = DBUtils.findAllOfStatusOnFloor(Integer.toString(i + 1), "0").size();

      floorsClean[i].setText(String.valueOf(cleanEq));
      floorsDirty[i].setText(String.valueOf(dirtyEq));

      floorsDirtyWarning[i].setVisible(dirtyEq >= cleanEq && dirtyEq >= 5);
    }
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

  private void addToBox(String inputText) {

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
}
