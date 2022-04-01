package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadIn {

  public ReadIn() {}

  // function that reads in CSV file and stores all its values in an ArrayList locationList
  public static ArrayList<Location> readLocationCSV(String Filename) {

    // ArrayList created to store values from CSV output
    ArrayList<String> csvOutputs = new ArrayList<String>();
    ArrayList<Location> LocationOutput = new ArrayList<Location>();

    // try-catch in order to avoid File not Found error
    try {

      // creates scanner object
      Scanner sc = new Scanner(new File(Filename));

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
      return new ArrayList<>();
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
        LocationOutput.add(iLocation);
      }
    }
    return LocationOutput;
  }

  // function that reads in CSV file and stores all its values in an ArrayList locationList
  public static ArrayList<MedEquip> readMedEquipCSV(String Filename) {

    // ArrayList created to store values from CSV output
    ArrayList<String> csvOutputs = new ArrayList<String>();
    ArrayList<MedEquip> MedEquipList = new ArrayList<MedEquip>();
    // try-catch in order to avoid File not Found error
    try {

      // creates scanner object
      Scanner sc = new Scanner(new File(Filename));

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
      return new ArrayList<>();
    }

    // adds each location value into the ArrayList
    for (int i = 0; i < csvOutputs.size(); i += 4) {

      if (i >= 4) { // checks in batches of 4
        String requestNum = csvOutputs.get(i);
        String equipmentType = csvOutputs.get(i + 1);
        String equipmentLocationID = csvOutputs.get(i + 2);
        String isClean = csvOutputs.get(i + 3);
        // creating MedEquip
        MedEquip medEquip = new MedEquip(requestNum, equipmentType, equipmentLocationID, isClean);
        // adding Location
        MedEquipList.add(medEquip);
      }
    }
    return MedEquipList;
  }

  // function that reads in CSV file and stores all its values in an ArrayList locationList
  public static ArrayList<MedEquipReq> ReadMedReqCSV(String filename) {

    // ArrayList created to store values from CSV output
    ArrayList<String> csvOutputs = new ArrayList<String>();
    ArrayList<MedEquipReq> MedEquipRequestsOutputs = new ArrayList<MedEquipReq>();

    // try-catch in order to avoid File not Found error
    try {

      // creates scanner object
      Scanner sc = new Scanner(new File(filename));

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
      return new ArrayList<>();
    }

    // adds each location value into the ArrayList
    for (int i = 0; i < csvOutputs.size(); i += 3) {

      if (i >= 3) { // checks in batches of 8
        String equipmentID = /*Integer.parseInt(*/ csvOutputs.get(i) /*)*/;
        String equipmentType = csvOutputs.get(i + 1);
        String targetLocationID = csvOutputs.get(i + 2);

        // creating MedEquip
        MedEquipReq medEquipR = new MedEquipReq(equipmentID, equipmentType, targetLocationID);

        // adding Location
        MedEquipRequestsOutputs.add(medEquipR);
      }
    }
    return MedEquipRequestsOutputs;
  }
}
