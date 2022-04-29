package edu.wpi.cs3733.d22.teamY.model;

import edu.wpi.cs3733.d22.teamY.controllers.MapPageController;
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
  private String status;

  public static final String EQUIP_ID = "EQUIPID";
  public static final String EQUIP_LOC_ID = "EQUIPLOCID";
  public static final String EQUIP_TYPE = "EQUIPTYPE";
  public static final String IS_CLEAN = "ISCLEAN";
  public static final String STATUS = "STATUS";

  private void init(String eID, String eType, String eLocId, String clean, String status) {
    equipID = eID;
    equipType = eType;
    equipLocId = eLocId;
    isClean = clean;
    this.status = status;
  }

  public MedEquip() {}

  public MedEquip(
      String equipID, String equipType, String equipLocId, String isClean, String status) {
    init(equipID, equipType, equipLocId, isClean, status);
  }

  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4]);
  }

  public String[] toStringArray() {
    return new String[] {equipID, equipType, equipLocId, isClean, status};
  }

  @Override
  public String toString() {
    String clean;
    if (Integer.parseInt(isClean) == 1) {
      clean = "Clean";
    } else {
      clean = "Dirty";
    }
    return "\n" + MapPageController.equipNames.get(equipType) + " : " + equipID + " : " + clean;
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

  public String getIsClean() {
    return isClean;
  }

  public String getStatus() {
    return status;
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

  public void setIsClean(String clean) {
    isClean = clean;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  // endregion
}
