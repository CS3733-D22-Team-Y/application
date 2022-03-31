package edu.wpi.cs3733.d22.teamY.serviceRequestObjects;

public class SecurityRequest extends AbsServiceRequest {
  String reportType;
  String priority;

  public SecurityRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String reportType,
      String priority) {
    super(roomID, patientName, assignedNurse, requestStatus, additionalNotes);
    this.reportType = reportType;
    this.priority = priority;
  }
}
