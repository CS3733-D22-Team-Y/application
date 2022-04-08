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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to authenticate users. hopefully this will be replaced with something better?
 */
public class Auth {

	public static String getCode() {
		return String.format("%06d", new Random().nextInt(999999));
	}

	//  public static boolean doAuth(String userName) {
	//    // get user with userName
	//    List<Employee> emps =
	//        DBManager.getAll(Employee.class, new Where(Employee.USERNAME, userName.hashCode()));
	//    if (emps.size() != 1) {
	//      System.out.println("Error: " + emps.size() + " users found with username " + userName);
	//      return false;
	//    }
	//    Employee emp = emps.get(0);
	//    String authString = emp.getAuthString();
	//
	//    // if no set authString return false
	//    if (authString == null || authString.equals("none")) {
	//      return true;
	//    }
	//
	//    String[] auth = authString.split(":");
	//    String authType = auth[0];
	//    switch (authType) {
	//      case "none":
	//        return true;
	//      case "pushbullet":
	//        return doPushBulletAuth(auth[1]);
	//      case "twilio":
	//        return doTwilioAuth(auth[1]);
	//      case "mail":
	//        return doMailAuth(auth[1]);
	//      default: // if unrecoginzed auth type return false
	//        System.out.println("Unrecognized auth type: " + authType);
	//        return false;
	//    }
	//  }

	public static String[] getKeys(String userName) {
		// get user with userName
		List<Employee> emps =
				DBManager.getAll(Employee.class, new Where(Employee.USERNAME, userName.hashCode()));
		Employee emp = emps.get(0);
		String authString = emp.getAuthString();
		String[] list = new String[Math.toIntExact(authString.chars().filter(ch -> ch == ';').count())];
		int index = 0;
		// Regex to extract the string
		// between two delimiters
		String regex = "\\;(.*?)\\:";

		// Compile the Regex.
		Pattern p = Pattern.compile(regex);

		// Find match between given string
		// and regular expression
		// using Pattern.matcher()
		Matcher m = p.matcher(authString);

		// Get the subsequence
		// using find() method
		while (m.find()) {
			System.out.println(m.group(1));
			list[index] = m.group(1);
			index++;
		}

		return list;
	}

	// Please dont read
	public static String[] getAtts(String key, String userName) {
		List<Employee> emps =
				DBManager.getAll(Employee.class, new Where(Employee.USERNAME, userName.hashCode()));
		Employee emp = emps.get(0);
		String authString = emp.getAuthString();
		try {
			System.out.println(authString.substring(0, authString.indexOf(key)));
		} catch (Exception e) {
		}
		authString =
				authString.replace(authString.substring(0, authString.indexOf(key)) + key, "") + ":";

		try {
			if (authString.contains(";"))
				authString = authString.substring(0, authString.indexOf(";") + 1);
		} catch (Exception e) {
		}
		String[] list = new String[Math.toIntExact(authString.chars().filter(ch -> ch == ':').count())];
		authString = authString.replace(";", ":");
		authString = authString.replace(":", "][");
		int index = 0;
		// Regex to extract the string
		// between two delimiters
		String regex = "\\[(.*?)\\]";

		// Compile the Regex.
		Pattern p = Pattern.compile(regex);

		// Find match between given string
		// and regular expression
		// using Pattern.matcher()
		Matcher m = p.matcher(authString);

		// Get the subsequence
		// using find() method
		while (m.find()) {
			list[index] = m.group(1);
			index++;
		}
		System.out.println(authString);
		return list;
	}

	//  public static boolean checkPopUp(String code) {
	//    TextInputDialog nodeDialog = new TextInputDialog();
	//    nodeDialog.setHeaderText("Enter Auth Code");
	//    Optional<String> result = nodeDialog.showAndWait();
	//    return result.filter(code::equals).isPresent();
	//  }

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
		try {
			;
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
