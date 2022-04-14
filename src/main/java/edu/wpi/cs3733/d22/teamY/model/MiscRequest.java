package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MISCREQUESTS")
public class MiscRequest extends Requestable implements StringArrayConv {
  private String requestName;

  public MiscRequest() {}

  public MiscRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String requestName) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, requestName);
  }

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String requestName) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.requestName = requestName;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      this.requestName
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
    return "Request: " + requestName;
  }

  public String getRequestName() {
    return requestName;
  }

  public void setRequestName(String requestName) {
    this.requestName = requestName;
  }

  @Override
  public String getTypeString() {
    return requestName + " (Misc)";
  }
}
