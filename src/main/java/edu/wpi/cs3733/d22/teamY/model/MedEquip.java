package edu.wpi.cs3733.d22.teamY.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class MedEquip implements StringArrayConv {
  @Entity
  @Table(name = "medequip")
  @Id private String equipID;
  private String equipType;
  private String equipLocId;
  private String isClean;

  public MedEquip() {}

  public MedEquip(String equipID, String equipType, String equipLocId, String isClean) {
    this.equipID = equipID;
    this.equipType = equipType;
    this.equipLocId = equipLocId;
    this.isClean = isClean;
  }

  public MedEquip(List<String> csv) {
    this(csv.get(0), csv.get(1), csv.get(2), csv.get(3));
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
