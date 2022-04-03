package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medequip")
public class MedEquip implements StringArrayConv {
  @Id private String equipID;
  private String equipType;
  private String equipLocId;
  private String isClean;

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
}
