package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for elements in the floral request table in the database. An instance of this class
 * represents a single row in the database. Instances can be used along with the DAO to add, update,
 * and delete rows in the table.
 */
@Entity
@Table(name = "FLORALREQUESTS")
public class FloralRequest extends Requestable implements StringArrayConv {

  // GetwellSoon, newBaby, bouquet otd
  private String bouquetTypeSelected;

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String bouquetTypeSelected) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.bouquetTypeSelected = bouquetTypeSelected;
  }

  public FloralRequest() {}

  public FloralRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String bouquetTypeSelected) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, bouquetTypeSelected);
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      bouquetTypeSelected
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
    return 1;
  }

  @Override
  public String getSpecificText() {
    return "Bouquet Type: " + bouquetTypeSelected;
  }

  public String getBouquetTypeSelected() {
    return bouquetTypeSelected;
  }

  public void setBouquetTypeSelected(String bouquetTypeSelected) {
    this.bouquetTypeSelected = bouquetTypeSelected;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }
}
