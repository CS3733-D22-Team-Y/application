package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MEALREQUESTS")
public class MealRequest extends Requestable implements StringArrayConv {

  private String mainChoice;
  private String sideChoice;
  private String allergies;
  private String specialInstructions;

  public MealRequest() {}

  private void init(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String mainChoice,
      String sideChoice,
      String allergies,
      String specialInstructions) {
    initParent(requestNum, roomID, assignedNurse, additionalNotes, requestStatus);
    this.mainChoice = mainChoice;
    this.sideChoice = sideChoice;
    this.allergies = allergies;
    this.specialInstructions = specialInstructions;
  }

  public MealRequest(
      String requestNum,
      String roomID,
      String assignedNurse,
      RequestStatus requestStatus,
      String additionalNotes,
      String mainChoice,
      String sideChoice,
      String allergies,
      String specialInstructions) {
    init(
        requestNum,
        roomID,
        assignedNurse,
        requestStatus,
        additionalNotes,
        mainChoice,
        sideChoice,
        allergies,
        specialInstructions);
  }

  @Override
  public String[] toStringArray() {
    return new String[] {
      getRequestNum(),
      getRoomID(),
      getAssignedNurse(),
      Integer.toString(getRequestStatus().ordinal()),
      getAdditionalNotes(),
      mainChoice,
      sideChoice,
      allergies,
      specialInstructions
    };
  }

  @Override
  public void fromStringArray(String[] args) {
    init(
        args[0],
        args[1],
        args[2],
        RequestStatus.values()[Integer.parseInt(args[3])],
        args[4],
        args[5],
        args[6],
        args[7],
        args[8]);
  }

  @Override
  public int getRequestPriority() {
    return 5;
  }

  @Override
  public String getSpecificText() {
    StringBuilder sb = new StringBuilder();
    sb.append("Entree: ").append(mainChoice);
    sb.append("Side: ").append(sideChoice);
    sb.append("Dietary Restriction: ").append(allergies);
    sb.append("Special Instructions: ").append(specialInstructions);

    return sb.toString();
  }

  public String getMainChoice() {
    return mainChoice;
  }

  public void setMainChoice(String mainChoice) {
    this.mainChoice = mainChoice;
  }

  public String getSideChoice() {
    return sideChoice;
  }

  public void setSideChoice(String sideChoice) {
    this.sideChoice = sideChoice;
  }

  public String getAllergies() {
    return allergies;
  }

  public void setAllergies(String allergies) {
    this.allergies = allergies;
  }

  public String getSpecialInstructions() {
    return specialInstructions;
  }

  public void setSpecialInstructions(String specialInstructions) {
    this.specialInstructions = specialInstructions;
  }

  @Override
  public String getLocID() {
    return getRoomID();
  }
}
