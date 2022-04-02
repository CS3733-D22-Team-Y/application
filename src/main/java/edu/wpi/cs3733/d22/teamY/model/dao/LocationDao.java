package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.util.List;

public abstract class LocationDao extends Dao<Location> {

  public abstract List<Location> getAllLocations() throws DaoGetException;

  public abstract List<Location> getLocationsOnFloor(String floor) throws DaoGetException;

  public abstract Location getLocation(String nodeID) throws DaoGetException;

  public abstract void addLocation(Location location) throws DaoAddException;

  public abstract void updateLocation(Location location) throws DaoUpdateException;

  public abstract void deleteLocation(Location location) throws DaoDeleteException;
}
