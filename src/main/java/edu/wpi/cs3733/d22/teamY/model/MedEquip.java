package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medequip")
public class MedEquip {
  @Id private String equipID;
  private String equipType;
  private String equipLocID;
  private String isClean;

  public MedEquip() {}

  public MedEquip(String equipID, String equipType, String equipLocId, String isClean) {
    this.equipID = equipID;
    this.equipType = equipType;
    this.equipLocID = equipLocId;
    this.isClean = isClean;
  }

  public String getEquipID() {
    return equipID;
  }

  public void setEquipID(String equipID) {
    this.equipID = equipID;
  }

  public String getEquipType() {
    return equipType;
  }

  public void setEquipType(String equipType) {
    this.equipType = equipType;
  }

  public String getEquipLocID() {
    return equipLocID;
  }

  public void setEquipLocID(String equipLocID) {
    this.equipLocID = equipLocID;
  }

  public String isClean() {
    return isClean;
  }

  public void setClean(String clean) {
    isClean = clean;
  }
}
