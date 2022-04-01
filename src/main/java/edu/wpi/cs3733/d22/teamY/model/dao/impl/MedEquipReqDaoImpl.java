package edu.wpi.cs3733.d22.teamY.model.dao.impl;

import edu.wpi.cs3733.d22.teamY.DatabaseConnection;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.dao.MedEquipReqDao;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedEquipReqDaoImpl implements MedEquipReqDao {
  static Connection conn = DatabaseConnection.getConnection();

  @Override
  public List<MedEquipReq> getAllMedEquipReq() throws DaoGetException {
    List<MedEquipReq> medEquipReqs = new ArrayList<>();
    try {
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery("SELECT * FROM MEDEQUIPREQUEST");
      while (rs.next()) {
        medEquipReqs.add(
            new MedEquipReq(
                Integer.toString(
                    rs.getInt("REQUESTNUM")), // TODO fix type mismatch in class and table?
                rs.getString("EQUIPID"),
                rs.getString("TARGETNODEID")));
      }
      return medEquipReqs;
    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public MedEquipReq getMedEquipReq(String requestNum) throws DaoGetException {
    try {
      PreparedStatement s =
          conn.prepareStatement("SELECT * FROM MEDEQUIPREQUEST WHERE REQUESTNUM = ?");
      s.setInt(1, Integer.parseInt(requestNum));
      ResultSet rs = s.executeQuery();
      if (rs.next()) {
        return new MedEquipReq(
            Integer.toString(rs.getInt("REQUESTNUM")), // TODO fix type mismatch in class and table?
            rs.getString("EQUIPID"),
            rs.getString("TARGETNODEID"));
      } else {
        throw new DaoGetException(
            new Exception("No Medical Equipment Request with Request Number = " + requestNum));
      }

    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public void addMedEquipReq(MedEquipReq mreq) throws DaoAddException {
    try {
      PreparedStatement s =
          conn.prepareStatement(
              "INSERT INTO MEDEQUIPREQUEST (REQUESTNUM, EQUIPID, TARGETNODEID) values (?, ?, ?)");
      s.setInt(1, Integer.parseInt(mreq.getRequestNum()));
      s.setString(2, mreq.getEquipID());
      s.setString(3, mreq.getTargetLocID());
      s.executeUpdate();
    } catch (SQLException e) {
      throw new DaoAddException(e);
    }
  }

  @Override
  public void updateMedEquipReq(MedEquipReq mreq) throws DaoUpdateException {
    try {
      PreparedStatement s =
          conn.prepareStatement(
              "UPDATE MEDEQUIPREQUEST SET EQUIPID = ?, TARGETNODEID = ? WHERE REQUESTNUM = ?");
      s.setString(1, mreq.getEquipID());
      s.setString(2, mreq.getTargetLocID());
      s.setInt(3, Integer.parseInt(mreq.getRequestNum()));
      s.executeUpdate();
    } catch (SQLException e) {
      throw new DaoUpdateException(e);
    }
  }

  @Override
  public void deleteMedEquipReq(MedEquipReq mreq) throws DaoDeleteException {
    try {
      PreparedStatement s =
          conn.prepareStatement("DELETE FROM MEDEQUIPREQUEST WHERE REQUESTNUM = ?");
      s.setString(1, mreq.getRequestNum());
      s.executeUpdate();
    } catch (SQLException e) {
      throw new DaoDeleteException(e);
    }
  }
}
