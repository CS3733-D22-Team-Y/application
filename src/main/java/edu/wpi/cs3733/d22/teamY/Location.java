package edu.wpi.cs3733.d22.teamY;

public class Location {
  String nodeID;
  int xCoord, yCoord;
  String floor;
  String building;
  String nodeType;
  String longName;
  String shortName;

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

  public Location(String id) {
    nodeID = id;
    xCoord = 0;
    yCoord = 0;
    floor = "";
    building = "";
    nodeType = "";
    longName = "";
    shortName = "";
  }

  public Location getClone() {
    return new Location(nodeID, xCoord, yCoord, floor, building, nodeType, longName, shortName);
  }
}
