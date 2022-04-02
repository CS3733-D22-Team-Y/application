package edu.wpi.cs3733.d22.teamY;

import java.sql.*;

public class Main {

  public static void main(String[] args) throws Exception {

    DBManager.saveList(ReadIn.readLocationCSV("TowerLocations.csv"));
    DBManager.saveList(ReadIn.readMedEquipCSV("MedEquip.csv"));
    DBManager.saveList(ReadIn.ReadMedReqCSV("MedEquipReq.csv"));

    App.launch(App.class, args);
  }
}
