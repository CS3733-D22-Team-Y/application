package edu.wpi.cs3733.d22.teamY;

import java.util.ArrayList;

public class LocationManagerFunctionality {

  /** In-between for displaying the list of database nodes. */
  public static void displayNodes() {
    ArrayList<Location> locs = DataManager.getAll(Location.TABLE_NAME);

    for (Location l : locs) {
      System.out.println(
          l.getKey()
              + ": ("
              + l.getXCoord()
              + ", "
              + l.getYCoord()
              + "),"
              + l.getBuilding()
              + " Floor "
              + l.getFloor()
              + ". "
              + l.getShortName()
              + " ["
              + l.getLongName()
              + "] "
              + l.getNodeType());
    }
  }

  /**
   * In-between: replaces the floor and location type of the node w/ specified ID.
   *
   * @param ID Node ID
   * @param floor new floor value
   * @param locationType new location type
   */
  public static boolean replaceNodeVals(String ID, String floor, String locationType) {
    Location toChange = DataManager.get(Location.TABLE_NAME, ID);
    if (toChange == null) {
      System.out.println("Could not get Node " + ID + " for replacement.");
      return false;
    }

    toChange.setFloor(floor);
    toChange.setNodeType(locationType);
    return DataManager.replace(toChange);
  }

  /**
   * In-between: creates and enters an empty node with the given ID.
   *
   * @param ID Node ID
   */
  public static boolean newNode(String ID) {
    Location newLoc = new Location(ID);
    return DataManager.add(newLoc);
  }

  /**
   * In-between: deletes the node with the given ID
   *
   * @param ID Node ID
   */
  public static boolean deleteNode(String ID) {
    return DataManager.remove(Location.TABLE_NAME, ID);
  }

  /**
   * In-between for dumping database contents to CSV with given name.
   *
   * @param fileLoc file location to write to
   */
  public static boolean writeDbToCSV(String fileLoc) {
    try {
      Java2CSV.locations2CSV(fileLoc);
    } catch (Exception e) {
      System.out.println("CSV generation failed. See below:");
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
