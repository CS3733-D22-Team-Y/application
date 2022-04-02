package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVBackup {

  // function that reads in CSV file for the corresponding entry type and
  // returns all its values as a wildcard ArrayList.
  public static ArrayList<?> loadFromCSV(entryType type) {
    return loadFromCSV(type.getEntryClass());
  }

  // function that reads in CSV file for the corresponding class and
  // stores all its values as an ArrayList of entries.
  @SuppressWarnings("unchecked")
  public static <T> ArrayList<T> loadFromCSV(Class<T> entryClass) {

    entryType type = entryType.getFromClass(entryClass);
    if (type == null) {
      System.out.println(
          "CSV read failed: Class \"" + entryClass.getName() + "\" is not a valid entry type.");
      return new ArrayList<>();
    }

    // ArrayList created to store values from CSV output
    ArrayList<String> csvOutputs = new ArrayList<>();
    ArrayList<T> output = new ArrayList<>();

    // try-catch in order to avoid File not Found error
    try {

      // creates scanner object
      Scanner sc = new Scanner(new File(type.getCsvInputLocation() + ".csv"));

      while (sc.hasNextLine()) // returns a boolean value
      {
        String s = sc.nextLine(); // takes line of input with commas

        String[] vals = s.split(","); // divides the CSV data by commas

        // adds all values into csvOutputs
        csvOutputs.addAll(Arrays.asList(vals));
      }
      sc.close(); // closes scanner
    } catch (java.io.FileNotFoundException e) {
      System.out.println("CSV read failed: File not found."); // accounts for File not Found
      return new ArrayList<>();
    }

    int size = type.getColumns();

    // adds each location value into the ArrayList
    for (int i = size; i < csvOutputs.size(); i += size) {
      output.add((T) type.newNode(csvOutputs.subList(i, i + size)));
    }
    return output;
  }

  // function that backs up all entries of the specified class
  // to its default CSV output file.
  public static <T extends StringArrayConv> void saveToCSV(Class<T> entryClass) {
    entryType type = entryType.getFromClass(entryClass);
    if (type == null) {
      System.out.println(
          "CSV write failed: Class \"" + entryClass.getName() + "\" is not a valid entry type.");
      return;
    }

    saveToCSV(type);
  }

  // function that backs up all entries of the specified type
  // to its default CSV output file.
  @SuppressWarnings("unchecked")
  public static void saveToCSV(entryType type) {

    File csvFile = new File(type.getCsvOutputLocation() + ".csv");
    List<StringArrayConv> list;
    FileWriter fileWriter;

    try {
      fileWriter = new FileWriter(csvFile);
      list = (List<StringArrayConv>) type.getDao().getAll();
    } catch (DaoGetException e) {
      System.out.println("CSV write failed: DAO get failed.");
      return;
    } catch (IOException e) {
      System.out.println("CSV write failed: Could not open file.");
      return;
    }

    String[][] stringMatrix = new String[list.size()][type.getColumns()];
    int ctr = 0;
    for (int i = 0; i < list.size(); i++) {
      stringMatrix[i] = list.get(i).toStringArray();
    }

    for (String[] data : stringMatrix) {
      try {
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
      } catch (IOException e) {
        System.out.println("CSV write failed: File write was unsuccessful.");
        tryClose(fileWriter);
        return;
      }
    }

    tryClose(fileWriter);
  }

  private static void tryClose(FileWriter fw) {
    try {
      fw.close();
    } catch (IOException e) {
      System.out.println("Could not close file.");
    }
  }
}
