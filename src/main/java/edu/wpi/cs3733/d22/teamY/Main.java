package edu.wpi.cs3733.d22.teamY;

import com.google.firebase.database.*;
import edu.wpi.cs3733.d22.teamY.Messaging.Firebase;

public class Main {

  public static void main(String[] args) throws Exception {

    // Load all tables with values from CSV
    for (EntryType e : EntryType.values()) {
      CSVBackup.loadFromCSV(e);
    }

    App.launch(App.class, args);

    // Backup all tables to CSV on shutdown
    for (EntryType e : EntryType.values()) {
      CSVBackup.saveToCSV(e);
    }
  }
}
