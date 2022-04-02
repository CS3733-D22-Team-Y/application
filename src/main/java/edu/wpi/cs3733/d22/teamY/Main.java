package edu.wpi.cs3733.d22.teamY;

import java.sql.*;

public class Main {

  public static void main(String[] args) throws Exception {

    DBManager.save(ReadIn.readLocationCSV("TowerLocations.csv"));
    DBManager.save(ReadIn.readMedEquipCSV("MedEquip.csv"));
    DBManager.save(ReadIn.ReadMedReqCSV("MedEquipReq.csv"));

    App.launch(App.class, args);
  }
}
