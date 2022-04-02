package edu.wpi.cs3733.d22.teamY;

import org.hibernate.Session;

import java.util.List;

/**
 * Provides utility methods to use Hibernate API.
 */
public class DBUtil {

    private DBUtil() {}

    /**
     * Save an object in the database.
     * @param o The object to save.
     */
    public static void save(Object o){
        Session s = SessionManager.getSession();
        s.beginTransaction();
        s.save(o);
        s.getTransaction().commit();
        s.close();
    }

    /**
     * Updates an object in the database.
     * @param o The object to update.
     */
    public static void update(Object o){
        Session s = SessionManager.getSession();
        s.beginTransaction();
        s.update(o);
        s.getTransaction().commit();
        s.close();
    }

    /**
     * Deletes an object in the database.
     * @param o The object to delete.
     */
    public static void delete(Object o){
        Session s = SessionManager.getSession();
        s.beginTransaction();
        s.delete(o);
        s.getTransaction().commit();
        s.close();
    }

    /**
     * Deletes an object of the given class.
     * @param c The class of the object to delete.
     * @param id The id of the object to delete.
     */
    public static void delete(Class<?> c, int id){
        Session s = SessionManager.getSession();
        s.beginTransaction();
        Object o = s.get(c, id);
        s.delete(o);
        s.getTransaction().commit();
        s.close();
    }

    /**
     * Deletes an object of the given class.
     * @param c The class of the object to delete.
     * @param id The id of the object to delete.
     */
    public static void delete(Class<?> c, String id){
        Session s = SessionManager.getSession();
        s.beginTransaction();
        Object o = s.get(c, id);
        s.delete(o);
        s.getTransaction().commit();
        s.close();
    }


    /**
     * Returns a list of objects of the given class.
     * @param c The class of the objects to return.
     * @param <T> The type of the objects to return.
     * @return A list of objects of the given class.
     */
    @SuppressWarnings({"unchecked"})
    public static <T> List<T> getAll(Class<?> c){
        Session s = SessionManager.getSession();
        s.beginTransaction();
        List<T> list = s.createQuery("from " + c.getName()).list();
        s.close();
        return list;
    }

}
