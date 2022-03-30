package edu.wpi.cs3733.d22.teamY;

public class MedEquip extends DBObject {
  public static final String TABLE_NAME = "medequip";
  public static final String KEY_ATTRIBUTE_NAME = "equipID";
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

  public MedEquip(String equipID, String equipType, String equipLocId, boolean isClean) {
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

  public MedEquip getClone() {
    return new MedEquip(equipID, equipType, equipLocId, isClean);
  }
}
