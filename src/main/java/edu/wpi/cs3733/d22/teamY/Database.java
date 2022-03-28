package edu.wpi.cs3733.d22.teamY;

import java.sql.*;

public class Database {
  // DB connection
  public static Connection connection;

  /**
   * Initializes connection to existing DB
   *
   * @param db_name DB name to connect to.
   */
  public Database(String db_name) {
    // Check if apache derby driver is available
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    } catch (ClassNotFoundException e) {
      System.out.println("Apache Derby Driver Not found!");
      e.printStackTrace();
      return;
    }

    // Connect to existing DB in root folder
    try {
      connection = DriverManager.getConnection("jdbc:derby:" + db_name);

    } catch (SQLException e) {
      System.out.println("Connection Failed");
      e.printStackTrace();
      return;
    }
    System.out.println("Connection Successful");
  }

  /** Shutown Current DB connection. */
  public void shutdown_db() {
    try {
      connection.close();
      System.out.println("Connection Closed.");

    } catch (SQLException e) {
      System.out.println("Failed to shutdown");
      e.printStackTrace();
    }
  }

  /**
   * Initializes a table inside the current connected DB.
   *
   * @param tableName Stirng, name of the table to be created.
   */
  public void initTable(String tableName) {
    try {
      Statement stmt = connection.createStatement();
      String sql =
          "CREATE TABLE "
              + tableName
              + " (nodeID VARCHAR(25) PRIMARY KEY, xcoord INT, ycoord INT, floor VARCHAR(25), building VARCHAR(25), nodeType VARCHAR(25), longName VARCHAR(255), shortName VARCHAR(50))";
      stmt.executeUpdate(sql);
      System.out.println("Created tabled named " + tableName);

    } catch (SQLException e) {
      System.out.println("Connection Failed");
      e.printStackTrace();
      return;
    }

    System.out.println("Connection Successful");
  }

  /**
   * Executes PROPERLY FORMATTED SQL String
   *
   * @param sql_string PROPERLY FORMATTED STRING
   */
  public void execute_cmd(String sql_string) {
    try {
      Statement stmt = connection.createStatement();
      stmt.execute(sql_string);
      System.out.println("Executed!");

    } catch (SQLException e) {
      System.out.println("Failed execution, see output");
      e.printStackTrace();
    }
  }
}
