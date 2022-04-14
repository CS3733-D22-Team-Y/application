package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for elements in the lab request table in the database. An instance of this class
 * represents a single row in the database. Instances can be used along with the DAO to add, update,
 * and delete rows in the table.
 */
@Entity
@Table(name = "LABREQUESTS")
public class LabRequest extends Requestable implements StringArrayConv {
  private String resultType;

  public static final String REQUEST_NUM = "REQUESTNUM";
  public static final String ADDITIONAL_NOTES = "ADDITIONALNOTES";
  public static final String ASSIGNED_NURSE = "ASSIGNEDNURSE";
  public static final String PATIENT_NAME = "PATIENTNAME";
  public static final String REQUEST_STATUS = "REQUEST_STATUS";
  public static final String RESULT_TYPE = "RESULTTYPE";
  public static final String ROOM_ID = "ROOMID";

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String resultType) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.resultType = resultType;
  }

  public LabRequest() {}

  public LabRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String resultType) {
    init(requestNum, roomID, assignedNurse, requestStatus, additionalNotes, resultType);
  }

  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      resultType
    };
  }

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
    return 6;
  }

  @Override
  public String getSpecificText() {
    return "Result Type: " + resultType;
  }

  public String getResultType() {
    return resultType;
  }

  public void setResultTypeSelected(String resultType) {
    this.resultType = resultType;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }

  @Override
  public String getTypeString() {
    return "Lab Work";
  }
}
