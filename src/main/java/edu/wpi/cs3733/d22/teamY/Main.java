package edu.wpi.cs3733.d22.teamY;

import java.sql.*;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {

    // creating dataManager class that manages database
    DataManager.init("LocationDB");
    DataManager.cleanAll(); // cleans database

    // function set to read in CSV
    ReadIn input = new ReadIn();
    ArrayList<Location> locationArray = new ArrayList<>();
    locationArray = input.readLocationCSV("TowerLocations.csv");
    ArrayList<MedEquip> medEquipArray = new ArrayList<>();
    medEquipArray = input.readMedEquipCSV("MedEquip.csv");
    ArrayList<MedEquipReq> medEquipRequestArray = new ArrayList<>();
    medEquipRequestArray = input.ReadMedReqCSV("MedEquipReq.csv");

    DataManager.addObjects(locationArray);
    DataManager.addObjects(medEquipArray);
    // DataManager.addObjects(medEquipRequestArray);

    System.out.println(DataManager.getAll(Location.TABLE_NAME).size());
    System.out.println(DataManager.getAll(MedEquip.TABLE_NAME).size());
    // System.out.println(DataManager.getAll(MedEquipReq.TABLE_NAME).size());

    App.launch(App.class, args);

    // once ended, shuts off the database
    System.out.println("Shutting down database...");
    DataManager.shutdownDB();
  }
}
