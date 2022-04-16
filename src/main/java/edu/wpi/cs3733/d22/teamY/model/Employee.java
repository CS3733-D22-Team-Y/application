package edu.wpi.cs3733.d22.teamY.model;

import edu.wpi.cs3733.d22.teamY.AuthTypes;
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
  private String username;
  private String password;
  private int accessLevel;
  private String authString;
  private String prefName;
  private String email;
  private String phone;
  private String dob;
  private String pronouns;
  private String theme = "LIGHT";

  public static final String ID_NUMBER = "IDNUMBER";
  public static final String ACCESS = "ACCESS";
  public static final String FLOOR = "FLOOR";
  public static final String NAME = "NAME";
  public static final String ROLE = "ROLE";
  public static final String USERNAME = "USERNAME";
  public static final String PASSWORD = "PASSWORD";
  public static final String ACCESS_LEVEL = "ACCESSLEVEL";
  public static final String AUTH_STRING = "AUTHSTRING";
  public static final String PREF_NAME = "PREFNAME";
  public static final String EMAIL = "EMAIL";
  public static final String PHONE = "PHONE";
  public static final String DOB = "DOB";
  public static final String PRONOUNS = "PRONOUNS";

  private void init(
      String id,
      String nm,
      String rl,
      String acc,
      String flr,
      String usr,
      String pwd,
      int accessLevel,
      String authString,
      String prefName,
      String email,
      String phone,
      String dob,
      String pronouns) {
    IDNumber = id;
    name = nm;
    role = rl;
    access = acc;
    floor = flr;
    username = usr;
    password = pwd;
    this.accessLevel = accessLevel;
    this.authString = authString;
    this.prefName = prefName;
    this.email = email;
    this.phone = phone;
    this.dob = dob;
    this.pronouns = pronouns;
  }

  public Employee() {}

  public Employee(
      String id,
      String nm,
      String rl,
      String acc,
      String flr,
      String usr,
      String pwd,
      int accessLevel,
      String authString,
      String prefName,
      String email,
      String phone,
      String dob,
      String pronouns) {
    init(
        id,
        nm,
        rl,
        acc,
        flr,
        usr,
        pwd,
        accessLevel,
        authString,
        prefName,
        email,
        phone,
        dob,
        pronouns);
  }

  public String[] toStringArray() {
    return new String[] {
      IDNumber,
      name,
      role,
      access,
      floor,
      username,
      password,
      Integer.toString(accessLevel),
      authString,
      prefName.length() == 0 ? " " : prefName,
      email.length() == 0 ? " " : email,
      phone.length() == 0 ? " " : phone,
      dob.length() == 0 ? " " : dob,
      pronouns.length() == 0 ? " " : pronouns
    };
  }

  public void fromStringArray(String[] args) {
    init(
        args[0],
        args[1],
        args[2],
        args[3],
        args[4],
        args[5],
        args[6],
        Integer.parseInt(args[7]),
        args[8],
        args[9],
        args[10],
        args[11],
        args[12],
        args[13]);
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

  public void setPassword(String newPassword) {
    password = newPassword;
  }

  public String getPassword() {
    return password;
  }

  public int getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(int newAccessLevel) {
    accessLevel = newAccessLevel;
  }

  public String getAuthString() {
    return authString;
  }

  public void setAuthString(String authString) {
    this.authString = authString;
  }

  public String getPrefName() {
    return prefName;
  }

  public void setPrefName(String prefName) {
    this.prefName = prefName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDOB() {
    return dob;
  }

  public void setDOB(String dob) {
    this.dob = dob;
  }

  public String getPronouns() {
    return pronouns;
  }

  public void setPronouns(String pronouns) {
    this.pronouns = pronouns;
  }

  public void setTheme(String t) {
    theme = t;
  }

  public String getTheme() {
    return theme;
  }

  public static boolean isValidNewPassword(String password) {
    // password must be at least 5 characters long, and contain at least one number and one letter
    // and one special character
    return password.length() >= 5
        && password.matches(".*[0-9].*")
        && (password.matches(".*[A-Z].*") || password.matches(".*[a-z].*"))
        && password.matches(".*[!@#$%^&*()_+].*");
  }

  public void addAuthMode(AuthTypes type, String[] args) {
    if (type.getArgs() != args.length) {
      throw new IllegalArgumentException("Invalid number of arguments for auth type");
    }
    this.authString += (type.getName() + ":" + String.join(":", args) + ";");
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
