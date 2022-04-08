package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.*;

public enum EntryType {

	// Place all new entry types here.
	// In order: specify number of columns, CSV import path, CSV export path,
	// and actual entry class.

	// Remember! For something to qualify as an entry, it must implement StringArrayConv
	// as this ensures that it's a type that can be properly formatted for CSV.

	LOCATION(8, "TowerLocations", "locations_export", Location.class),

	MED_EQUIP(4, "MedEquip", "medEquipment_export", MedEquip.class),

	MED_EQUIP_REQ(6, "MedEquipReq", "medEquipRequests_export", MedEquipReq.class),

	LAB_REQUEST(6, "LabRequests", "labRequests_export", LabRequest.class),

	EMPLOYEE(9, "Employees", "employees_export", Employee.class),

	FLORAL_REQUEST(6, "FloralRequests", "floralRequests_export", FloralRequest.class),

	LAUNDRY_REQUEST(6, "LaundryRequests", "laundryRequests_export", LaundryRequest.class),

	MEAL_REQUEST(9, "MealRequests", "mealRequests_export", MealRequest.class),

	SECURITY_SERVICE_REQUEST(
			7, "SecurityServiceRequests", "securityServiceRequests_export", SecurityServiceRequest.class),

// region Enum Data / Methods
/* Enum specification begins below... */ ;

	private final int columns;
	private final String csvInLocation;
	private final String csvOutLocation;
	private final Class<? extends StringArrayConv> type;

	<T extends StringArrayConv> EntryType(int cols, String inPath, String outPath, Class<T> cls) {
		columns = cols;
		csvInLocation = inPath;
		csvOutLocation = outPath;
		type = cls;
	}

	// Returns number of columns in this type.
	public int getColumns() {
		return columns;
	}

	// Returns CSV input path for this type.
	public String getCsvInputLocation() {
		return csvInLocation;
	}

	// Returns CSV output path for this type.
	public String getCsvOutputLocation() {
		return csvOutLocation;
	}

	// Returns actual class for this type.
	public Class<? extends StringArrayConv> getEntryClass() {
		return type;
	}

	// Returns the entry type tied to the input class (if any).
	public static EntryType getFromClass(Class<? extends StringArrayConv> cls) {
		for (EntryType et : EntryType.values()) {
			if (et.getEntryClass() == cls) {
				return et;
			}
		}

		return null;
	}
	// endregion
}
