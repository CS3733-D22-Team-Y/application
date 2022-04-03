package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for elements in the employee table in the database. An instance of this class
 * represents a single row in the database. Instances can be used along with the DBManager to add,
 * update, and delete rows in the table.
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee implements StringArrayConv {
  @Id private String IDNumber;
  private String name;
  private String role;
  private String access;
  private String floor;

  public static final String ID_NUMBER = "IDNUMBER";
  public static final String ACCESS = "ACCESS";
  public static final String FLOOR = "FLOOR";
  public static final String NAME = "NAME";
  public static final String ROLE = "ROLE";

  private void init(String id, String nm, String rl, String acc, String flr) {
    IDNumber = id;
    name = nm;
    role = rl;
    access = acc;
    floor = flr;
  }

  public Employee() {}

  public Employee(String id, String nm, String rl, String acc, String flr) {
    init(id, nm, rl, acc, flr);
  }

  public String[] toStringArray() {
    return new String[] {IDNumber, name, role, access, floor};
  }

  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4]);
  }

  // region Getters/Setters
  public String getIDNumber() {
    return IDNumber;
  }

  public void setIDNumber(String id) {
    IDNumber = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String nm) {
    name = nm;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String rl) {
    role = rl;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String acc) {
    access = acc;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String flr) {
    floor = flr;
  }
  // endregion
}
