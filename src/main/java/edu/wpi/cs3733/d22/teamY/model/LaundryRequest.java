package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LAUNDRYREQUESTS")
public class LaundryRequest implements StringArrayConv {

  @Id private String requestNum;
  private String roomID;
  private String patientName;
  private String assignedNurse;
  private String requestStatus;
  private String additionalNotes;
  private String laundryTypeSelected;

  public LaundryRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String laundryTypeSelected) {
    this.requestNum = requestNum;
    this.roomID = roomID;
    this.patientName = patientName;
    this.assignedNurse = assignedNurse;
    this.requestStatus = requestStatus;
    this.additionalNotes = additionalNotes;
    this.laundryTypeSelected = laundryTypeSelected;
  }

  public LaundryRequest(
      String requestNum,
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String laundryTypeSelected) {
    init(
        requestNum,
        roomID,
        patientName,
        assignedNurse,
        requestStatus,
        additionalNotes,
        laundryTypeSelected);
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      requestNum,
      roomID,
      patientName,
      assignedNurse,
      requestStatus,
      additionalNotes,
      laundryTypeSelected
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
  }

  public String getRequestNum() {
    return requestNum;
  }

  public void setRequestNum(String requestNum) {
    this.requestNum = requestNum;
  }

  public String getRoomID() {
    return roomID;
  }

  public void setRoomID(String roomID) {
    this.roomID = roomID;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getAssignedNurse() {
    return assignedNurse;
  }

  public void setAssignedNurse(String assignedNurse) {
    this.assignedNurse = assignedNurse;
  }

  public String getRequestStatus() {
    return requestStatus;
  }

  public void setRequestStatus(String requestStatus) {
    this.requestStatus = requestStatus;
  }

  public String getAdditionalNotes() {
    return additionalNotes;
  }

  public void setAdditionalNotes(String additionalNotes) {
    this.additionalNotes = additionalNotes;
  }

  public String getLaundryTypeSelected() {
    return laundryTypeSelected;
  }

  public void setLaundryTypeSelected(String laundryTypeSelected) {
    this.laundryTypeSelected = laundryTypeSelected;
  }
}
