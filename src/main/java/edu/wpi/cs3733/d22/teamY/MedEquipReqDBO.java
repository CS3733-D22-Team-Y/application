package edu.wpi.cs3733.d22.teamY;

public class MedEquipReqDBO extends DBObject {
  public static final String TABLE_NAME = "MEDEQUIPREQUEST";
  public static final String KEY_ATTRIBUTE_NAME = "REQUESTNUM";
  private String requestNum;
  private String equipID;
  private String targetLocID;

  public String getKey() {
    return requestNum;
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

  public MedEquipReqDBO(String requestNum, String equipID, String targetLocID) {
    super(TABLE_NAME, KEY_ATTRIBUTE_NAME);
    this.requestNum = requestNum;
    this.equipID = equipID;
    this.targetLocID = targetLocID;
  }

  public String getInsertQuery() {
    return "VALUES("
        + Integer.parseInt(this.requestNum)
        + ", "
        + "'"
        + this.equipID
        + "'"
        + ", "
        + "'"
        + this.targetLocID
        + "'"
        + ")";
  }

  public MedEquipReqDBO getClone() {
    return new MedEquipReqDBO(requestNum, equipID, targetLocID);
  }
}
