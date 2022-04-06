package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import java.io.Serializable;
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
  public static <T> void saveList(List<T> objects) {
    System.out.println("Saving " + objects.size() + " objects");
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
   * Refresh Location Table when Called.
   * Returns nothing
   */
  public static void refreshLocationsFromCSV() {
    List<StringArrayConv> list = DBManager.getAll(EntryType.LOCATION.getEntryClass());
    //Check if null
    if(list == null){
      return;
    }
    //Delete All from Location List
    for (StringArrayConv o : list) {
      delete(o);
    }
    //Reinitialize
    CSVBackup.loadFromCSV(EntryType.LOCATION);
  }

  /**
   * Deletes an object of the given class.
   *
   * @param c The class of the object to delete.
   * @param id The id of the object to delete.
   */
  public static void delete(Class<?> c, Serializable id) {
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
   * Deletes an object of the given entry type.
   *
   * @param eT The entry type of the object to delete.
   * @param id The id of the object to delete.
   */
  public static void delete(EntryType eT, Serializable id) {
    delete(eT.getEntryClass(), id);
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
  public static <T extends StringArrayConv> List<T> getAll(Class<?> c, Where... wheres) {
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
  public static <T extends StringArrayConv> List<T> getAll(EntryType eT, Where... wheres) {
    return getAll(eT.getEntryClass(), wheres);
  }
}
