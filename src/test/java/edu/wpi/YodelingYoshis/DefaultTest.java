/*-------------------------*/
/* DO NOT DELETE THIS TEST */
/*-------------------------*/

package edu.wpi.YodelingYoshis;

import edu.wpi.cs3733.d22.teamY.DataManager;
import edu.wpi.cs3733.d22.teamY.Database;
import edu.wpi.cs3733.d22.teamY.Location;
import edu.wpi.cs3733.d22.teamY.ReadIn;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DefaultTest {

  @Test
  public void test() {
    assert true;
  }

  @Test
  public void testAddAndGet() {
    Database db = init();
    Location testLoc =
        new Location(
            "TESTLOCATION",
            55,
            40,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    DataManager.add(testLoc);
    Location l2 = DataManager.get(Location.TABLE_NAME, "TESTLOCATION");
    if (l2 == null) {
      assert false;
    }
    assert (l2.toString().equals(testLoc.toString()));
    // now we set the cloned location to have a different attribute should not affect the original
    l2.setFloor("SOMETHINGELSE");
    Location getLoc =
        DataManager.get(Location.TABLE_NAME, "TESTLOCATION"); // should still be the same
    if (getLoc == null) {
      assert false;
    }
    assert (getLoc.toString().equals(testLoc.toString()));
    // once ended, shuts off the database
    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  @Test
  public void testGetAll() {
    Database db = init();
    ArrayList<Location> locationArray = new ArrayList<>();
    locationArray = DataManager.getAll(Location.TABLE_NAME);
    if (locationArray == null) {
      assert false;
    }
    assert (locationArray.size() == 151);

    for (Location l : locationArray) {
      System.out.println(l.toString());
    }

    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  @Test
  public void testRemoveObject() {
    Database db = init();
    Location testLoc =
        new Location(
            "TESTLOCATION",
            55,
            40,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    DataManager.add(testLoc);
    Location l2 = DataManager.get(Location.TABLE_NAME, "TESTLOCATION");
    if (l2 == null) {
      assert false;
      return;
    }
    assert (l2.toString().equals(testLoc.toString()));
    DataManager.remove(Location.TABLE_NAME, "TESTLOCATION");
    Location getLoc = DataManager.get(Location.TABLE_NAME, "TESTLOCATION");
    if (getLoc != null) {
      assert false;
      return;
    }
    assert true;

    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  @Test
  public void testReplaceObject() {
    Database db = init();
    Location testLoc =
        new Location(
            "TESTLOCATION",
            55,
            40,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    DataManager.add(testLoc);
    Location l2 = DataManager.get(Location.TABLE_NAME, "TESTLOCATION");
    if (l2 == null) {
      assert false;
      return;
    }
    assert (l2.toString().equals(testLoc.toString()));
    Location newLoc =
        new Location(
            "TESTLOCATION",
            99,
            99,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    DataManager.replace(newLoc);
    Location getLoc = DataManager.get(Location.TABLE_NAME, "TESTLOCATION");
    if (getLoc == null) {
      assert false;
    }
    assert (getLoc.toString().equals(newLoc.toString()));

    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  @Test
  public void testBadReplace() {
    Database db = init();
    Location testLoc =
        new Location(
            "TESTLOCATION",
            55,
            40,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    boolean replace = DataManager.replace(testLoc); // should not work
    assert (!replace);
    Location loc = DataManager.get(Location.TABLE_NAME, "TESTLOCATION"); // should not work
    assert (loc == null);
    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  @Test
  public void testCleanTable() {
    Database db = init();
    assert (DataManager.getAll(Location.TABLE_NAME).size() == 151);
    DataManager.cleanTable(Location.TABLE_NAME);
    assert (DataManager.getAll(Location.TABLE_NAME).size() == 0);

    // make sure we can add a new object
    Location testLoc =
        new Location(
            "TESTLOCATION",
            55,
            40,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    DataManager.add(testLoc);
    assert (DataManager.getAll(Location.TABLE_NAME).size() == 1);

    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  @Test
  public void testCleanAll() {
    Database db = init();

    assert (DataManager.getAll(Location.TABLE_NAME).size() == 151);
    DataManager.cleanAll();
    assert (DataManager.getAll(Location.TABLE_NAME).size() == 0);

    // make sure we can add a new object
    Location testLoc =
        new Location(
            "TESTLOCATION",
            55,
            40,
            "TESTFLOOR",
            "TESTBUILDING",
            "TestNODETYPE",
            "LONGName",
            "SHORTNAME");
    DataManager.add(testLoc);
    assert (DataManager.getAll(Location.TABLE_NAME).size() == 1);

    System.out.println("Shutting down database...");
    db.shutdown_db();
  }

  // helper function to start the database and populate it with data from the csv file
  public Database init() {
    // creates database
    Database locationDB = new Database("LocationDB");
    Connection db_conn = Database.connection; // establishes connection to database

    // creating dataManager class that manages database
    DataManager.init(db_conn, "locations");
    DataManager.cleanAll(); // cleans database

    // function set to read in CSV
    ReadIn input = new ReadIn();
    ArrayList<Location> locationArray = new ArrayList<>();
    locationArray = input.readCSV();

    DataManager.addObjects(locationArray);
    return locationDB;
  }
}
