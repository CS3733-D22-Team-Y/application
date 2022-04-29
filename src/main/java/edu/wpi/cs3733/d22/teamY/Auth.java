package edu.wpi.cs3733.d22.teamY;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * This class is used to authenticate users. hopefully this will be replaced with something better?
 */
public class Auth {

  public static String getCode() {
    return String.format("%06d", new Random().nextInt(999999));
  }

    public static String[] getKeys(String userName) {
    // get user with userName
    List<Employee> emps =
        DBManager.getAll(Employee.class, new Where(Employee.USERNAME, userName.hashCode()));
    Employee emp = emps.get(0);
    String authString = emp.getAuthString();

    String[] split = authString.split(";");
    String[] list = new String[split.length];
    for (int i = 0; i < split.length; i++) {
      list[i] = split[i].split(":")[0];
      System.out.println(list[i]);
    }

    return list;
  }

  public static String[] getAtts(String key, String userName) {
    List<Employee> emps =
        DBManager.getAll(Employee.class, new Where(Employee.USERNAME, userName.hashCode()));
    Employee emp = emps.get(0);
    String authString = emp.getAuthString();
    ArrayList<String> args = new ArrayList<>();
    String[] split = authString.split(";");
    for (int i = 0; i < split.length; i++) {
      String[] split2 = split[i].split(":");
      if (split2[0].equals(key)) {
        for (int j = 1; j < split2.length; j++) {
          args.add(split2[j]);
        }
      }
    }
    String[] list = new String[args.size()];
    for (int i = 0; i < args.size(); i++) {
      list[i] = args.get(i);
    }
    return list;
  }

  public static boolean vaildYubikey(String key) throws Exception {
    Boolean valid = false;
    UUID randomUUID = UUID.randomUUID();
    String query =
        "https://api.yubico.com/wsapi/2.0/verify?otp="
            + key
            + "&id=73695&nonce="
            + randomAlphabetic(20).toUpperCase(Locale.ROOT);
    if (getHTML(query).equals("OK")) {
      return true;
    }
    return false;
  }

  public static String getHTML(String urlToRead) throws Exception {
    StringBuilder result = new StringBuilder();
    URL url = new URL(urlToRead);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      for (String line; (line = reader.readLine()) != null; ) {
        if (line.contains("status")) {
          result.append(line.replaceAll("status=", ""));
        }
      }
    }
    return result.toString();
  }

  /**
   * Sends a pushbullet notification to the user with the given authString and checks if the user
   * entered the correct code
   *
   * @param apiKey the api key of the user
   * @return true if the user entered the correct code, false otherwise
   */
  public static void doPushBulletAuth(String code, String apiKey) {
    try {;
      URL url =
          new URL("https://fa-push-bullet.nathanp.workers.dev/?code=" + code + "&key=" + apiKey);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("accept", "application/json");

      InputStream responseStream = connection.getInputStream();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  // TODO move api key out of plain text
  // maybe move on a worker server
  public static void doTwilioAuth(String code, String number) {
    Twilio.init("AC3a7833108f9d83910c973e3e6bf5cf85", "a7ffa173d7aabf10f8706747e18b44c5");
    Message message =
        Message.creator(
                new com.twilio.type.PhoneNumber(number),
                new com.twilio.type.PhoneNumber("+17579822979"),
                "Auth Code: \n" + code)
            .create();
  }

  public static boolean doMailAuth(String code, String toEmail) {
    MailService.sendMessage(
        toEmail,
        "Auth Code",
        "Use the following code to login:\n" + code + "\n\n" + "Thank you, \n" + "Team Y");
    return true;
  }
}
