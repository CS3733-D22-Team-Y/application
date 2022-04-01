package edu.wpi.cs3733.d22.teamY.model.dao.impl;

import edu.wpi.cs3733.d22.teamY.DatabaseConnection;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.dao.LocationDao;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoImpl implements LocationDao {
  static Connection conn = DatabaseConnection.getConnection();

  @Override
  public List<Location> getAllLocations() throws DaoGetException {
    List<Location> locations = new ArrayList<>();
    try {
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery("SELECT * FROM LOCATIONS");
      while (rs.next()) {
        locations.add(
            new Location(
                rs.getString("NODEID"),
                rs.getInt("XCOORD"),
                rs.getInt("YCOORD"),
                rs.getString("FLOOR"),
                rs.getString("BUILDING"),
                rs.getString("NODETYPE"),
                rs.getString("LONGNAME"),
                rs.getString("SHORTNAME")));
      }
      return locations;
    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public List<Location> getLocationsOnFloor(String floor) throws DaoGetException {
    List<Location> locations = new ArrayList<>();
    try {
      PreparedStatement s = conn.prepareStatement("SELECT * FROM LOCATIONS WHERE FLOOR = ?");
      s.setString(1, floor);
      ResultSet rs = s.executeQuery();
      while (rs.next()) {
        locations.add(
            new Location(
                rs.getString("NODEID"),
                rs.getInt("XCOORD"),
                rs.getInt("YCOORD"),
                rs.getString("FLOOR"),
                rs.getString("BUILDING"),
                rs.getString("NODETYPE"),
                rs.getString("LONGNAME"),
                rs.getString("SHORTNAME")));
      }
      return locations;
    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public Location getLocation(String nodeID) throws DaoGetException {
    try {
      PreparedStatement s = conn.prepareStatement("SELECT * FROM LOCATIONS WHERE NODEID = ?");
      s.setString(1, nodeID);
      ResultSet rs = s.executeQuery();
      if (rs.next()) {
        return new Location(
            rs.getString("NODEID"),
            rs.getInt("XCOORD"),
            rs.getInt("YCOORD"),
            rs.getString("FLOOR"),
            rs.getString("BUILDING"),
            rs.getString("NODETYPE"),
            rs.getString("LONGNAME"),
            rs.getString("SHORTNAME"));
      } else {
        throw new DaoGetException(new Exception("No location with nodeID = " + nodeID));
      }

    } catch (SQLException e) {
      throw new DaoGetException(e);
    }
  }

  @Override
  public void addLocation(Location location) throws DaoAddException {
    try {
      PreparedStatement s =
          conn.prepareStatement(
              "INSERT INTO LOCATIONS (NODEID, XCOORD, YCOORD, FLOOR, BUILDING, NODETYPE, LONGNAME, SHORTNAME) values (?, ?, ?, ?, ?, ?, ?, ?)");
      s.setString(1, location.getNodeID());
      s.setInt(2, location.getXCoord());
      s.setInt(3, location.getYCoord());
      s.setString(4, location.getFloor());
      s.setString(5, location.getBuilding());
      s.setString(6, location.getNodeType());
      s.setString(7, location.getLongName());
      s.setString(8, location.getShortName());
      s.executeUpdate();
    } catch (SQLException e) {
      throw new DaoAddException(e);
    }
  }

  @Override
  public void updateLocation(Location location) throws DaoUpdateException {
    try {
      PreparedStatement s =
          conn.prepareStatement(
              "UPDATE LOCATIONS SET XCOORD = ?, YCOORD = ?, FLOOR = ?, BUILDING = ?, NODETYPE = ?, LONGNAME = ?, SHORTNAME = ? WHERE NODEID = ?");
      s.setInt(1, location.getXCoord());
      s.setInt(2, location.getYCoord());
      s.setString(3, location.getFloor());
      s.setString(4, location.getBuilding());
      s.setString(5, location.getNodeType());
      s.setString(6, location.getLongName());
      s.setString(7, location.getShortName());
      s.setString(8, location.getNodeID());
      s.executeUpdate();
    } catch (SQLException e) {
      throw new DaoUpdateException(e);
    }
  }

  @Override
  public void deleteLocation(Location location) throws DaoDeleteException {
    try {
      PreparedStatement s = conn.prepareStatement("DELETE FROM LOCATIONS WHERE NODEID = ?");
      s.setString(1, location.getNodeID());
      s.executeUpdate();
    } catch (SQLException e) {
      throw new DaoDeleteException(e);
    }
  }
}
