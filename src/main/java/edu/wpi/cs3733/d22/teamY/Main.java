package edu.wpi.cs3733.d22.teamY;

public class Main {

  public static void main(String[] args) throws Exception {

    // Load all tables with values from CSV
    for (EntryType e : EntryType.values()) {
      CSVBackup.loadFromCSV(e);
    }

    System.out.println(DBUtils.getNameFromID("00043"));

    App.launch(App.class, args);

    // Backup all tables to CSV on shutdown
    for (EntryType e : EntryType.values()) {
      CSVBackup.saveToCSV(e);
    }
  }
}
