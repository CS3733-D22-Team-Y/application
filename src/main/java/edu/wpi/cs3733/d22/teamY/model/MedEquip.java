package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for elements in the medical equipment table in the database. An instance of this
 * class represents a single row in the database. Instances can be used along with the DBManager to
 * add, update, and delete rows in the table.
 */
@Entity
@Table(name = "MEDEQUIP")
public class MedEquip implements StringArrayConv {
  @Id private String equipID;
  private String equipType;
  private String equipLocId;
  private String isClean;

  public static final String EQUIP_ID = "EQUIPID";
  public static final String EQUIP_LOC_ID = "EQUIPLOCID";
  public static final String EQUIP_TYPE = "EQUIPTYPE";
  public static final String IS_CLEAN = "ISCLEAN";

  private void init(String eID, String eType, String eLocId, String clean) {
    equipID = eID;
    equipType = eType;
    equipLocId = eLocId;
    isClean = clean;
  }

  public MedEquip() {}

  public MedEquip(String equipID, String equipType, String equipLocId, String isClean) {
    init(equipID, equipType, equipLocId, isClean);
  }

  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3]);
  }

  public String[] toStringArray() {
    return new String[] {equipID, equipType, equipLocId, isClean};
  }

  @Override
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
        + ", isClean='"
        + isClean
        + '\''
        + '}';
  }

  // region Getters/Setters
  public String getEquipID() {
    return equipID;
  }

  public String getEquipType() {
    return equipType;
  }

  public String getEquipLocId() {
    return equipLocId;
  }

  public String isClean() {
    return isClean;
  }

  public void setEquipID(String eID) {
    equipID = eID;
  }

  public void setEquipType(String eType) {
    equipType = eType;
  }

  public void setEquipLocId(String eLocId) {
    equipLocId = eLocId;
  }

  public void setClean(String clean) {
    isClean = clean;
  }
  // endregion
}
