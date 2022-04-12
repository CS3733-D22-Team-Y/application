package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.*;

public enum RequestType {
  FLORAL(FloralRequest.class),
  LAB(LabRequest.class),
  LAUNDRY(LaundryRequest.class),
  MEAL(MealRequest.class),
  MED_EQUIP(MedEquipReq.class),
  SECURITY(SecurityServiceRequest.class),
  TRANSLATOR(TranslatorRequest.class),
  MISC(MiscRequest.class);

  public Class<? extends Requestable> requestClass;

  RequestType(Class<? extends Requestable> requestClass) {
    this.requestClass = requestClass;
  }
}
