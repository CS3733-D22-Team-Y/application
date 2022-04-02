package edu.wpi.cs3733.d22.teamY.model;

import java.util.List;

public class MedEquipReq implements StringArrayConv {
  private String requestNum;
  private String equipID;
  private String targetLocID;

  public MedEquipReq(String requestNum, String equipID, String targetLocID) {
    this.requestNum = requestNum;
    this.equipID = equipID;
    this.targetLocID = targetLocID;
  }

  public String[] toStringArray() {
    return new String[] {requestNum, equipID, targetLocID};
  }

  public MedEquipReq(List<String> csv) {
    this(csv.get(0), csv.get(1), csv.get(2));
  }

  public String getRequestNum() {
    return requestNum;
  }

  public String getEquipID() {
    return equipID;
  }

  public String getTargetLocID() {
    return targetLocID;
  }
}
