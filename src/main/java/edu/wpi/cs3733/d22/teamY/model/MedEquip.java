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

  private void init(String equipID, String equipType, String equipLocId, String isClean) {
    this.equipID = equipID;
    this.equipType = equipType;
    this.equipLocId = equipLocId;
    this.isClean = isClean;
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

  public void setEquipID(String equipID) {
    this.equipID = equipID;
  }

  public void setEquipType(String equipType) {
    this.equipType = equipType;
  }

  public void setEquipLocId(String equipLocId) {
    this.equipLocId = equipLocId;
  }

  public void setClean(String isClean) {
    this.isClean = isClean;
  }
}
