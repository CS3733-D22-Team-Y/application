package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITYSERVICEREQUESTS")
public class SecurityServiceRequest extends Requestable implements StringArrayConv {

  @Id private String requestNum;
  private String roomID;
  private String assignedNurse;
  private String requestStatus;
  private String additionalNotes;
  private String requestTypeSelected;
  private String requestPriority;

  public SecurityServiceRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus, 5);
    this.requestTypeSelected = requestTypeSelected;
    this.requestPriority = requestPriority;
  }

  public SecurityServiceRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {
    init(
        requestNum,
        roomID,
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
      assignedNurse,
      requestStatus,
      additionalNotes,
      requestTypeSelected,
      requestPriority
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
  }

  public String getRequestTypeSelected() {
    return requestTypeSelected;
  }

  public void setRequestTypeSelected(String requestTypeSelected) {
    this.requestTypeSelected = requestTypeSelected;
  }

  public String getRequestPriority() {
    return requestPriority;
  }

  public void setRequestPriority(String requestPriority) {
    this.requestPriority = requestPriority;
  }

  @Override
  public String getLocID() {
    return this.roomID;
  }
}
