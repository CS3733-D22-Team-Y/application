package edu.wpi.cs3733.d22.teamY;

import java.sql.*;

public class Main {

  public static void main(String[] args) throws Exception {

    CSVBackup.loadFromCSV(EntryType.LOCATION);
    CSVBackup.loadFromCSV(EntryType.MED_EQUIP);
    CSVBackup.loadFromCSV(EntryType.MED_EQUIP_REQ);

    App.launch(App.class, args);

    CSVBackup.saveToCSV(EntryType.LOCATION);
    CSVBackup.saveToCSV(EntryType.MED_EQUIP);
    CSVBackup.saveToCSV(EntryType.MED_EQUIP_REQ);
  }
}
