package edu.wpi.cs3733.d22.teamY;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** This class is used to manage the Hibernate session. */
public class SessionManager {
  private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();

  private SessionManager() {}

  /**
   * Use only if you know what you are doing. Close the session after use.
   *
   * @return Session object
   */
  public static Session getSession() {
    return sf.openSession();
  }
}
