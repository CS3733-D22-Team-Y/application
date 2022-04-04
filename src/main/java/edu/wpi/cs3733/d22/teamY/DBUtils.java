package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.Location;
import java.util.List;
import org.hibernate.Session;

/** This class is used to create specific queries for the database. */
public class DBUtils {

  private DBUtils() {}

  @SuppressWarnings("unchecked")
  public static List<Location> getLocationsOnFloor(String floor) {
    Session s = SessionManager.getSession();
    List<Location> locations =
        s.createQuery("from Location where floor = :floor").setParameter("floor", floor).list();
    s.close();
    return locations;
  }
}
