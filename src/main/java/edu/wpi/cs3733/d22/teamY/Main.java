package edu.wpi.cs3733.d22.teamY;

import java.sql.*;

public class Main {

  public static void main(String[] args) throws Exception {

    ReadIn csvReader = new ReadIn();
    csvReader.addCSVsToDB();

    App.launch(App.class, args);
  }
}
