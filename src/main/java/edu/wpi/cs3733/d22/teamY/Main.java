package edu.wpi.cs3733.d22.teamY;

public class Main {

  public static void main(String[] args) throws Exception {

    CSVBackup.loadFromCSV(EntryType.LOCATION);
    CSVBackup.loadFromCSV(EntryType.MED_EQUIP);
    CSVBackup.loadFromCSV(EntryType.MED_EQUIP_REQ);
    CSVBackup.loadFromCSV(EntryType.LAB_REQUEST);
    CSVBackup.loadFromCSV(EntryType.EMPLOYEE);

    App.launch(App.class, args);

    CSVBackup.saveToCSV(EntryType.LOCATION);
    CSVBackup.saveToCSV(EntryType.MED_EQUIP);
    CSVBackup.saveToCSV(EntryType.MED_EQUIP_REQ);
    CSVBackup.saveToCSV(EntryType.LAB_REQUEST);
    CSVBackup.saveToCSV(EntryType.EMPLOYEE);
  }
}
