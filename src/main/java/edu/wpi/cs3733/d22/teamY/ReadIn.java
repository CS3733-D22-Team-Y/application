package edu.wpi.cs3733.d22.teamY;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReadIn {

  // function that reads in CSV file and stores all its values in an ArrayList locationList
  public static <T> ArrayList<T> readCSV(String filename, entryType type) {

    // ArrayList created to store values from CSV output
    ArrayList<String> csvOutputs = new ArrayList<>();
    ArrayList<T> output = new ArrayList<>();

    // try-catch in order to avoid File not Found error
    try {

      // creates scanner object
      Scanner sc = new Scanner(new File(filename));

      while (sc.hasNextLine()) // returns a boolean value
      {
        String s = sc.nextLine(); // takes line of input with commas

        String[] vals = s.split(","); // divides the CSV data by commas

        // adds all values into csvOutputs
        csvOutputs.addAll(Arrays.asList(vals));
      }
      sc.close(); // closes scanner
    } catch (java.io.FileNotFoundException e) {
      System.out.println("File not found."); // accounts for File not Found
      return new ArrayList<>();
    }

    int size = type.getColumns();

    // adds each location value into the ArrayList
    for (int i = size; i < csvOutputs.size(); i += size) {
      output.add((T) type.newNode(csvOutputs.subList(i, i + size)));
    }
    return output;
  }
}
