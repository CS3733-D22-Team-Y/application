package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.Request;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {

    // Load all tables with values from CSV
    for (EntryType e : EntryType.values()) {
      CSVBackup.loadFromCSV(e);
    }

    // TEMP TESTING
    Request test =
        new Request(RequestTypes.FLORAL, "sandra", "room1", "nothing", new String[] {"rose"});

    Request test2 =
        new Request(
            RequestTypes.MEAL,
            "paul",
            "room2",
            "nothing",
            new String[] {"pizza", "apple", "none", "none"});
    DBManager.save(test);
    DBManager.save(test2);

    List<Request> retrieve = DBManager.getAll(Request.class);
    System.out.println(retrieve.get(0).get("bouquetTypeSelected"));
    System.out.println(retrieve.get(0).getRequestId());
    System.out.println(retrieve.get(1).get("mainChoice"));
    System.out.println(retrieve.get(1).get("sideChoice"));
    System.out.println(retrieve.get(1).getRequestId());

    // App.launch(App.class, args);

    // Backup all tables to CSV on shutdown
    for (EntryType e : EntryType.values()) {
      CSVBackup.saveToCSV(e);
    }
  }
}
