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
	* Changes Type of Derby Server
	*
	* @param type false For Embedded, true for Client-Server
	*/
	public static void switchType(boolean type) {
		if (type == false) {
			System.out.println("Switching to Embedded...");
			sf.close();
			sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		if (type == true) {
			System.out.println("Switching to Client-Server...");
			sf.close();
			sf = new Configuration().configure("hibernate_server.cfg.xml").buildSessionFactory();
		}
	}
}
