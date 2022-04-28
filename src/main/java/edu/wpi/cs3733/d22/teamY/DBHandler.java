package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.controllers.DashboardController;
import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

/** Provides an in-between with the Hibernate API. */
public class DBHandler implements DashboardController.EquipmentSubject {

  private static DBHandler myself = null;

  DashboardController.EquipmentMonitor monitor;

  @Override
  public void attachObserver(DashboardController.EquipmentMonitor monitor) {
    this.monitor = monitor;
  }

  /** Single object of the given class. */
  private DBHandler() {}

  public static DBHandler getInstance() {
    if (myself == null) {
      myself = new DBHandler();
    }

    return myself;
  }

  private void pingMonitor() {
    if (monitor != null) {
      monitor.update();
    }
  }

  /**
   * Save an object in the database.
   *
   * @param o The object to save.
   */
  public void handleSave(Object o) {
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

    pingMonitor();
  }

  /**
   * Saves a list of objects.
   *
   * @param objects The list of objects to save.
   */
  public <T> void handleSaveList(List<T> objects) {
    System.out.println("Saving " + objects.size() + " objects");
    for (Object obj : objects) {
      handleSave(obj);
    }

    pingMonitor();
  }

  /**
   * Updates an object in the database.
   *
   * @param o The object to update.
   */
  public void handleUpdate(Object o) {
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

    pingMonitor();
  }

  /**
   * Deletes an object in the database.
   *
   * @param o The object to delete.
   */
  public void handleDelete(Object o) {
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

    pingMonitor();
  }

  /**
   * Deletes an object of the given class.
   *
   * @param c The class of the object to delete.
   * @param id The id of the object to delete.
   */
  public void handleDelete(Class<?> c, Serializable id) {
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

    pingMonitor();
  }

  /**
   * Deletes an object of the given entry type.
   *
   * @param eT The entry type of the object to delete.
   * @param id The id of the object to delete.
   */
  public void handleDelete(EntryType eT, Serializable id) {
    handleDelete(eT.getEntryClass(), id);
  }

  /**
   * Returns a list of objects of the given class.
   *
   * @param c The class of the objects to return.
   * @param <T> The type of the objects to return.
   * @param wheres 0 or more WHERE clauses for the SQL request.
   * @return A list of objects of the given class.
   */
  @SuppressWarnings("unchecked")
  public <T> List<T> handleGetAll(Class<?> c, Where... wheres) {
    Session s = SessionManager.getSession();
    try {
      s.beginTransaction();
      StringBuilder q = new StringBuilder("from " + c.getName());
      if (wheres.length > 0) {
        boolean whereAdded = false;
        for (Where w : wheres) {
          q.append(whereAdded ? " and " : " where ");
          q.append(w.getField()).append(" = :").append(w.getField());

          whereAdded = true;
        }
      }

      org.hibernate.query.Query<T> query = s.createQuery(q.toString());
      for (Where w : wheres) {
        query.setParameter(w.getField(), w.getValue());
      }

      List<T> list = query.list();
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

  /**
   * Returns a list of objects of the given class.
   *
   * @param eT The entry type of the objects to return.
   * @param wheres 0 or more WHERE clauses for the SQL request.
   * @return A list of objects of the given class (as Object).
   */
  public <T extends StringArrayConv> List<T> handleGetAll(EntryType eT, Where... wheres) {
    return handleGetAll(eT.getEntryClass(), wheres);
  }
}
