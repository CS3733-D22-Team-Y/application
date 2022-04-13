package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITYSERVICEREQUESTS")
public class SecurityServiceRequest extends Requestable implements StringArrayConv {
  private String requestTypeSelected;
  private String requestPriority;

  public SecurityServiceRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
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
      RequestStatus requestStatus,
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
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      requestTypeSelected,
      requestPriority
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
        args[5],
        args[6]);
  }

  @Override
  public int getRequestPriority() {
    return 5;
  }

  public String getRequestTypeSelected() {
    return requestTypeSelected;
  }

  public void setRequestTypeSelected(String requestTypeSelected) {
    this.requestTypeSelected = requestTypeSelected;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }
}
