package edu.wpi.cs3733.d22.teamY;

import java.sql.*;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    // creates database
    Database locationDB = new Database("LocationDB");
    Connection db_conn = locationDB.connection; // establishes connection to database

    // creating dataManager class that manages database
    DataManager.init(db_conn, "locations");
    DataManager.cleanAll(); // cleans database

    // function set to read in CSV
    ReadIn input = new ReadIn();
    ArrayList<Location> locationArray = new ArrayList<>();
    locationArray = input.readCSV();

    DataManager.addObjects(locationArray);

    //TESTING STARTS HERE ======================================================
    Location l = new Location("Kilroy Was Here");
    DataManager.add(l);
    l.floor = "69";
    DataManager.replace(l);
    DataManager.remove(Location.TABLE_NAME, "Kilroys Was Here");

    Location[] arr = new Location[1];
    arr[0] = new Location("Kilroy Was Here2");
    DataManager.addObjects(arr);

    ArrayList<Location> locations = DataManager.getAll(Location.TABLE_NAME);
    for (Location loc : locations) {
      System.out.println(loc.nodeID);
    }

    Location kilroy = DataManager.get(Location.TABLE_NAME, "Kilroy Was Here");
    if (kilroy != null) {
      kilroy.floor = "699";
    }
    Location kilroy2 = DataManager.get(Location.TABLE_NAME, "Kilroy Was Here");
    if (kilroy2 != null) {
      System.out.println(kilroy2.floor);
    }
    //TESTING ENDS HERE ========================================================

    App.launch(App.class, args);

    // once ended, shuts off the database
    locationDB.shutdown_db();
  }
}
