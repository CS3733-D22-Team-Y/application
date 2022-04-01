package edu.wpi.cs3733.d22.teamY;

import java.sql.*;

public class Main {

  public static void main(String[] args) throws Exception {

    // creating dataManager class that manages database
    // DataManager.init("LocationDB");
    // DataManager.cleanAll(); // cleans database

    DaoManager daoManager = new DaoManager();
    daoManager.init();

    App.launch(App.class, args);

    // once ended, shuts off the database
    System.out.println("Shutting down database...");
    daoManager.shutdownDB();
  }
}
