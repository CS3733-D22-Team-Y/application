package edu.wpi.cs3733.d22.teamY;

public enum RequestTypes {
  FLORAL(1, new String[] {"bouquetTypeSelected"}),
  LAB(1, new String[] {"resultType"}),
  LAUNDRY(1, new String[] {"laundryTypeSelected"}),
  MEAL(4, new String[] {"mainChoice", "sideChoice", "allergies", "specialInstructions"}),
  MEDEQUIP(1, new String[] {"equipmentTypeSelected"}),
  SECURITY(2, new String[] {"requestTypeSelected", "securityRequestPriority"}),
  MAINTENANCE(2, new String[] {"requestTypeSelected", "maintenanceRequestPriority"}),

  SPECIALIST(1, new String[] {"specialistType"}),

  TRANSLATOR(1, new String[] {"requestTypeSelected"}),

  FACILITIES(1, new String[] {"type"}),

  MISC(1, new String[] {"requestName"});

  private final int attribues;
  private final String[] attributeNames;

  RequestTypes(int attribues, String[] attributeNames) {
    this.attribues = attribues;
    this.attributeNames = attributeNames;
  }

  public int getAttributeCount() {
    return this.attribues;
  }

  public String[] getAttributes() {
    return this.attributeNames;
  }
}
