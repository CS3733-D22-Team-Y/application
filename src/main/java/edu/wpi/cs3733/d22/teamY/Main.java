package edu.wpi.cs3733.d22.teamY;

import java.sql.*;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    // creates database
    Database locationDB = new Database("LocationDB");
    Connection db_conn = Database.connection; // establishes connection to database

    // creating dataManager class that manages database
    DataManager.init(db_conn, "locations", "medequip", "medequiprequest");
    DataManager.cleanAll(); // cleans database

    // function set to read in CSV
    ReadIn input = new ReadIn();
    ArrayList<Location> locationArray = new ArrayList<>();
    locationArray = input.readLocationCSV("java/edu/wpi/cs3733/d22/teamY/TowerLocations.csv");

    DataManager.addObjects(locationArray);

    App.launch(App.class, args);

    // once ended, shuts off the database
    System.out.println("Shutting down database...");
    locationDB.shutdown_db();
  }
}
