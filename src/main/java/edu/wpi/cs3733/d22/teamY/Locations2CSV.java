package edu.wpi.cs3733.d22.teamY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** ExportToCSV takes no parameters returns void creates a CSV file */
public class Locations2CSV {
  public static void generateCSV(String fileName) throws IOException {
    File csvFile = new File(fileName + ".csv");
    FileWriter fileWriter = new FileWriter(csvFile);
    ArrayList<Location> arrayList = new ArrayList<>();
    arrayList = DataManager.getAll(Location.TABLE_NAME);
    String[][] stringArray = new String[arrayList.size()][8];
    int ctr = 0;
    for (Location L : arrayList) {
      stringArray[ctr][0] = L.getKey();
      stringArray[ctr][1] = String.valueOf(L.getXCoord());
      stringArray[ctr][2] = String.valueOf(L.getYCoord());
      stringArray[ctr][3] = L.getFloor();
      stringArray[ctr][4] = L.getBuilding();
      stringArray[ctr][5] = L.getNodeType();
      stringArray[ctr][6] = L.getLongName();
      stringArray[ctr][7] = L.getShortName();
      ctr = ctr + 1;
    }
    for (String[] data : stringArray) {
      StringBuilder line = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        line.append("\"");
        line.append(data[i].replaceAll("\"", "\"\""));
        line.append("\"");
        if (i != data.length - 1) {
          line.append(',');
        }
      }
      line.append("\n");
      fileWriter.write(line.toString());
    }
    fileWriter.close();
  }
}
