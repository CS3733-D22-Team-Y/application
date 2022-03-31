package edu.wpi.cs3733.d22.teamY.serviceRequestObjects;

public abstract class AbsServiceRequest {
  // Fields that are in every service request
  String roomID;
  String patientName;
  String assignedNurse;
  String requestStatus;

  String additionalNotes;

  public AbsServiceRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes) {
    this.roomID = roomID;
    this.patientName = patientName;
    this.assignedNurse = assignedNurse;
    this.requestStatus = requestStatus;
    this.additionalNotes = additionalNotes;
  }
}
