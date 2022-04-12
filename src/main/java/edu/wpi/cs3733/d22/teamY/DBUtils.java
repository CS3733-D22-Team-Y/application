package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.controllers.PersonalSettingsController;
import edu.wpi.cs3733.d22.teamY.model.*;
import java.util.ArrayList;
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

  public static Pair<Integer, Integer> getAvailableEquipment(String equipType) {
    List<MedEquip> equipment =
        DBManager.getAll(MedEquip.class, new Where(MedEquip.EQUIP_TYPE, equipType));

    if (equipment == null) {
      return null;
    }

    int total = equipment.size();
    int available = 0;

    for (MedEquip i : equipment) {
      if (i.isClean().equals("1")) {
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
    thisEquip.setClean("0");

    DBManager.update(thisEquip);
  }

  // find MedEquip object
  @SuppressWarnings("unchecked")
  public static String getNameFromID(String id) {
    Session s = SessionManager.getSession();
    List<Employee> people =
        s.createQuery("from Employee where username = :id")
            .setParameter("id", id.hashCode() + "")
            .list();
    s.close();

    if (people.size() == 0) {
      return "Guest";
    }

    Employee thePerson = people.get(0);
    PersonalSettingsController.currentEmployee = thePerson; // TODO change

    return thePerson.getName();
  }

  /**
   * Returns next request number
   *
   * @param e EntryType of Object to Use
   * @return Number of Next Request
   */
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
  @SuppressWarnings("unchecked")
  public static boolean isValidLogin(String username, String password) {
    Session s = SessionManager.getSession();
    // search for the employee with the given username and password
    List<Employee> employees =
        s.createQuery("from Employee where username = :username and password = :password")
            .setParameter("username", username.hashCode() + "")
            .setParameter("password", password.hashCode() + "")
            .list();
    s.close();
    return employees.size() == 1;
  }

  /**
   * Returns if username already exists.
   *
   * @param hashedUsername hashed Username INTEGER
   * @return true if valid username, false if one exists.
   */
  public static boolean doesUserExist(int hashedUsername) {
    Session s = SessionManager.getSession();

    List<Employee> employees =
        s.createQuery("from Employee where username = :username")
            .setParameter("username", String.valueOf(hashedUsername))
            .list();
    s.close();
    return employees.size() == 0;
  }

  public static String convertNameToID(String shortName) {
    Session s = SessionManager.getSession();
    List<Location> tempLocations =
        s.createQuery("from Location where shortName = :shortName").list();
    s.close();

    if (tempLocations.size() > 1) {
      return null;
    }

    return (tempLocations.get(0).getNodeID());
  }

  public static String convertIDToName(String nodeID) {
    Session s = SessionManager.getSession();
    List<Location> tempLocations =
        s.createQuery("from Location where nodeID = :nodeID").setParameter("nodeID", nodeID).list();
    s.close();

    if (tempLocations.size() > 1) {
      return null;
    }

    return (tempLocations.get(0).getShortName());
  }

  /**
   * Changes an employee's password.
   *
   * @param username the username of the employee
   * @param oldPassword the old password of the employee
   * @param newPassword the new password of the employee
   * @return String relating to the success of the change
   */
  @SuppressWarnings("unchecked")
  public static String changePassword(String username, String oldPassword, String newPassword) {
    newPassword = newPassword.hashCode() + "";
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

    employee.setPassword(newPassword);
    DBManager.update(employee);
    return "Successfully changed password.";
  }

  /**
   * Switches the DB to and from Client-Server -> Embedded
   *
   * @param input false for Embedded, true for C-S
   */
  public static void switchDBType(boolean input) {
    SessionManager.switchType(input);
    DBUtils.completeCSVRefresh();
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
}
