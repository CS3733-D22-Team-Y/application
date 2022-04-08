package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEALREQUESTS")
public class MealRequest implements StringArrayConv {

	@Id private String requestNum;
	private String roomID;
	private String patientName;
	private String assignedNurse;
	private String requestStatus;
	private String additionalNotes;
	private String mainChoice;
	private String sideChoice;
	private String allergies;
	private String specialInstructions;

	public MealRequest() {}

	private void init(
			String requestNum,
			String roomID,
			String patientName,
			String assignedNurse,
			String requestStatus,
			String additionalNotes,
			String mainChoice,
			String sideChoice,
			String allergies,
			String specialInstructions) {
		this.requestNum = requestNum;
		this.roomID = roomID;
		this.patientName = patientName;
		this.assignedNurse = assignedNurse;
		this.requestStatus = requestStatus;
		this.additionalNotes = additionalNotes;
		this.mainChoice = mainChoice;
		this.sideChoice = sideChoice;
		this.allergies = allergies;
		this.specialInstructions = specialInstructions;
	}

	public MealRequest(
			String requestNum,
			String roomID,
			String patientName,
			String assignedNurse,
			String requestStatus,
			String additionalNotes,
			String mainChoice,
			String sideChoice,
			String allergies,
			String specialInstructions) {
		init(
				requestNum,
				roomID,
				patientName,
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
			requestNum,
			roomID,
			patientName,
			assignedNurse,
			requestStatus,
			additionalNotes,
			mainChoice,
			sideChoice,
			allergies,
			specialInstructions
		};
	}

	@Override
	public void fromStringArray(String[] args) {
		init(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
	}

	public String getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(String requestNum) {
		this.requestNum = requestNum;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAssignedNurse() {
		return assignedNurse;
	}

	public void setAssignedNurse(String assignedNurse) {
		this.assignedNurse = assignedNurse;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
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
}
