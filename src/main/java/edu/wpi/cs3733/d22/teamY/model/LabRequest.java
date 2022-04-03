package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LabRequests")
public class LabRequest implements StringArrayConv {

  @Id private String requestNum;
  private String roomID;
  private String patientName;
  private String requestStatus;
  private String assignedNurse;
  private String additionalNotes;
  private String resultType;

  private void init(
      String requestNum,
      String roomID,
      String patientName,
      String requestStatus,
      String assignedNurse,
      String additionalNotes,
      String resultType) {
    this.requestNum = requestNum;
    this.roomID = roomID;
    this.patientName = patientName;
    this.requestStatus = requestStatus;
    this.assignedNurse = assignedNurse;
    this.additionalNotes = additionalNotes;
    this.resultType = resultType;
  }

  public LabRequest() {}

  public LabRequest(
      String requestNum,
      String roomID,
      String patientName,
      String requestStatus,
      String assignedNurse,
      String additionalNotes,
      String resultType) {
    init(
        requestNum, roomID, patientName, requestStatus, assignedNurse, additionalNotes, resultType);
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      requestNum, roomID, patientName, requestStatus, assignedNurse, additionalNotes, resultType
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

  public String getRequestStatus() {
    return requestStatus;
  }

  public void setRequestStatus(String requestStatus) {
    this.requestStatus = requestStatus;
  }

  public String getAssignedNurse() {
    return assignedNurse;
  }

  public void setAssignedNurse(String assignedNurse) {
    this.assignedNurse = assignedNurse;
  }

  public String getAdditionalNotes() {
    return additionalNotes;
  }

  public void setAdditionalNotes(String additionalNotes) {
    this.additionalNotes = additionalNotes;
  }

  public String getResultType() {
    return resultType;
  }

  public void setResultTypeSelected(String resultType) {
    this.resultType = resultType;
  }
}
