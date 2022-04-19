package edu.wpi.cs3733.d22.teamY;

import com.google.firebase.database.*;
import edu.wpi.cs3733.d22.teamY.Messaging.Firebase;

public class Main {

  public static void main(String[] args) throws Exception {
    Firebase.init();
    // Load all tables with values from CSV
    CSVBackup.loadFromCSV(EntryType.LOCATION);
    CSVBackup.loadFromCSV(EntryType.MED_EQUIP);
    CSVBackup.loadFromCSV(EntryType.EMPLOYEE);
    CSVBackup.loadFromCSV(EntryType.REQUESTS);

    App.launch(App.class, args);

    CSVBackup.saveToCSV(EntryType.REQUESTS);
    CSVBackup.saveToCSV(EntryType.LOCATION);
    CSVBackup.saveToCSV(EntryType.MED_EQUIP);
    CSVBackup.saveToCSV(EntryType.EMPLOYEE);


  }
}
