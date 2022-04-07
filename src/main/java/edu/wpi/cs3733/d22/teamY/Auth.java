package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.TextInputDialog;

/**
 * This class is used to authenticate users.
 * hopefully this will be replaced with something better?
 */
public class Auth {

  public static boolean doAuth(String userName) {
    // get user with userName
    List<Employee> emps =
        DBManager.getAll(Employee.class, new Where(Employee.USERNAME, userName.hashCode()));
    if (emps.size() != 1) {
      System.out.println("Error: " + emps.size() + " users found with username " + userName);
      return false;
    }
    Employee emp = emps.get(0);
    String authString = emp.getAuthString();

    // if no set authString return false
    if (authString == null || authString.equals("none")) {
      return true;
    }

    String[] auth = authString.split(":");
    String authType = auth[0];
    switch (authType) {
      case "none":
        return true;
      case "pushbullet":
        return doPushBulletAuth(auth[1]);
      default: // if unrecoginzed auth type return false
        System.out.println("Unrecognized auth type: " + authType);
        return false;
    }
  }

  /**
   * Sends a pushbullet notification to the user with the given authString and checks if the user
   * entered the correct code
   *
   * @param apiKey the api key of the user
   * @return true if the user entered the correct code, false otherwise
   */
  public static boolean doPushBulletAuth(String apiKey) {
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
