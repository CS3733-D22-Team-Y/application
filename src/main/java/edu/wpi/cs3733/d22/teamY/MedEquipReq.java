package edu.wpi.cs3733.d22.teamY;

public class MedEquipReq extends DBObject {
  public static final String TABLE_NAME = "medequiprequest";
  public static final String KEY_ATTRIBUTE_NAME = "requestNum";
  private String requestNum;
  private String equipID;
  private String targetLocID;

  @Override
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

  public MedEquipReq(String requestNum, String equipID, String targetLocID) {
    super(TABLE_NAME, KEY_ATTRIBUTE_NAME);
    this.requestNum = requestNum;
    this.equipID = equipID;
    this.targetLocID = targetLocID;
  }

  public String getInsertQuery() {
    return "VALUES("
        + "'"
        + this.requestNum
        + "'"
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

  public MedEquipReq getClone() {
    return new MedEquipReq(requestNum, equipID, targetLocID);
  }
}
