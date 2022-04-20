package edu.wpi.cs3733.d22.teamY;

public enum RequestTypes {
  FLORAL(1, new String[] {"bouquetTypeSelected"}, "Bouquet Delivery"),
  LAB(1, new String[] {"resultType"}, "Lab Work"),
  LAUNDRY(1, new String[] {"laundryTypeSelected"}, "Laundry Services"),
  MEAL(
      4,
      new String[] {"mainChoice", "sideChoice", "allergies", "specialInstructions"},
      "Meal Delivery"),
  MEDEQUIP(1, new String[] {"equipmentTypeSelected"}, "Equipment Transit"),
  SECURITY(2, new String[] {"requestTypeSelected", "securityRequestPriority"}, "Security Alert"),

  SPECIALIST(1, new String[] {"specialistType"}, "Specialist"),
  TRANSLATOR(1, new String[] {"requestTypeSelected"}, "Medical Translator");

  MISC(1, new String[] {"requestName"}, "Task (Uncategorized)"),

  private final int attribues;
  private final String[] attributeNames;
  private final String friendlyName;

  RequestTypes(int attribues, String[] attributeNames, String friendlyName) {
    this.attribues = attribues;
    this.attributeNames = attributeNames;
    this.friendlyName = friendlyName;
  }

  public int getAttributeCount() {
    return this.attribues;
  }

  public String[] getAttributes() {
    return this.attributeNames;
  }

  public String getFriendlyName() {
    return friendlyName;
  }
}
