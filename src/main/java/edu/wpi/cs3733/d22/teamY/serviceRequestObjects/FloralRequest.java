package edu.wpi.cs3733.d22.teamY.serviceRequestObjects;

public class FloralRequest extends AbsServiceRequest {
  String bouquetType;

  public FloralRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String bouquetType) {
    super(roomID, patientName, assignedNurse, requestStatus, additionalNotes);
    this.bouquetType = bouquetType;
  }
}
