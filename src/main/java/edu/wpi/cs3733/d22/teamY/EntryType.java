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

  MED_EQUIP_REQ(3, "MedEquipReq", "medEquipRequests_export", MedEquipReq.class),

  LAB_REQUESTS(7, "LabRequests", "labRequests_export", LabRequest.class),

// region Enum Data / Methods (don't touch probably)
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

  public int getColumns() {
    return columns;
  }

  public String getCsvInputLocation() {
    return csvInLocation;
  }

  public String getCsvOutputLocation() {
    return csvOutLocation;
  }

  public Class<? extends StringArrayConv> getEntryClass() {
    return type;
  }

  public static EntryType getFromClass(Class<?> cls) {
    for (EntryType et : EntryType.values()) {
      if (et.getEntryClass() == cls) {
        return et;
      }
    }

    return null;
  }
  // endregion
}
