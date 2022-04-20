package edu.wpi.cs3733.d22.teamY.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashboardController {

  private static final String key = "2cad081d3a66daa8ee82b4abeaabb308";

  @FXML private Label timeDisplay;
  @FXML private Label dateDisplay;

  @FXML private Label fahrenheit;
  @FXML private Label celsius;

  @FXML private ImageView weatherIcon;

  public void initialize() throws IOException {
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
    timer.start();
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
      ignored.printStackTrace();
    }
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
}
