package edu.wpi.cs3733.d22.teamY;

public enum RequestTypes {
  FLORAL(1, new String[] {"bouquetTypeSelected"}, new String[] {"Type"}, "Bouquet Delivery"),
  LAB(1, new String[] {"resultType"}, new String[] {"Type"}, "Lab Work"),
  LAUNDRY(1, new String[] {"laundryTypeSelected"}, new String[] {"Type"}, "Laundry Services"),
  MEAL(
      4,
      new String[] {"mainChoice", "sideChoice", "allergies", "specialInstructions"},
      new String[] {"Main", "Side", "Allergies", "Special Inst."},
      "Meal Delivery"),
  MEDEQUIP(1, new String[] {"equipmentTypeSelected"}, new String[] {"Type"}, "Equipment Transit"),
  SECURITY(
      2,
      new String[] {"requestTypeSelected", "securityRequestPriority"},
      new String[] {"Type", "Priority"},
      "Security Alert"),
  SPECIALIST(1, new String[] {"specialistType"}, new String[] {"Type"}, "Specialist"),
  TRANSLATOR(1, new String[] {"requestTypeSelected"}, new String[] {"Type"}, "Medical Translator"),
  FACILITIES(1, new String[] {"type"}, new String[] {"Type"}, "Facilities Request"),
  MISC(1, new String[] {"requestName"}, new String[] {"Name"}, "Uncategorized Task"),
  MAINTENANCE(
      2,
      new String[] {"requestTypeSelected", "maintenanceRequestPriority"},
      new String[] {"Type", "Priority"},
      "Maintenance Request");

  private final int attribues;
  private final String[] attributeNames;
  private final String[] friendlyAttributeNames;
  private final String friendlyName;

  RequestTypes(
      int attribues,
      String[] attributeNames,
      String[] friendlyAttributeNames,
      String friendlyName) {
    this.attribues = attribues;
    this.attributeNames = attributeNames;
    this.friendlyName = friendlyName;
    this.friendlyAttributeNames = friendlyAttributeNames;
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

  public String[] getFriendlyAttributes() {
    return this.friendlyAttributeNames;
  }
}
