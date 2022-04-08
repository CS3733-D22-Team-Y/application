package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Entity class for elements in the floral request table in the database. An instance of this class
* represents a single row in the database. Instances can be used along with the DAO to add, update,
* and delete rows in the table.
*/
@Entity
@Table(name = "FLORALREQUESTS")
public class FloralRequest implements StringArrayConv {

	@Id private String requestNum;
	private String roomID;
	private String patientName;
	private String requestStatus;
	private String assignedNurse;

	// GetwellSoon, newBaby, bouquet otd
	private String bouquetTypeSelected;
	// Additional Notes
	private String additionalNotes;

	private void init(
			String requestNum,
			String roomID,
			String patientName,
			String assignedNurse,
			String requestStatus,
			String additionalNotes,
			String bouquetTypeSelected) {
		this.requestNum = requestNum;
		this.roomID = roomID;
		this.patientName = patientName;
		this.assignedNurse = assignedNurse;
		this.requestStatus = requestStatus;
		this.additionalNotes = additionalNotes;
		this.bouquetTypeSelected = bouquetTypeSelected;
	}

	public FloralRequest() {}

	public FloralRequest(
			String requestNum,
			String roomID,
			String patientName,
			String assignedNurse,
			String requestStatus,
			String additionalNotes,
			String bouquetTypeSelected) {
		init(
				requestNum,
				roomID,
				patientName,
				assignedNurse,
				requestStatus,
				additionalNotes,
				bouquetTypeSelected);
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
			bouquetTypeSelected
		};
	}

	@Override
	public void fromStringArray(String[] args) {
		init(args[0], args[1], args[2], args[3], args[4], args[5], args[7]);
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

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAssignedNurse() {
		return assignedNurse;
	}

	public void setAssignedNurse(String assignedNurse) {
		this.assignedNurse = assignedNurse;
	}

	public String getBouquetTypeSelected() {
		return bouquetTypeSelected;
	}

	public void setBouquetTypeSelected(String bouquetTypeSelected) {
		this.bouquetTypeSelected = bouquetTypeSelected;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
}
