package edu.wpi.cs3733.d22.teamY.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for elements in the locations table in the database. An instance of this class
 * represents a single row in the database. Instances can be used along with the DAO to add, update,
 * and delete rows in the table.
 */
public class Location implements StringArrayConv {
  @Entity
  @Table(name = "locations")
  @Id private String nodeID;
  private int xCoord;
  private int yCoord;
  private String floor;
  private String building;
  private String nodeType;
  private String longName;
  private String shortName;

  public Location() {}

  public Location(
      String nodeID,
      int xCoord,
      int yCoord,
      String floor,
      String building,
      String nodeType,
      String longName,
      String shortName) {
    this.nodeID = nodeID;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.floor = floor;
    this.building = building;
    this.nodeType = nodeType;
    this.longName = longName;
    this.shortName = shortName;
  }

  public Location(List<String> csv) {
    this(
        csv.get(0),
        Integer.parseInt(csv.get(1)),
        Integer.parseInt(csv.get(2)),
        csv.get(3),
        csv.get(4),
        csv.get(5),
        csv.get(6),
        csv.get(7));
  }

  public String[] toStringArray() {
    return new String[] {
      nodeID,
      String.valueOf(xCoord),
      String.valueOf(yCoord),
      floor,
      building,
      nodeType,
      longName,
      shortName
    };
  }

  public String getNodeID() {
    return nodeID;
  }

  public int getXCoord() {
    return xCoord;
  }

  public void setXCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  public int getYCoord() {
    return yCoord;
  }

  public void setYCoord(int yCoord) {
    this.yCoord = yCoord;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
}
