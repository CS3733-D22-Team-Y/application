package edu.wpi.cs3733.d22.teamY;

import java.util.Locale;
import java.util.Scanner;

/** Manages the user interface for the location manager program. */
public class LocationManagerInterface {
  // message to display to show user input options
  final String displayMessage =
      "==========================================================\n"
          + "1 - Location Information\n"
          + "2 – Change Floor and Type\n"
          + "3 – Enter Location\n"
          + "4 – Delete Location\n"
          + "5 – Save Locations to CSV file\n"
          + "6 – Exit Program\n"
          + "==========================================================\n";
  final String inputMessage = "Please enter a number from 1 to 6: "; // Message to prompt user input
  final String inputErrorMessage = "Input error."; // Message for user input error

  /** Starts the location manager user interface in the command line/console. */
  public void start() { // starts the interface
    System.out.println("\n");
    while (true) {
      System.out.println(displayMessage);
      System.out.print(inputMessage);
      int num = getInput();
      if (num == -1)
        continue; // if the user entered an invalid number, continue to the next iteration of the
      // loop
      switch (num) {
        case 1:
          LocationManagerFunctionality.displayNodes();
          System.out.println("End of location data.\n");
          break;
        case 2:
          System.out.print("Enter ID of node to change: ");
          String repId = getTextInput();
          System.out.print("Enter new floor value: ");
          String flr = getTextInput();
          System.out.print("Enter new location type: ");
          String locType = getTextInput();
          if (LocationManagerFunctionality.replaceNodeVals(repId, flr, locType)) {
            System.out.println("Values successfully replaced.");
          } else {
            System.out.println("Values were not replaced.");
          }
          break;
        case 3:
          System.out.print("Enter ID of node to add: ");
          String newId = getTextInput();
          if (LocationManagerFunctionality.newNode(newId)) {
            System.out.println("Node ID " + newId + " added.");
          } else {
            System.out.println("No new node added.");
          }
          break;
        case 4:
          System.out.print("Enter ID of node to delete: ");
          String delId = getTextInput();
          if (LocationManagerFunctionality.deleteNode(delId)) {
            System.out.println("Node ID " + delId + " successfully deleted.");
          } else {
            System.out.println("Deletion was unsuccessful.");
          }
          break;
        case 5:
          System.out.print("Enter CSV file name: ");
          String csv = getTextInput();
          if (LocationManagerFunctionality.writeDbToCSV(csv)) {
            System.out.println("All values successfully written to CSV.");
          } else {
            System.out.println("CSV write failed.");
          }
          break;
        case 6: // exit condition
          return; // exit the program
        default: // if the user entered an invalid number, continue to the next iteration of the
          // loop
          System.out.println(inputErrorMessage);
          break;
      }
    }
  }

  /**
   * Gets the user input and returns it as an integer.
   *
   * @return the number that the user entered or -1 if the user entered an invalid number
   */
  public int getInput() {
    Scanner scanner = new Scanner(System.in); // Scanner for user input
    String input = scanner.next();
    int num = -1;

    // try to parse input as a number
    try {
      num = Integer.parseInt(input);
    } catch (Exception e) {
      System.out.println(inputErrorMessage);
    }
    return num;
  }

  /**
   * Gets the user input and returns it as a string.
   *
   * @return the string that the user entered
   */
  public String getTextInput() {
    return getTextInput(false);
  }

  /**
   * Gets the user input and returns it as a string, specifying whether to force all caps.
   *
   * @param allCaps should this input be capitalized?
   * @return the string that the user entered
   */
  public String getTextInput(boolean allCaps) {
    Scanner scanner = new Scanner(System.in); // Scanner for user input
    String in = scanner.next();
    if (allCaps) {
      in = in.toUpperCase(Locale.ROOT);
    }

    return in;
  }
}
