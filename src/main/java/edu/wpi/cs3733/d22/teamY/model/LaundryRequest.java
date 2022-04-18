package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LAUNDRYREQUESTS")
public class LaundryRequest extends Requestable implements StringArrayConv {
  private String laundryTypeSelected;

  public LaundryRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String laundryTypeSelected) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.laundryTypeSelected = laundryTypeSelected;
  }

  public LaundryRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String laundryTypeSelected) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, laundryTypeSelected);
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      laundryTypeSelected
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
    return 2;
  }

  @Override
  public String getSpecificText() {
    return "Laundry Type: " + laundryTypeSelected;
  }

  public String getLaundryTypeSelected() {
    return laundryTypeSelected;
  }

  public void setLaundryTypeSelected(String laundryTypeSelected) {
    this.laundryTypeSelected = laundryTypeSelected;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }

  @Override
  public String getTypeString() {
    return "Laundry Services";
  }
}
