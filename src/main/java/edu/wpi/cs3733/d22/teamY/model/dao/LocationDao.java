package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.util.List;

public interface LocationDao {
  List<Location> getAllLocations() throws DaoGetException;

  List<Location> getLocationsOnFloor(String floor) throws DaoGetException;

  Location getLocation(String nodeID) throws DaoGetException;

  void addLocation(Location location) throws DaoAddException;

  void updateLocation(Location location) throws DaoUpdateException;

  void deleteLocation(Location location) throws DaoDeleteException;
}
