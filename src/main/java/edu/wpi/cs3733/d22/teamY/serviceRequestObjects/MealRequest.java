package edu.wpi.cs3733.d22.teamY.serviceRequestObjects;

public class MealRequest extends AbsServiceRequest {
  String mainOrder;
  String sideOrder;
  String dietaryRestrictions;
  String specialInstructions;

  public MealRequest(
      String roomID,
      String patientName,
      String assignedNurse,
      String requestStatus,
      String additionalNotes,
      String mainOrder,
      String sideOrder,
      String dietaryRestrictions,
      String specialInstructions) {
    super(roomID, patientName, assignedNurse, requestStatus, additionalNotes);
    this.mainOrder = mainOrder;
    this.sideOrder = sideOrder;
    this.dietaryRestrictions = dietaryRestrictions;
    this.specialInstructions = specialInstructions;
  }
}
