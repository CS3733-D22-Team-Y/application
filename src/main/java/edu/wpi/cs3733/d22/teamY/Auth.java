package edu.wpi.cs3733.d22.teamY;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.TextInputDialog;

/**
 * This class is used to authenticate users. hopefully this will be replaced with something better?
 */
public class Auth {

  public static String getCode() {
    return String.format("%06d", new Random().nextInt(999999));
  }

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
      case "twilio":
        return doTwilioAuth(auth[1]);
      case "mail":
        return doMailAuth(auth[1]);
      default: // if unrecoginzed auth type return false
        System.out.println("Unrecognized auth type: " + authType);
        return false;
    }
  }

  public static boolean checkPopUp(String code) {
    TextInputDialog nodeDialog = new TextInputDialog();
    nodeDialog.setHeaderText("Enter Auth Code");
    Optional<String> result = nodeDialog.showAndWait();
    return result.filter(code::equals).isPresent();
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
      String code = getCode();
      ;
      URL url =
          new URL(
              "https://fa-push-bullet.nathanp.workers.dev/?code="
                  + code
                  + "&key=o.Z40B7DXfzzcyJnnGVwOU61vEx9iLazRQ");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("accept", "application/json");

      InputStream responseStream = connection.getInputStream();
      return checkPopUp(code);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    return false;
  }

  // TODO move api key out of plain text
  // maybe move on a worker server
  public static boolean doTwilioAuth(String number) {
    String code = getCode();
    Twilio.init("AC3a7833108f9d83910c973e3e6bf5cf85", "a7ffa173d7aabf10f8706747e18b44c5");
    Message message =
        Message.creator(
                new com.twilio.type.PhoneNumber(number),
                new com.twilio.type.PhoneNumber("+17579822979"),
                "Auth Code: \n" + code)
            .create();
    return checkPopUp(code);
  }

  public static boolean doMailAuth(String toEmail) {
    String code = getCode();
    MailService.sendMessage(
        toEmail,
        "Auth Code",
        "Use the following code to login:\n" + code + "\n\n" + "Thank you, \n" + "Team Y");
    return checkPopUp(code);
  }
}
