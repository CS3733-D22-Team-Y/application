package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import java.io.Serializable;
import java.util.List;

/** DBHandler Facade for use of Hibernate API. */
public class DBManager {

  /** Single object of the given class. */
  private DBManager() {}

  /**
   * Save an object in the database.
   *
   * @param o The object to save.
   */
  public static void save(Object o) {
    DBHandler.getInstance().handleSave(o);
  }

  /**
   * Saves a list of objects.
   *
   * @param objects The list of objects to save.
   */
  public static <T> void saveList(List<T> objects) {
    DBHandler.getInstance().handleSaveList(objects);
  }

  /**
   * Updates an object in the database.
   *
   * @param o The object to update.
   */
  public static void update(Object o) {
    DBHandler.getInstance().handleUpdate(o);
  }

  /**
   * Deletes an object in the database.
   *
   * @param o The object to delete.
   */
  public static void delete(Object o) {
    DBHandler.getInstance().handleDelete(o);
  }

  /**
   * Deletes an object of the given class.
   *
   * @param c The class of the object to delete.
   * @param id The id of the object to delete.
   */
  public static void delete(Class<?> c, Serializable id) {
    DBHandler.getInstance().handleDelete(c, id);
  }

  /**
   * Deletes an object of the given entry type.
   *
   * @param eT The entry type of the object to delete.
   * @param id The id of the object to delete.
   */
  public static void delete(EntryType eT, Serializable id) {
    DBHandler.getInstance().handleDelete(eT, id);
  }

  /**
   * Returns a list of objects of the given class.
   *
   * @param c The class of the objects to return.
   * @param <T> The type of the objects to return.
   * @param wheres 0 or more WHERE clauses for the SQL request.
   * @return A list of objects of the given class.
   */
  public static <T> List<T> getAll(Class<?> c, Where... wheres) {
    return DBHandler.getInstance().handleGetAll(c, wheres);
  }

  /**
   * Returns a list of objects of the given class.
   *
   * @param eT The entry type of the objects to return.
   * @param wheres 0 or more WHERE clauses for the SQL request.
   * @return A list of objects of the given class (as Object).
   */
  public static <T extends StringArrayConv> List<T> getAll(EntryType eT, Where... wheres) {
    return DBHandler.getInstance().handleGetAll(eT, wheres);
  }
}
