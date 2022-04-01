package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.Location;
import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;

import java.util.List;

public enum entryType {
    LOCATION(8, Location::new),
    MED_EQUIP(4, MedEquip::new),
    MED_EQUIP_REQ(3, MedEquipReq::new);



    private final int columns;
    private final entryMaker generator;
    entryType(int cols, entryMaker em)
    {
        columns = cols;
        generator = em;
    }

    public int getColumns()
    {
        return columns;
    }

    public Object newNode(List<String> csvData)
    {
        return generator.run(csvData);
    }
}
