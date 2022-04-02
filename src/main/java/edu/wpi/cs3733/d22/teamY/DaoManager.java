package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.impl.LocationDaoImpl;
import edu.wpi.cs3733.d22.teamY.model.dao.impl.MedEquipDaoImpl;
import edu.wpi.cs3733.d22.teamY.model.dao.impl.MedEquipReqDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoManager {
  static LocationDaoImpl locationDao = new LocationDaoImpl();
  static MedEquipReqDaoImpl medEquipReqDao = new MedEquipReqDaoImpl();
  static MedEquipDaoImpl medEquipDao = new MedEquipDaoImpl();

  Connection dbConnection = DatabaseConnection.getConnection();

  private static final String[] tables = {"MEDEQUIPREQUEST", "MEDEQUIP", "LOCATIONS"};

  public void init() throws DaoAddException {
    cleanAllTables();

    try {
      addAllLocations();
      addAllMedEquip();
    } catch (DaoAddException e) {
      throw new DaoAddException(e);
    }
  }

  public void addAllLocations() throws DaoAddException {
    CSVBackup.loadFromCSV(entryType.LOCATION);
  }

  public void addAllMedEquip() throws DaoAddException {
    CSVBackup.loadFromCSV(entryType.MED_EQUIP);
  }

  public void cleanAllTables() {
    for (String s : tables) {
      cleanTable(s);
    }
  }

  public void cleanTable(String tableName) {
    String sql_string = "DELETE FROM " + tableName;
    try {
      Statement stmt = dbConnection.createStatement();
      stmt.executeUpdate(sql_string);
    } catch (SQLException e) {
      System.out.println("Clearing table failed, check console");
      e.printStackTrace();
    }
  }

  public static LocationDaoImpl getLocationDao() {
    return locationDao;
  }

  public static MedEquipReqDaoImpl getMedEquipReqDao() {
    return medEquipReqDao;
  }

  public static MedEquipDaoImpl getMedEquipDao() {
    return medEquipDao;
  }

  public void shutdownDB() {
    CSVBackup.saveToCSV(entryType.LOCATION);
    CSVBackup.saveToCSV(entryType.MED_EQUIP);
    CSVBackup.saveToCSV(entryType.MED_EQUIP_REQ);

    try {
      dbConnection.close();
      System.out.println("DB Connection Closed");
    } catch (SQLException e) {
      System.out.println("Failed to Shutdown");
      e.printStackTrace();
    }
  }
}
