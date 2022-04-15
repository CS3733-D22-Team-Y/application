package edu.wpi.cs3733.d22.teamY.model;

import static edu.wpi.cs3733.d22.teamY.RequestTypes.FLORAL;

import edu.wpi.cs3733.d22.teamY.RequestTypes;
import javax.persistence.*;

@Entity
@Table(name = "REQUESTS")
public class Request {
  @Id private String id;
  private RequestTypes type;
  private String assignedNurse;
  private String locationID;
  private String additionalNotes;

  private String atr0;

  private String atr1;

  private String atr2;

  private String atr3;

  public Request(
      String id,
      RequestTypes type,
      String assignedNurse,
      String locationID,
      String additionalNotes,
      String[] customAttributes) {
    this.id = id;
    this.type = type;
    this.assignedNurse = assignedNurse;
    this.locationID = locationID;
    this.additionalNotes = additionalNotes;

    try {
      switch (type) {
        case FLORAL:
          atr0 = customAttributes[0];
          break;
        case LAB:
          atr0 = customAttributes[0];
          break;
        case LAUNDRY:
          atr0 = customAttributes[0];
          break;
        case MEAL:
          atr0 = customAttributes[0];
          atr1 = customAttributes[1];
          atr2 = customAttributes[2];
          atr3 = customAttributes[4];
          break;
        case MEDEQUIP:
          atr0 = customAttributes[0];
          break;
        case SECURITY:
          atr0 = customAttributes[0];
          atr1 = customAttributes[1];
          break;
        case TRANSLATOR:
          atr0 = customAttributes[0];
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Request() {}

  public void set(String key, String value) {
    switch (this.type) {
      case FLORAL:
      case TRANSLATOR:
      case MEDEQUIP:
      case LAUNDRY:
      case LAB:
        atr0 = value;
        break;

      case MEAL:
        switch (key) {
          case "mainChoice":
            atr0 = value;
            break;
          case "sideChoice":
            atr1 = value;
            break;
          case "allergies":
            atr2 = value;
            break;
          case "specialInstructions":
            atr3 = value;
            break;
        }
        break;

      case SECURITY:
        switch (key) {
          case "requestTypeSelected":
            atr0 = value;
            break;
          case "securityRequestPriority":
            atr1 = value;
            break;
        }
        break;
    }
  }

  public String get(String key) {
    switch (this.type) {
      case FLORAL:
      case TRANSLATOR:
      case MEDEQUIP:
      case LAUNDRY:
      case LAB:
        return atr0;
      case MEAL:
        switch (key) {
          case "mainChoice":
            return atr0;
          case "sideChoice":
            return atr1;
          case "allergies":
            return atr2;
          case "specialInstructions":
            return atr3;
        }
        break;

      case SECURITY:
        switch (key) {
          case "requestTypeSelected":
            return atr0;
          case "securityRequestPriority":
            return atr1;
        }
        break;
    }
    return "Incorrect Key";
  }

  public RequestTypes getType() {
    return type;
  }

  public String getAssignedNurse() {
    return assignedNurse;
  }

  public void setAssignedNurse(String assignedNurse) {
    this.assignedNurse = assignedNurse;
  }

  public String getLocationID() {
    return locationID;
  }

  public void setLocationID(String locationID) {
    this.locationID = locationID;
  }

  public String getAdditionalNotes() {
    return additionalNotes;
  }

  public void setAdditionalNotes(String additionalNotes) {
    this.additionalNotes = additionalNotes;
  }
}
