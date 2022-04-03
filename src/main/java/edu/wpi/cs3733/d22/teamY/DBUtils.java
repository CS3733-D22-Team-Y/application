package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
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

  @SuppressWarnings("unchecked")
  public static String getAvailableEquipment(String equipType) {
    Session s = SessionManager.getSession();
    List<MedEquip> equipment =
        s.createQuery("from MedEquip where equipType = :equipType")
            .setParameter("equipType", equipType)
            .list();
    s.close();

    int total = equipment.size();
    int available = 0;

    for (MedEquip i : equipment) {
      if (i.isClean().equals("1")) {
        available++;
      }
    }

    return (available + " of " + total);
  }
}
