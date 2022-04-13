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
@Table(name = "MEDEQUIPREQUEST")
public class MedEquipReq extends Requestable implements StringArrayConv {
  @Id private String requestNum;
  private String roomID;
  private String assignedNurse;
  private String requestStatus;
  private String additionalNotes;
  private String equipmentTypeSelected;

  public static final String REQUEST_NUM = "REQUESTNUM";
  public static final String EQUIP_ID = "EQUIPID";
  public static final String TARGET_LOC_ID = "TARGETLOCID";

  public void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String equipmentTypeSelected) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus, 5);
    this.equipmentTypeSelected = equipmentTypeSelected;
  }

  public MedEquipReq() {}

  public MedEquipReq(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String equipmentTypeSelected) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, equipmentTypeSelected);
  }

  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5]);
  }

  public String[] toStringArray() {
    return new String[] {
      this.requestNum,
      this.roomID,
      this.assignedNurse,
      this.requestStatus,
      this.additionalNotes,
      this.equipmentTypeSelected
    };
  }

  public String getEquipmentTypeSelected() {
    return equipmentTypeSelected;
  }

  public void setEquipmentTypeSelected(String equipmentTypeSelected) {
    this.equipmentTypeSelected = equipmentTypeSelected;
  }

  @Override
  public String getLocID() {
    return this.roomID;
  }

  // endregion
}
