package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for elements in the lab request table in the database. An instance of this class
 * represents a single row in the database. Instances can be used along with the DAO to add, update,
 * and delete rows in the table.
 */
@Entity
@Table(name = "LABREQUESTS")
public class LabRequest extends Requestable implements StringArrayConv {
  @Id private String requestNum;
  private String roomID;
  private String requestStatus;
  private String assignedNurse;
  private String additionalNotes;
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
      String requestStatus,
      String assignedNurse,
      String additionalNotes,
      String resultType) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus, 5);
    this.resultType = resultType;
  }

  public LabRequest() {}

  public LabRequest(
      String requestNum,
      String roomID,
      String requestStatus,
      String assignedNurse,
      String additionalNotes,
      String resultType) {
    init(requestNum, roomID, requestStatus, assignedNurse, additionalNotes, resultType);
  }

  public String[] toStringArray() {
    return new String[] {
      requestNum, roomID, requestStatus, assignedNurse, additionalNotes, resultType
    };
  }

  public void fromStringArray(String[] args) {
    init(args[0], args[1], args[2], args[3], args[4], args[5]);
  }

  public String getResultType() {
    return resultType;
  }

  public void setResultTypeSelected(String resultType) {
    this.resultType = resultType;
  }

  @Override
  public String getLocID() {
    return roomID;
  }
}
