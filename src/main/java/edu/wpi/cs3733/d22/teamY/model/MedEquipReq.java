package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for elements in the equipment request table in the database. An instance of this
 * class represents a single row in the database. Instances can be used along with the DBManager to
 * add, update, and delete rows in the table.
 */
@Entity
@Table(name = "medequiprequest")
public class MedEquipReq implements StringArrayConv {
  @Id private String requestNum;
  private String equipID;
  private String targetLocID;

  /*public final String REQUEST_NUM = "REQUESTNUM";
  public final String EQUIP_ID = "EQUIPID";
  public final String TARGET_LOC_ID = "TARGETLOCID";*/

  public void init(String requestNum, String equipID, String targetLocID) {
    this.requestNum = requestNum;
    this.equipID = equipID;
    this.targetLocID = targetLocID;
  }

  public MedEquipReq() {}

  public MedEquipReq(String requestNum, String equipID, String targetLocID) {
    init(requestNum, equipID, targetLocID);
  }

  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2]);
  }

  public String[] toStringArray() {
    return new String[] {requestNum, equipID, targetLocID};
  }

  // region Getters/Setters
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
  // endregion
}
