/*-------------------------*/
/* DO NOT DELETE THIS TEST */
/*-------------------------*/

package edu.wpi.YodelingYoshis;

import edu.wpi.cs3733.d22.teamY.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DefaultTest {

  @Test
  public void test() {
    assert true;
  }

  /*
    @Test
    public void testAddAndGet() {
      DataManager.init("LocationDB");
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
      DataManager.shutdownDB();
    }

    @Test
    public void testAddAndGetMedEquip() {
      DataManager.init("LocationDB");
      MedEquip testLoc = new MedEquip("TestEquip", "TestTypeyeyeHere", "yPATI01703", false);
      DataManager.add(testLoc);
      MedEquip l2 = DataManager.get(MedEquip.TABLE_NAME, "TestEquip");
      if (l2 == null) {
        assert false;
      }
      assert (l2.toString().equals(testLoc.toString()));
      // now we set the cloned location to have a different attribute should not affect the original
      l2.setClean(true);
      MedEquip getLoc = DataManager.get(MedEquip.TABLE_NAME, "TestEquip"); // should still be the same
      if (getLoc == null) {
        assert false;
      }
      assert (getLoc.toString().equals(testLoc.toString()));
      // once ended, shuts off the database
      System.out.println("Shutting down database...");
      DataManager.shutdownDB();
    }
  /*
    @Test
    public void testGetAll() {
      DataManager.init("LocationDB");
      ArrayList<Location> locationArray = new ArrayList<>();
      locationArray = DataManager.getAll(Location.TABLE_NAME);
      if (locationArray == null) {
        assert false;
      }
      System.out.println(locationArray.size());
      assert (locationArray.size() == 151);

      for (Location l : locationArray) {
        System.out.println(l.toString());
      }

      System.out.println("Shutting down database...");
      DataManager.shutdownDB();
    }

    @Test
    public void testRemoveObject() {
      DataManager.init("LocationDB");
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
      DataManager.shutdownDB();
    }
  */
  @Test
  public void testReplaceObject() {
    DataManager.init("LocationDB");
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
    DataManager.shutdownDB();
  }

  @Test
  public void testBadReplace() {
    DataManager.init("LocationDB");
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
    DataManager.shutdownDB();
  }

  /*
    @Test
    public void testCleanTable() {
      DataManager.init("LocationDB");
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
      DataManager.shutdownDB();
    }
    @Test
    public void testCleanAll() {
      DataManager.init("LocationDB");

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
      DataManager.shutdownDB();
    }

  */
  @Test
  public void initTest() {
    DataManager.init("LocationDB");
    DataManager.cleanAll();
  }

  // helper function to start the database and populate it with data from the csv file
  public void init() {
    // creating dataManager class that manages database
    DataManager.init("LocationDB");
    DataManager.cleanAll(); // cleans database

    // function set to read in CSV
    ReadIn input = new ReadIn();
    ArrayList<Location> locations = new ArrayList<Location>();
    locations = input.readLocationCSV("TowerLocations.csv");
    DataManager.addObjects(locations);
    //    DataManager.addObjects(input.readMedEquipCSV("MedEquip.csv"));
    //    DataManager.addObjects(input.ReadMedReqCSV("MedEquipRequest.csv"));

  }
}
