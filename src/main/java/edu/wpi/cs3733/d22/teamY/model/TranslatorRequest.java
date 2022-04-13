package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSLATORREQUESTS")
public class TranslatorRequest extends Requestable implements StringArrayConv {
  private String languageTypeSelected;

  public TranslatorRequest() {}

  public TranslatorRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additonalNotes,
      String languageTypeSelected) {
    init(requestNum, roomID, assignedNurse, requestStatus, additonalNotes, languageTypeSelected);
  }

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String languageTypeSelected) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.languageTypeSelected = languageTypeSelected;
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      this.languageTypeSelected
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(
        args[0],
        args[1],
        args[2],
        RequestStatus.values()[Integer.parseInt(args[3])],
        args[4],
        args[5]);
  }

  @Override
  public int getRequestPriority() {
    return 5;
  }

  @Override
  public String getSpecificText() {
    return "Language: " + languageTypeSelected;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }

  public String getLanguageTypeSelected() {
    return languageTypeSelected;
  }

  public void setLanguageTypeSelected(String languageTypeSelected) {
    this.languageTypeSelected = languageTypeSelected;
  }
}
