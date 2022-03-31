package edu.wpi.cs3733.d22.teamY.serviceRequestObjects;

public class LaundryRequest extends AbsServiceRequest {
  String cleanupType;

  public LaundryRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String cleanupType) {
    super(roomID, patientName, assignedNurse, requestStatus, additionalNotes);
    this.cleanupType = cleanupType;
  }
}
