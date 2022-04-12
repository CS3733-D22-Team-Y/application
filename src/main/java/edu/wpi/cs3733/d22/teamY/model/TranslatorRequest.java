package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSLATORREQUESTS")
public class TranslatorRequest implements StringArrayConv {
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
      String additonalNotes,
      String languageTypeSelected) {
    this.requestNum = requestNum;
    this.roomID = roomID;
    this.assignedNurse = assignedNurse;
    this.requestStatus = requestStatus;
    this.additonalNotes = additonalNotes;
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
}
