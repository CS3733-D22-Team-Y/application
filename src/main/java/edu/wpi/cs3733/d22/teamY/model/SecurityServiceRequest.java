package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITYSERVICEREQUESTS")
public class SecurityServiceRequest implements StringArrayConv {

  @Id private String requestNum;
  private String roomID;
  private String patientName;
  private String assignedNurse;
  private String requestStatus;
  private String additionalNotes;
  private String requestTypeSelected;
  private String requestPriority;

  public SecurityServiceRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {
    this.requestNum = requestNum;
    this.roomID = roomID;
    this.patientName = patientName;
    this.assignedNurse = assignedNurse;
    this.requestStatus = requestStatus;
    this.additionalNotes = additionalNotes;
    this.requestTypeSelected = requestTypeSelected;
    this.requestPriority = requestPriority;
  }

  public SecurityServiceRequest(
      String requestNum,
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {
    init(
        requestNum,
        roomID,
        patientName,
        assignedNurse,
        requestStatus,
        additionalNotes,
        requestTypeSelected,
        requestPriority);
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
      requestTypeSelected,
      requestPriority
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
  }
}
