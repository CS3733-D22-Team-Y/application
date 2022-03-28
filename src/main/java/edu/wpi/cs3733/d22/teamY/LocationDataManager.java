package edu.wpi.cs3733.d22.teamY;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** This class manages the location data so that data is mirrored in memory and in the database. */
public class LocationDataManager {
  private static HashMap<String, Location> locations = new HashMap<>();
  private static Connection dbConnection;

  public LocationDataManager(Connection dbConnection) {
    this.dbConnection = dbConnection;
  }
  /**
   * Adds a location to the list of locations
   *
   * @param location the location to add
   * @return true if successful, false otherwise
   */
  public static boolean addLocation(Location location) {
    locations.put(location.nodeID, location);

    String sql_string =
        "INSERT INTO locations "
            + "VALUES("
            + "'"
            + location.nodeID
            + "'"
            + ", "
            + location.xCoord
            + ", "
            + location.yCoord
            + ", "
            + "'"
            + location.floor
            + "'"
            + ", "
            + "'"
            + location.building
            + "'"
            + ", "
            + "'"
            + location.nodeType
            + "'"
            + ", "
            + "'"
            + location.longName
            + "'"
            + ", "
            + "'"
            + location.shortName
            + "'"
            + ")";
    try {
      Statement stmt = dbConnection.createStatement();
      stmt.executeUpdate(sql_string);
    } catch (SQLException e) {
      System.out.println("Adding Location " + location.nodeID + " Failed, check console");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /** Updates the local copy of the location list from the database */
  public static void updateLocationsFromDB() {
    // erase hashmap
    locations.clear();
    // TODO get locations from db and update hashmap
  }

  /**
   * Adds a list of locations to the current list of locations
   *
   * @param locations the list of locations to add
   * @return true if successful, false otherwise
   */
  public static boolean addLocations(Location... locations) {
    boolean ok = true;
    for (Location location : locations) {
      if (!addLocation(location)) // uses the addLocation method to avoid redundancy)
      {
        ok = false;
      }
    }

    return ok;
  }

  /**
   * Adds a list of locations to the current list of locations
   *
   * @param locations the arraylist of locations to add
   * @return true if successful, false otherwise
   */
  public static boolean addLocations(ArrayList<Location> locations) {
    boolean ok = true;
    for (Location location : locations) {
      if (!addLocation(location)) // uses the addLocation method to avoid redundancy)
      {
        ok = false;
      }
    }

    return ok;
  }

  /**
   * Removes a location from the list of locations
   *
   * @param nodeID the nodeID attribute of the Location object to remove
   * @return true if successful, false otherwise
   */
  public static boolean removeLocation(String nodeID) {
    Location removed = locations.remove(nodeID);
    if (removed == null) {
      System.out.println("Location " + nodeID + " not found");
      return false;
    }

    String sql_string = "DELETE FROM locations WHERE nodeID=" + "'" + nodeID + "'";

    try {
      Statement stmt = dbConnection.createStatement();
      stmt.executeUpdate(sql_string);
    } catch (SQLException e) {
      System.out.println("Removing Location Failed, check console");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Gets a location from the list of locations
   *
   * @param nodeID the nodeID attribute of the Location object to get
   * @return the Location object with the given nodeID or null if no such location exists
   */
  private static Location getLocation(String nodeID) {
    return locations.get(nodeID);
  }

  /**
   * Returns a copy of a location from the list of locations
   *
   * @param nodeID the nodeID attribute of the Location object to get
   * @return a copy of the Location object with the given nodeID or null if no such location exists
   */
  public static Location getLocationCopy(String nodeID) {
    Location location = getLocation(nodeID);
    if (location != null) {
      return location.getClone();
    } else {
      return null;
    }
  }

  /**
   * Replaces a location in the list of locations
   *
   * @param nodeID the nodeID attribute of the Location object to replace
   * @param newLocation the new Location object to replace the old one with
   * @return true if successful, false otherwise
   */
  public static boolean replaceLocation(String nodeID, Location newLocation) {
    Location location = getLocation(nodeID);
    if (location != null) {
      if (!removeLocation(nodeID)) {
        return false;
      }
      if (!addLocation(newLocation)) {
        System.out.println("WARNING: Replacement failed partway. Re-adding Node " + nodeID + "...");
        addLocation(
            location); // Emergency re-addition of location in the event of incomplete replacement
        return false;
      }
    } else {
      return false;
    }

    return true;
  }

  /**
   * Returns a deep copy of the list of locations.
   *
   * @return an ArrayList of Location objects
   */
  public static ArrayList<Location> getLocations() {
    ArrayList<Location> locationsCopy = new ArrayList<>();
    for (Map.Entry<String, Location> entry : locations.entrySet()) {
      Location value = entry.getValue();
      locationsCopy.add(value.getClone());
    }
    return locationsCopy;
  }

  /**
   * Clears all data from Connected DB + inputted tableName
   *
   * @param tableName String of table Name to clear
   */
  public static void cleanTable(String tableName) {
    String sql_string = "DELETE FROM " + tableName;

    try {
      Statement stmt = dbConnection.createStatement();
      stmt.executeUpdate(sql_string);
    } catch (SQLException e) {
      System.out.println("Clearing table failed, check console");
      e.printStackTrace();
    }
  }
}
