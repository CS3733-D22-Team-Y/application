package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITYSERVICEREQUESTS")
public class SecurityServiceRequest extends Requestable implements StringArrayConv {
  private String requestTypeSelected;
  private String securityRequestPriority;

  public SecurityServiceRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String requestPriority) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.requestTypeSelected = requestTypeSelected;
    this.securityRequestPriority = requestPriority;
  }

  public SecurityServiceRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String requestTypeSelected,
      String securityRequestPriority) {
    init(
        requestNum,
        roomID,
        assignedNurse,
        requestStatus,
        additionalNotes,
        requestTypeSelected,
        securityRequestPriority);
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
      securityRequestPriority
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

  public int getRequestPriority() {
    switch (securityRequestPriority) {
      case "mostUrgent":
        return 10;
      case "urgent":
        return 9;
      case "lowPriority":
        return 8;
      default:
        break;
    }

    return 10;
  }

  @Override
  public String getSpecificText() {
    return "Type: " + requestTypeSelected;
  }

  public String getRequestTypeSelected() {
    return requestTypeSelected;
  }

  public void setRequestTypeSelected(String requestTypeSelected) {
    this.requestTypeSelected = requestTypeSelected;
  }

  public String getSecurityRequestPriority() {
    return securityRequestPriority;
  }

  public void setSecurityRequestPriority(String priority) {
    this.securityRequestPriority = priority;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }

  @Override
  public String getTypeString() {
    return "Security Alert";
  }
}
