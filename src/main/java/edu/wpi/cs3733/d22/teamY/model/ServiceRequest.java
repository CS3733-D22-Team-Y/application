package edu.wpi.cs3733.d22.teamY.model;

import edu.wpi.cs3733.d22.teamY.RequestTypes;
import javax.persistence.*;

@Entity
@Table(name = "REQUESTS")
public class ServiceRequest implements StringArrayConv {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int id;

  private RequestTypes type;
  private String assignedNurse;
  private String locationID;
  private String additionalNotes;

  private String atr0;

  private String atr1;

  private String atr2;

  private String atr3;

  /**
   * Generic Request Constructor ID Generation Handled by Constructor and Database
   *
   * @param type Enum Request Type
   * @param assignedNurse Nurse/Employee id to be assigned.
   * @param locationID String nodeID of Request Location
   * @param additionalNotes Additional Notes Field
   * @param customAttributes a STRING ARRAY OF CUSTOM ATTRIBUTES !!! THE SAME SIZE AS THE NUMBER OF
   *     FIELDS THE REQUEST REQUIRES !!!
   *     <p>EXAMPLE ServiceRequest NAME = new ServiceRequest( RequestTypes.FLORAL, "none", roomID,
   *     additionalNotes, new String[] {bouquetTypeSelected}));
   */
  public ServiceRequest(
      RequestTypes type,
      String assignedNurse,
      String locationID,
      String additionalNotes,
      String[] customAttributes) {
    init(type, assignedNurse, locationID, additionalNotes, customAttributes);
  }

  private void init(
      RequestTypes type,
      String assignedNurse,
      String locationID,
      String additionalNotes,
      String[] customAttributes) {
    this.type = type;
    this.assignedNurse = assignedNurse;
    this.locationID = locationID;
    this.additionalNotes = additionalNotes;

    try {
      switch (type) {
        case FLORAL:
        case LAUNDRY:
        case LAB:
        case TRANSLATOR:
          atr0 = customAttributes[0];
          break;
        case MEAL:
          atr0 = customAttributes[0];
          atr1 = customAttributes[1];
          atr2 = customAttributes[2];
          atr3 = customAttributes[3];
          break;
        case MEDEQUIP:
          atr0 = customAttributes[0];
          break;
        case SECURITY:
          atr0 = customAttributes[0];
          atr1 = customAttributes[1];
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ServiceRequest() {}

  /**
   * Set's custom Attributes based on the Type of Request
   *
   * @param key String of the Key/Attribute to Update
   * @param value String of the Value
   */
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

  /**
   * Returns a String based on the current Request Type and the Passed in Key.
   *
   * @param key !!!
   * @return
   */
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

  public int getRequestId() {
    return this.id;
  }
  /**
   * Retrieve Request Type of Current Request
   *
   * @return ENUM REQUESTTYPES
   */
  public RequestTypes getType() {
    return type;
  }

  /**
   * Get Assigned Employee/Nurse
   *
   * @return String of Nurse/Employee Name/ID
   */
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

  @Override
  public String[] toStringArray() {
    return new String[] {
      String.valueOf(type), assignedNurse, locationID, additionalNotes, atr0, atr1, atr2, atr3
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    switch (args[0]) {
      case "FLORAL":
        init(RequestTypes.FLORAL, args[1], args[2], args[3], new String[] {args[4]});
        break;
      case "LAB":
        init(RequestTypes.LAB, args[1], args[2], args[3], new String[] {args[4]});
        break;
      case "LAUNDRY":
        init(RequestTypes.LAUNDRY, args[1], args[2], args[3], new String[] {args[4]});
        break;
      case "MEAL":
        init(
            RequestTypes.MEAL,
            args[1],
            args[2],
            args[3],
            new String[] {args[4], args[5], args[6], args[7]});
        break;
      case "MEDEQUIP":
        init(RequestTypes.MEDEQUIP, args[1], args[2], args[3], new String[] {args[4]});
        break;
      case "SECURITY":
        init(RequestTypes.SECURITY, args[1], args[2], args[3], new String[] {args[4], args[5]});
        break;
      case "TRANSLATOR":
        init(RequestTypes.TRANSLATOR, args[1], args[2], args[3], new String[] {args[4]});
        break;
    }
  }
}
