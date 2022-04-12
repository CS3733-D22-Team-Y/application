package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MISCREQUESTS")
public class MiscRequest implements StringArrayConv, Requestable {
  @Id private String requestNum;
  private String roomID;
  private String assignedNurse;
  private String requestStatus;
  private String additionalNotes;
  private String requestName;

  public MiscRequest() {}

  public MiscRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestName) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, requestName);
  }

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestName) {
    this.requestNum = requestNum;
    this.roomID = roomID;
    this.assignedNurse = assignedNurse;
    this.requestStatus = requestStatus;
    this.additionalNotes = additionalNotes;
    this.requestName = requestName;
  }

  @Override
  public String getLocID() {
    return this.roomID;
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      this.requestNum,
      this.roomID,
      this.assignedNurse,
      this.requestStatus,
      this.additionalNotes,
      this.requestName
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5]);
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

  public String getRequestName() {
    return requestName;
  }

  public void setRequestName(String requestName) {
    this.requestName = requestName;
  }
}
