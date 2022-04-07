package edu.wpi.cs3733.d22.teamY;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.TextInputDialog;

public class Auth {

  public static boolean doPushBulletAuth() {
    // send a request to url https://fa-push-bullet.nathanp.workers.dev/
    // return true if the request is successful
    try {
      String code = String.format("%06d", new Random().nextInt(999999));
      ;
      URL url =
          new URL(
              "https://fa-push-bullet.nathanp.workers.dev/?code="
                  + code
                  + "&key=o.Z40B7DXfzzcyJnnGVwOU61vEx9iLazRQ");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("accept", "application/json");

      InputStream responseStream = connection.getInputStream();

      TextInputDialog nodeDialog = new TextInputDialog();
      nodeDialog.setHeaderText("Enter Auth Code");
      Optional<String> result = nodeDialog.showAndWait();
      if (result.isPresent()) {
        return code.equals(result.get());
      }
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
    return false;
  }
}
