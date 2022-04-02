package edu.wpi.cs3733.d22.teamY;

import java.util.List;
import org.hibernate.Session;

/** Provides utility methods to use Hibernate API. */
public class DBManager {

  /** Single object of the given class. */
  private DBManager() {}

  /**
   * Save an object in the database.
   *
   * @param o The object to save.
   */
  public static void save(Object o) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      s.save(o);
      s.getTransaction().commit();
      s.close();
    } catch (Exception e) {
      s.getTransaction().rollback();
      s.close();
      System.out.println(e.getMessage());
    }
  }

  /**
   * Saves a list of objects.
   *
   * @param objects The list of objects to save.
   */
  public static void save(List<Object> objects) {
    for (Object obj : objects) {
      save(obj);
    }
  }

  /**
   * Updates an object in the database.
   *
   * @param o The object to update.
   */
  public static void update(Object o) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      s.update(o);
      s.getTransaction().commit();
    } catch (Exception e) {
      s.getTransaction().rollback();
      s.close();
      System.out.println(e.getMessage());
    }
  }

  /**
   * Deletes an object in the database.
   *
   * @param o The object to delete.
   */
  public static void delete(Object o) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      s.delete(o);
      s.getTransaction().commit();
      s.close();
    } catch (Exception e) {
      s.getTransaction().rollback();
      s.close();
      System.out.println(e.getMessage());
    }
  }

  /**
   * Deletes an object of the given class.
   *
   * @param c The class of the object to delete.
   * @param id The id of the object to delete.
   */
  public static void delete(Class<?> c, int id) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      Object o = s.get(c, id);
      s.delete(o);
      s.getTransaction().commit();
      s.close();
    } catch (Exception e) {
      s.getTransaction().rollback();
      s.close();
      System.out.println(e.getMessage());
    }
  }

  /**
   * Deletes an object of the given class.
   *
   * @param c The class of the object to delete.
   * @param id The id of the object to delete.
   */
  public static void delete(Class<?> c, String id) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      Object o = s.get(c, id);
      s.delete(o);
      s.getTransaction().commit();
      s.close();
    } catch (Exception e) {
      s.getTransaction().rollback();
      s.close();
      System.out.println(e.getMessage());
    }
  }

  /**
   * Returns a list of objects of the given class.
   *
   * @param c The class of the objects to return.
   * @param <T> The type of the objects to return.
   * @return A list of objects of the given class.
   */
  @SuppressWarnings({"unchecked"})
  public static <T> List<T> getAll(Class<?> c) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      List<T> list = s.createQuery("from " + c.getName()).list();
      s.getTransaction().commit();
      s.close();
      return list;
    } catch (Exception e) {
      s.getTransaction().rollback();
      s.close();
      System.out.println(e.getMessage());
      return null;
    }
  }
}
