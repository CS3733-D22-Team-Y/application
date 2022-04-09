package edu.wpi.cs3733.d22.teamY.serviceRequestObjects;

public class MedicalEquipmentRequest extends AbsServiceRequest {
  String equipmentType;

  public MedicalEquipmentRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String equipmentTypeType) {
    super(roomID, patientName, assignedNurse, requestStatus, additionalNotes);
    this.equipmentType = equipmentTypeType;
  }
}
