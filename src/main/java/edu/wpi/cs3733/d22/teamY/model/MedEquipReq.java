package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for elements in the equipment request table in the database. An instance of this
 * class represents a single row in the database. Instances can be used along with the DBManager to
 * add, update, and delete rows in the table.
 */
@Entity
@Table(name = "MEDEQUIPREQUEST")
public class MedEquipReq extends Requestable implements StringArrayConv {
  private String equipmentTypeSelected;

  public static final String REQUEST_NUM = "REQUESTNUM";
  public static final String EQUIP_ID = "EQUIPID";
  public static final String TARGET_LOC_ID = "TARGETLOCID";

  public void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String equipmentTypeSelected) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.equipmentTypeSelected = equipmentTypeSelected;
  }

  public MedEquipReq() {}

  public MedEquipReq(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String equipmentTypeSelected) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, equipmentTypeSelected);
  }

  public void fromStringArray(String[] args) {
    init(
        args[0],
        args[1],
        args[2],
        RequestStatus.values()[Integer.parseInt(args[3])],
        args[4],
        args[5]);
  }

  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      this.equipmentTypeSelected
    };
  }

  @Override
  public int getRequestPriority() {
    return 5;
  }

  @Override
  public String getSpecificText() {
    return "Equipment Type: " + equipmentTypeSelected;
  }

  public String getEquipmentTypeSelected() {
    return equipmentTypeSelected;
  }

  public void setEquipmentTypeSelected(String equipmentTypeSelected) {
    this.equipmentTypeSelected = equipmentTypeSelected;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }

  // endregion
}
