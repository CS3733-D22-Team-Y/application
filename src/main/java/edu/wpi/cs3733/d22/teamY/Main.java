package edu.wpi.cs3733.d22.teamY;

public class Main {

  public static void main(String[] args) {

    // Load all tables with values from CSV
    CSVBackup.loadFromCSV(EntryType.LOCATION);
    CSVBackup.loadFromCSV(EntryType.MED_EQUIP);
    CSVBackup.loadFromCSV(EntryType.EMPLOYEE);
    CSVBackup.loadFromCSV(EntryType.REQUESTS);

    CSVBackup.saveToCSV(EntryType.REQUESTS);
    // App.launch(App.class, args);

    // Backup all tables to CSV on shutdown
    /*
    for (EntryType e : EntryType.values()) {
      CSVBackup.saveToCSV(e);
    }
     */
  }
}
