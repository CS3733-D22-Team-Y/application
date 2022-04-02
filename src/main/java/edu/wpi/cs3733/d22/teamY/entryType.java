package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.lambdaInterfaces.DaoGetter;
import edu.wpi.cs3733.d22.teamY.lambdaInterfaces.EntryMaker;
import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import edu.wpi.cs3733.d22.teamY.model.dao.Dao;
import java.util.List;

public enum entryType {

  // Place all new entry types here.
  // In order: specify number of columns, CSV import path, CSV export path,
  // actual entry class, (List<String>-compatible) constructor, and DAO getter.

  // Remember! For something to qualify as an entry, it must implement StringArrayConv
  // as this ensures that it's a type that can be properly formatted for CSV.

  LOCATION(
      8,
      "TowerLocations",
      "locations_export",
      Location.class,
      Location::new,
      DaoManager::getLocationDao),

  MED_EQUIP(
      4,
      "MedEquip",
      "medEquipment_export",
      MedEquip.class,
      MedEquip::new,
      DaoManager::getMedEquipDao),

  MED_EQUIP_REQ(
      3,
      "MedEquipReq",
      "medEquipRequests_export",
      MedEquipReq.class,
      MedEquipReq::new,
      DaoManager::getMedEquipReqDao),

// region Enum Data / Methods
/* Enum specification begins below... */ ;

  private final int columns;
  private final String csvInLocation;
  private final String csvOutLocation;
  private final Class<? extends StringArrayConv> type;

  private final EntryMaker<? extends StringArrayConv> entryGenerator;
  private final DaoGetter<? extends StringArrayConv> daoGetter;

  <T extends StringArrayConv> entryType(
      int cols, String inPath, String outPath, Class<T> cls, EntryMaker<T> em, DaoGetter<T> dg) {
    columns = cols;
    csvInLocation = inPath;
    csvOutLocation = outPath;
    type = cls;
    entryGenerator = em;
    daoGetter = dg;
  }

  public int getColumns() {
    return columns;
  }

  public Object newNode(List<String> csvData) {
    return entryGenerator.run(csvData);
  }

  public Dao<?> getDao() {
    return daoGetter.run();
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

  public static entryType getFromClass(Class<?> cls) {
    for (entryType et : entryType.values()) {
      if (et.getEntryClass() == cls) {
        return et;
      }
    }

    return null;
  }
  // endregion
}
