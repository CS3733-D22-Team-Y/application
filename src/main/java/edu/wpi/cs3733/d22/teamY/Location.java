package edu.wpi.cs3733.d22.teamY;

public class Location extends DBObject {
  public static final String TABLE_NAME = "LOCATIONS";
  public static final String KEY_ATTRIBUTE_NAME = "NODEID";
  private String nodeID;
  private int xCoord, yCoord;
  private String floor;
  private String building;
  private String nodeType;
  private String longName;
  private String shortName;

  public Location(
      String nodeID,
      int xCoord,
      int yCoord,
      String floor,
      String building,
      String nodeType,
      String longName,
      String shortName) {
    super(TABLE_NAME, KEY_ATTRIBUTE_NAME);
    this.nodeID = nodeID;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.floor = floor;
    this.building = building;
    this.nodeType = nodeType;
    this.longName = longName;
    this.shortName = shortName;
  }

  public Location(String id) {
    super(TABLE_NAME, KEY_ATTRIBUTE_NAME);
    nodeID = id;
    xCoord = 0;
    yCoord = 0;
    floor = "";
    building = "";
    nodeType = "";
    longName = "";
    shortName = "";
  }

  public String getKey() {
    return nodeID;
  }

  public int getXCoord() {
    return xCoord;
  }

  public int getYCoord() {
    return yCoord;
  }

  public String getFloor() {
    return floor;
  }

  public String getBuilding() {
    return building;
  }

  public String getNodeType() {
    return nodeType;
  }

  public String getLongName() {
    return longName;
  }

  public String getShortName() {
    return shortName;
  }

  public void setXCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  public void setYCoord(int yCoord) {
    this.yCoord = yCoord;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  @Override
  public String toString() {
    return "Location{"
        + "nodeID='"
        + nodeID
        + '\''
        + ", xCoord="
        + xCoord
        + ", yCoord="
        + yCoord
        + ", floor='"
        + floor
        + '\''
        + ", building='"
        + building
        + '\''
        + ", nodeType='"
        + nodeType
        + '\''
        + ", longName='"
        + longName
        + '\''
        + ", shortName='"
        + shortName
        + '\''
        + '}';
  }

  public String getInsertQuery() {
    return "VALUES("
        + "'"
        + this.nodeID
        + "'"
        + ", "
        + this.xCoord
        + ", "
        + this.yCoord
        + ", "
        + "'"
        + this.floor
        + "'"
        + ", "
        + "'"
        + this.building
        + "'"
        + ", "
        + "'"
        + this.nodeType
        + "'"
        + ", "
        + "'"
        + this.longName
        + "'"
        + ", "
        + "'"
        + this.shortName
        + "'"
        + ")";
  }

  public Location getClone() {
    return new Location(nodeID, xCoord, yCoord, floor, building, nodeType, longName, shortName);
  }
}
