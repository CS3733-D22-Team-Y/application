package edu.wpi.cs3733.d22.teamY;

import java.sql.*;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    // creates database
    Database locationDB = new Database("LocationDB");
    Connection db_conn = locationDB.connection; // establishes connection to database

    // creating dataManager class that manages database
    LocationDataManager dataManager = new LocationDataManager(db_conn);
    dataManager.cleanTable("locations");

    // function set to read in CSV
    ReadIn input = new ReadIn();
    ArrayList<Location> locationArray = new ArrayList<Location>();
    locationArray = input.readCSV();

    // add ArrayList locations so that Datamanager can access all values
    dataManager.addLocations(locationArray);

    // creatae locationManager to run console loop
    LocationManagerInterface locationManagerInterface = new LocationManagerInterface();
    locationManagerInterface.start();
    //    App.launch(App.class, args);

    // once ended, shuts off the database
    locationDB.shutdown_db();
  }
}
