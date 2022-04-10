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

	public static final String ID_NUMBER = "IDNUMBER";
	public static final String ACCESS = "ACCESS";
	public static final String FLOOR = "FLOOR";
	public static final String NAME = "NAME";
	public static final String ROLE = "ROLE";
	public static final String USERNAME = "USERNAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String ACCESS_LEVEL = "ACCESSLEVEL";
	public static final String AUTH_STRING = "AUTHSTRING";

	private void init(
			String id,
			String nm,
			String rl,
			String acc,
			String flr,
			String usr,
			String pwd,
			int accessLevel,
			String authString) {
		IDNumber = id;
		name = nm;
		role = rl;
		access = acc;
		floor = flr;
		username = usr;
		password = pwd;
		this.accessLevel = accessLevel;
		this.authString = authString;
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
			String authString) {
		init(id, nm, rl, acc, flr, usr, pwd, accessLevel, authString);
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
			authString
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
				args[8]);
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

	public static boolean isValidNewPassword(String password) {
		// password must be at least 5 characters long, and contain at least one number and one letter
		// and one special character
		return password.length() >= 5
				&& password.matches(".*[0-9].*")
				&& (password.matches(".*[A-Z].*") || password.matches(".*[a-z].*"))
				&& password.matches(".*[!@#$%^&*()_+].*");
	}
	// endregion
	public void addAuthMode(AuthTypes type, String[] args) {
		if(type.getArgs() != args.length) {
			throw new IllegalArgumentException("Invalid number of arguments for auth type");
		}
		this.authString +=  (";" + type.getName() + ":" + String.join(":", args));
	}

}
