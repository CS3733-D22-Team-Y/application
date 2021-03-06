package edu.wpi.cs3733.d22.teamY;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** This class is used to manage the Hibernate session. */
public class SessionManager {

  private static SessionFactory sf =
      new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

  private SessionManager() {}

  /**
   * Use only if you know what you are doing. Close the session after use.
   *
   * @return Session object
   */
  public static Session getSession() {
    return sf.openSession();
  }

  /**
   * Changes DB Connection
   *
   * @param type string of the DB type to switch to. Handled by front end.
   */
  public static void switchType(String type) {
    switch (type) {
      case "Embedded":
        System.out.println("Switching to Embedded...");
        sf.close();
        sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        break;
      case "Client-Server":
        System.out.println("Switching to Client-Server...");
        sf.close();
        sf = new Configuration().configure("hibernate_server.cfg.xml").buildSessionFactory();
        break;
    }
  }
}
