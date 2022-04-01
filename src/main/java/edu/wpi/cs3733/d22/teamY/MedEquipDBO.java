package edu.wpi.cs3733.d22.teamY;

public class MedEquipDBO extends DBObject {
  public static final String TABLE_NAME = "MEDEQUIP";
  public static final String KEY_ATTRIBUTE_NAME = "EQUIPID";
  private String equipID;
  private String equipType;
  private String equipLocId;

  public String getEquipID() {
    return equipID;
  }

  public String getEquipType() {
    return equipType;
  }

  public String getEquipLocId() {
    return equipLocId;
  }

  public boolean isClean() {
    return isClean;
  }

  public void setEquipID(String equipID) {
    this.equipID = equipID;
  }

  public void setEquipType(String equipType) {
    this.equipType = equipType;
  }

  public void setEquipLocId(String equipLocId) {
    this.equipLocId = equipLocId;
  }

  public void setClean(boolean isClean) {
    this.isClean = isClean;
  }

  private boolean isClean;

  public MedEquipDBO(String equipID, String equipType, String equipLocId, boolean isClean) {
    super(TABLE_NAME, KEY_ATTRIBUTE_NAME);
    this.equipID = equipID;
    this.equipType = equipType;
    this.equipLocId = equipLocId;
    this.isClean = isClean;
  }

  public String getID() {
    return equipID;
  }

  @Override
  public String getKey() {
    return equipID;
  }

  @Override
  public String getInsertQuery() {
    return "VALUES("
        + "'"
        + this.equipID
        + "'"
        + ", "
        + "'"
        + this.equipType
        + "'"
        + ", "
        + "'"
        + this.equipLocId
        + "'"
        + ", "
        + "'"
        + this.isClean
        + "'"
        + ")";
  }

  public String toString() {
    return "MedEquip{"
        + "equipID='"
        + equipID
        + '\''
        + ", equipType='"
        + equipType
        + '\''
        + ", equipLocId='"
        + equipLocId
        + '\''
        + ", isClean="
        + isClean
        + '}';
  }

  public MedEquipDBO getClone() {
    return new MedEquipDBO(equipID, equipType, equipLocId, isClean);
  }
}
