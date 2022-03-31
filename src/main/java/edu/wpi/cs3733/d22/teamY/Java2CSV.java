package edu.wpi.cs3733.d22.teamY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** LocationsToCSV takes no parameters returns void creates a CSV file */
public class Java2CSV {
  public static void locations2CSV(String fileName) throws IOException {
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

  public static void medEquip2CSV(String fileName) throws IOException {
    File csvFile = new File(fileName + ".csv");
    FileWriter fileWriter = new FileWriter(csvFile);
    ArrayList<MedEquip> arrayList = new ArrayList<>();
    arrayList = DataManager.getAll(MedEquip.TABLE_NAME);
    String[][] stringArray = new String[arrayList.size()][5];
    int ctr = 0;
    for (MedEquip L : arrayList) { // equipID,equipType,equipLocID,isClean
      stringArray[ctr][0] = L.getKey();
      stringArray[ctr][1] = String.valueOf(L.getEquipID());
      stringArray[ctr][2] = String.valueOf(L.getEquipType());
      stringArray[ctr][3] = L.getEquipLocId();
      stringArray[ctr][4] = Boolean.toString(L.isClean());
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
  /*
  public static void medEquipReq2CSV(String fileName) throws IOException {
    File csvFile = new File(fileName + ".csv");
    FileWriter fileWriter = new FileWriter(csvFile);
    ArrayList<MedEquipReq> arrayList = new ArrayList<>();
    arrayList = DataManager.getAll(MedEquipReq.TABLE_NAME);
    String[][] stringArray = new String[arrayList.size()][3];
    int ctr = 0;
    for (MedEquipReq L : arrayList) {
      stringArray[ctr][0] = L.getKey();
      stringArray[ctr][1] = String.valueOf(L.getEquipID());
      stringArray[ctr][2] = String.valueOf(L.getTargetLocID());
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
   */
}
