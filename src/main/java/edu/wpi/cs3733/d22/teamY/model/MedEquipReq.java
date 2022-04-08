package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Entity class for elements in the equipment request table in the database. An instance of this
* class represents a single row in the database. Instances can be used along with the DBManager to
* add, update, and delete rows in the table.
*/
@Entity
@Table(name = "MEDEQUIPREQUEST")
public class MedEquipReq implements StringArrayConv {
	@Id private String requestNum;
	private String roomID;
	private String patientName;
	private String assignedNurse;
	private String requestStatus;
	private String additionalNotes;
	private String equipmentTypeSelected;

	public static final String REQUEST_NUM = "REQUESTNUM";
	public static final String EQUIP_ID = "EQUIPID";
	public static final String TARGET_LOC_ID = "TARGETLOCID";

	public void init(
			String requestNum,
			String roomID,
			String patientName,
			String assignedNurse,
			String requestStatus,
			String additionalNotes,
			String equipmentTypeSelected) {
		this.requestNum = requestNum;
		this.roomID = roomID;
		this.patientName = patientName;
		this.assignedNurse = assignedNurse;
		this.requestStatus = requestStatus;
		this.additionalNotes = additionalNotes;
		this.equipmentTypeSelected = equipmentTypeSelected;
	}

	public MedEquipReq() {}

	public MedEquipReq(
			String requestNum,
			String roomID,
			String patientName,
			String assignedNurse,
			String requestStatus,
			String additionalNotes,
			String equipmentTypeSelected) {
		init(
				requestNum,
				roomID,
				patientName,
				assignedNurse,
				requestStatus,
				additionalNotes,
				equipmentTypeSelected);
	}

	public void fromStringArray(String[] args) {
		init(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
	}

	public String[] toStringArray() {
		return new String[] {
			this.requestNum,
			this.roomID,
			this.patientName,
			this.assignedNurse,
			this.requestStatus,
			this.additionalNotes,
			this.equipmentTypeSelected
		};
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

	public String getEquipmentTypeSelected() {
		return equipmentTypeSelected;
	}

	public void setEquipmentTypeSelected(String equipmentTypeSelected) {
		this.equipmentTypeSelected = equipmentTypeSelected;
	}

	// endregion
}
