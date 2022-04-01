package edu.wpi.cs3733.d22.teamY.model.dao.impl;

import edu.wpi.cs3733.d22.teamY.DatabaseConnection;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.dao.MedEquipDao;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MedEquipDaoImpl implements MedEquipDao {
  static Connection conn = DatabaseConnection.getConnection();

  @Override
  public List<MedEquip> getAllMedEquip() throws DaoGetException {
    List<MedEquip> medEquips = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM MEDEQUIP");
      while (rs.next()) {
        medEquips.add(
            new MedEquip(
                rs.getString("equipID"),
                rs.getString("equipType"),
                rs.getString("equipLocId"),
                rs.getString("isClean")));
      }
      return medEquips;
    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public MedEquip getMedEquip(String equipID) throws DaoGetException {
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MEDEQUIP WHERE EQUIPID = ?");
      stmt.setString(1, equipID);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return new MedEquip(
            rs.getString("equipID"),
            rs.getString("equipType"),
            rs.getString("equipLocId"),
            rs.getString("isClean"));
      } else {
        throw new DaoGetException(new Exception("No Equipment with equipID = " + equipID));
      }
    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public void addMedEquip(MedEquip medEquip) throws DaoAddException {
    try {
      PreparedStatement stmt =
          conn.prepareStatement(
              "INSERT INTO MEDEQUIP (EQUIPID, EQUIPTYPE, NODEID, ISCLEAN) values (?, ?, ?, ?)");
      stmt.setString(1, medEquip.getEquipID());
      stmt.setString(2, medEquip.getEquipType());
      stmt.setString(3, medEquip.getEquipLocID());
      stmt.setString(4, medEquip.isClean());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DaoAddException(e);
    }
  }

  @Override
  public void updateMedEquip(MedEquip medEquip) throws DaoUpdateException {
    try {
      PreparedStatement stmt =
          conn.prepareStatement(
              "UPDATE MEDEQUIP SET EQUIPTYPE = ?, NODEID = ?, ISCLEAN = ? WHERE EQUIPID = ?");
      stmt.setString(1, medEquip.getEquipType());
      stmt.setString(2, medEquip.getEquipType());
      stmt.setString(3, medEquip.isClean());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DaoUpdateException(e);
    }
  }

  @Override
  public void deleteMedEquip(MedEquip medEquip) throws DaoDeleteException {
    try {
      PreparedStatement stmt = conn.prepareStatement("DELETE FROM MEDEQUIP WHERE NODEID = ?");
      stmt.setString(1, medEquip.getEquipID());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DaoDeleteException(e);
    }
  }
}
