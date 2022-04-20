package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.controllers.PersonalSettings;
import edu.wpi.cs3733.d22.teamY.controllers.PersonalSettingsController;
import edu.wpi.cs3733.d22.teamY.model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;
import org.hibernate.Session;

/** This class is used to create specific queries for the database. */
public class DBUtils {

  private DBUtils() {}

  public static List<Location> getLocationsOnFloor(String floor) {
    return DBManager.getAll(Location.class, new Where(Location.FLOOR, floor));
  }

  public static List<MedEquip> getEquipmentAtLocation(Location location) {
    return DBManager.getAll(MedEquip.class, new Where(MedEquip.EQUIP_LOC_ID, location.getNodeID()));
  }

  /**
   * Refresh Data from CSV
   *
   * @param e Entry type to Refresh
   */
  public static void refreshFromCSV(EntryType e) {
    // Reinitialize
    CSVBackup.loadFromCSV(e);
  }

  /**
   * Deletes all objects from a specified EntryType table
   *
   * @param e Entry Type to delete
   */
  public static void deleteType(EntryType e) {
    List<StringArrayConv> list = DBManager.getAll(EntryType.LOCATION.getEntryClass());
    // Check if null
    if (list == null) {
      return;
    }
    // Delete All from Location List
    for (StringArrayConv o : list) {
      DBManager.delete(o);
    }
    System.out.println("deleted all");
  }

  public static void completeCSVRefresh() {
    for (EntryType e : EntryType.values()) {
      deleteType(e);
    }

    for (EntryType e : EntryType.values()) {
      refreshFromCSV(e);
    }
  }

  @SuppressWarnings("Unchecked")
  public static Pair<Integer, Integer> getAvailableEquipment(String equipType) {
    List<MedEquip> equipment =
        DBManager.getAll(MedEquip.class, new Where(MedEquip.EQUIP_TYPE, equipType));

    if (equipment == null) {
      return null;
    }

    int total = equipment.size();
    int available = 0;

    for (MedEquip i : equipment) {
      if (i.getIsClean().equals("1")) {
        available++;
      }
    }
    Pair<Integer, Integer> returnPair = new Pair<>(available, total);
    return returnPair;
  }

  // find MedEquip object
  @SuppressWarnings("unchecked")
  public static void updateCleanStatus(String equipType, String locationID) {
    Session s = SessionManager.getSession();
    List<MedEquip> equipment =
        s.createQuery("from MedEquip where equipType = :equipType and isClean = '1'")
            .setParameter("equipType", equipType)
            .list();
    s.close();

    if (equipment.size() == 0) {
      return;
    }

    MedEquip thisEquip = equipment.get(0);

    thisEquip.setEquipLocId(locationID);
    thisEquip.setIsClean("0");

    DBManager.update(thisEquip);
  }

  // Gets an employee's legal name from their ID
  @SuppressWarnings("Unchecked")
  public static String getNameFromID(String id) {
    Session s = SessionManager.getSession();
    List<Employee> people =
        s.createQuery("from Employee where username = :id")
            .setParameter("id", id.hashCode() + "")
            .list();
    s.close();

    if (people.size() == 0) {
      return "";
    }

    Employee thePerson = people.get(0);
    PersonalSettingsController.currentEmployee = thePerson; // TODO change
    //    try {
    ////      Firebase.init();
    ////    } catch (IOException e) {
    ////      e.printStackTrace();
    ////    }
    ////    System.out.println("Firebase initialized");
    return thePerson.getName();
  }

  // Gets an employee's preferred name from their ID
  @SuppressWarnings("Unchecked")
  public static String getPrefNameFromID(String id) throws IOException {
    Session s = SessionManager.getSession();
    List<Employee> people =
        s.createQuery("from Employee where username = :id")
            .setParameter("id", id.hashCode() + "")
            .list();
    s.close();

    if (people.size() == 0) {
      return "";
    }

    Employee thePerson = people.get(0);
    PersonalSettingsController.currentEmployee = thePerson; // TODO change
    //    Firebase.init();
    //    System.out.println("Firebase initialized");

    return thePerson.getPrefName();
  }

  public static String getNameFromActualID(String id) throws IOException {
    Session s = SessionManager.getSession();
    List<Employee> people =
        s.createQuery("from Employee where id = :id").setParameter("id", id).list();
    s.close();

    if (people.size() == 0) {
      return "";
    }

    Employee thePerson = people.get(0);

    return thePerson.getName();
  }

  public static String getNamesFromIds(ArrayList<String> ids, boolean excludeMe)
      throws IOException {
    StringBuilder sb = new StringBuilder();

    for (String id : ids) {
      if (excludeMe && id.equals(PersonalSettingsController.currentEmployee.getIDNumber())) {
        continue;
      }
      String name = getNameFromActualID(id);
      if (name.equals("")) {
        name = "ID# " + id;
      }
      sb.append(name);
      sb.append(", ");
    }
    if (sb.length() > 0) {
      sb.delete(sb.length() - 2, sb.length());
    }
    return sb.toString();
  }

  /**
   * Returns next request number
   *
   * @param e EntryType of Object to Use
   * @return Number of Next Request
   */
  @SuppressWarnings("Unchecked")
  public static int getNextRequestNum(EntryType e) {
    Session s = SessionManager.getSession();
    int count =
        ((Long)
                s.createQuery("select count(*) from " + e.getEntryClass().getSimpleName())
                    .uniqueResult())
            .intValue();
    s.close();
    return (++count);
  }

  /**
   * Returns if a valid login was made.
   *
   * @param username the username of the employee
   * @param password the password of the employee
   * @return true if the employee had valid credentials, false otherwise
   */
  @SuppressWarnings("Unchecked")
  public static boolean isValidLogin(String username, String password) throws IOException {
    Session s = SessionManager.getSession();
    // search for the employee with the given username and password
    List<Employee> employees =
        s.createQuery("from Employee where username = :username and password = :password")
            .setParameter("username", username.hashCode() + "")
            .setParameter("password", password.hashCode() + "")
            .list();
    s.close();
    if (employees.size() == 1) {
      PersonalSettings.currentEmployee = employees.get(0);
      System.out.println("Valid Login");
      return true;
    }
    return false;
  }

  /**
   * Returns a list of Nurses from DB.
   * @return List of Nurses
   */
  public static List<Employee> getAllNurses(){
    Session s = SessionManager.getSession();

    List<Employee> nurses =
        s.createQuery("from Employee where role = :role")
            .setParameter("role", "Nurse" + "")
            .list();
    s.close();
    if(nurses.size() < 1){
      return null;
    }
    return nurses;
  }

  /**
   * Returns if username already exists.
   *
   * @param hashedUsername hashed Username INTEGER
   * @return true if valid username, false if one exists.
   */
  @SuppressWarnings("Unchecked")
  public static boolean doesUserExist(int hashedUsername) {
    Session s = SessionManager.getSession();

    List<Employee> employees =
        s.createQuery("from Employee where username = :username")
            .setParameter("username", String.valueOf(hashedUsername))
            .list();
    s.close();
    return employees.size() == 0;
  }

  @SuppressWarnings("Unchecked")
  public static String convertNameToID(String shortName) {
    Session s = SessionManager.getSession();
    List<Location> tempLocations =
        s.createQuery("from Location where shortName =:shortName")
            .setParameter("shortName", shortName)
            .list();
    s.close();

    if (tempLocations.size() > 1) {
      return null;
    }

    return (tempLocations.get(0).getNodeID());
  }

  @SuppressWarnings("Unchecked")
  public static String convertIDToName(String nodeID) {
    Session s = SessionManager.getSession();
    List<Location> tempLocations =
        s.createQuery("from Location where nodeID = :nodeID").setParameter("nodeID", nodeID).list();
    s.close();

    if (tempLocations.size() != 1) {
      return "null";
    }

    return (tempLocations.get(0).getShortName());
  }

  /**
   * Changes an employee's password.
   *
   * @param username the username of the employee | hash in string form
   * @param oldPassword the old password of the employee | hash in string form
   * @param newPassword the new password of the employee | new pass in plain text
   * @return String relating to the success of the change
   */
  @SuppressWarnings("unchecked")
  public static String changePassword(String username, String oldPassword, String newPassword) {
    Session s = SessionManager.getSession();
    List<Employee> employees =
        s.createQuery("from Employee where username = :username")
            .setParameter("username", username)
            .list();
    s.close();
    // if the employee exists
    if (employees.size() != 1) {
      return "Error: Invalid username.";
    }

    Employee employee = employees.get(0);

    // check validity of old password and new password
    if (!employee.getPassword().equals(oldPassword)) {
      return "Error: Invalid old password.";
    }
    if (oldPassword.equals(newPassword)) {
      return "Error: New password cannot be the same as old password.";
    }
    if (!Employee.isValidNewPassword(newPassword)) {
      return "Error: New password must be at least 5 characters long and contain at least one number, one letter, and one special character.";
    }

    employee.setPassword(newPassword.hashCode() + "");
    DBManager.update(employee);
    return "Successfully changed password.";
  }

  /**
   * Switches the DB to and from Client-Server -> Embedded
   *
   * @param input false for Embedded, true for C-S
   */
  public static void switchDBType(String input) {
    SessionManager.switchType(input);
    DBUtils.completeCSVRefresh();
  }

  /**
   * Checks if logged in user has the default password
   *
   * @param passwordHash hashed pass INTEGER
   * @return true if users' pass is the default
   */
  public static boolean checkDefaultPassword(int passwordHash) {
    String defaultPass = "1234";
    return (passwordHash == defaultPass.hashCode());
  }

  public static <T extends Requestable> List<T> getRequestsOnFloor(
      Class<T> requestType, String floor) {
    List<T> requests = DBManager.getAll(requestType);
    if (requests.size() == 0) {
      return requests;
    }
    List<T> filtered = new ArrayList<>();
    List<Location> locations = DBUtils.getLocationsOnFloor(floor);
    for (T r : requests) {
      String locID = r.getLocID();
      for (Location l : locations) {
        if (l.getNodeID().equals(locID)) {
          filtered.add(r);
        }
      }
    }
    return filtered;
  }

  public static <T extends Requestable> List<T> serviceReqsAtLocation(Class<?> type, Location l) {
    List<T> requests = DBManager.getAll(type);
    List<T> filtered = new ArrayList<>();
    for (T r : requests) {
      if (r.getLocID().equals(l.getNodeID())) {
        filtered.add(r);
      }
    }
    return filtered;
  }

  public static <T extends Requestable> List<T> getAllServiceReqsAtLocation(Location l) {
    List<T> requests = new ArrayList<>();
    for (RequestType rt : RequestType.values()) {
      requests.addAll(serviceReqsAtLocation(rt.requestClass, l));
    }
    return requests;
  }

  public static HashMap<String, HashMap<String, Integer>> getEquipFloorCounts() {
    // floor can be index, then need hashmap for each equipment type
    HashMap<String, HashMap<String, Integer>> equipFloorCounts = new HashMap<>();

    String[] floorNames = {"L1", "L2", "1", "2", "3", "4", "5"};
    for (String floor : floorNames) {
      HashMap<String, Integer> floorCounts = new HashMap<>();
      floorCounts.put("BED", 0);
      floorCounts.put("PUMP", 0);
      floorCounts.put("RECLINER", 0);
      floorCounts.put("XRAY", 0);
      equipFloorCounts.put(floor, floorCounts);
    }
    // go through each piece of equipment
    List<MedEquip> equip = DBManager.getAll(MedEquip.class);
    List<Location> locations = DBManager.getAll(Location.class);
    for (MedEquip e : equip) {
      // find what floor it is on
      String locationID = e.getEquipLocId();
      for (Location l : locations) {
        if (l.getNodeID().equals(locationID)) {
          int prevCount = equipFloorCounts.get(l.getFloor()).get(e.getEquipType());
          equipFloorCounts.get(l.getFloor()).put(e.getEquipType(), prevCount + 1);
        }
      }
    }
    return equipFloorCounts;
  }

  public static int getSumOfRequestsOnFloor(String floor) {
    int sum = 0;
    for (RequestType r : RequestType.values()) {
      sum += getRequestsOnFloor(r.requestClass, floor).size();
    }
    return sum;
  }

  /**
   * Returns a list of all the medical equipment at a location.
   *
   * @param l the location
   * @return equipment list
   */
  public static List<MedEquip> getEquipAtLocation(Location l) {
    List<MedEquip> allEquip = DBManager.getAll(MedEquip.class);
    List<MedEquip> filtered = new ArrayList<>();
    for (MedEquip e : allEquip) {
      if (e.getEquipLocId().equals(l.getNodeID())) {
        filtered.add(e);
      }
    }
    return filtered;
  }
}
