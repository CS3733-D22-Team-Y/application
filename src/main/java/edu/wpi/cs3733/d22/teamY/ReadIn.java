package edu.wpi.cs3733.d22.teamY;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadIn {

  // ArrayList that stores all the location values from the CSV file
  ArrayList<Location> locationList = new ArrayList<Location>();

  // returns the ArrayList
  public ArrayList<Location> getLocationList() {
    return locationList;
  }

  // function that reads in CSV file and stores all its values in an ArrayList locationList
  public ArrayList<Location> readCSV() {

    // ArrayList created to store values from CSV output
    ArrayList<String> csvOutputs = new ArrayList<String>();

    // try-catch in order to avoid File not Found error
    try {

      // creates scanner object
      Scanner sc = new Scanner(new File("TowerLocations.csv"));

      while (sc.hasNextLine()) // returns a boolean value
      {
        String s = sc.nextLine(); // takes line of input with commas

        String[] vals = s.split(","); // divides the CSV data by commas

        // adds all values into csvOutputs
        for (int i = 0; i < vals.length; i++) {
          csvOutputs.add(vals[i]);
        }
      }
      sc.close(); // closes scanner
    } catch (java.io.FileNotFoundException e) {
      System.out.println("File not found."); // accounts for File not Found
      return locationList;
    }

    // adds each location value into the ArrayList
    for (int i = 0; i < csvOutputs.size(); i += 8) {

      if (i >= 8) { // checks in batches of 8
        String nodeID = csvOutputs.get(i);
        int xCoord = Integer.parseInt(csvOutputs.get(i + 1));
        int yCoord = Integer.parseInt(csvOutputs.get(i + 2));
        String floor = csvOutputs.get(i + 3);
        String building = csvOutputs.get(i + 4);
        String nodeType = csvOutputs.get(i + 5);
        String longName = csvOutputs.get(i + 6);
        String shortName = csvOutputs.get(i + 7);

        // creating Location
        Location iLocation =
            new Location(nodeID, xCoord, yCoord, floor, building, nodeType, longName, shortName);

        // adding Location
        locationList.add(iLocation);
      }
    }

    return locationList;
  }
}
