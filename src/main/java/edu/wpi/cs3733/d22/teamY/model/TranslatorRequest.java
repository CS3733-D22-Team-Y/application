package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSLATORREQUESTS")
public class TranslatorRequest extends Requestable implements StringArrayConv {
  @Id private String requestNum;
  private String roomID;
  private String assignedNurse;
  private String requestStatus;
  private String additonalNotes;
  private String languageTypeSelected;

  public TranslatorRequest() {}

  public TranslatorRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additonalNotes,
      String languageTypeSelected) {
    init(requestNum, roomID, assignedNurse, requestStatus, additonalNotes, languageTypeSelected);
  }

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String languageTypeSelected) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus, 5);
    this.languageTypeSelected = languageTypeSelected;
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      this.requestNum,
      this.roomID,
      this.assignedNurse,
      this.requestStatus,
      this.additonalNotes,
      this.languageTypeSelected
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5]);
  }

  @Override
  public String getLocID() {
    return this.roomID;
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

  public String getAdditonalNotes() {
    return additonalNotes;
  }

  public void setAdditonalNotes(String additonalNotes) {
    this.additonalNotes = additonalNotes;
  }

  public String getLanguageTypeSelected() {
    return languageTypeSelected;
  }

  public void setLanguageTypeSelected(String languageTypeSelected) {
    this.languageTypeSelected = languageTypeSelected;
  }
}
