package edu.wpi.cs3733.d22.teamY;

public class MedEquip extends DBObject{
    public static final String TABLE_NAME = "medequip";
    public static final String KEY_ATTRIBUTE_NAME = "equipID";
    private String equipID;
    private String equipType;
    private String equipLocId;

    public String getEquipID() {
        return equipID;
    }

    public String getEquipType() {
        return equipType;
    }

    public String getEquipLocId() {
        return equipLocId;
    }

    public boolean isClean() {
        return isClean;
    }

    private boolean isClean;


    public MedEquip(
            String equipID, String equipType, String equipLocId, boolean isClean) {
        this.equipID = equipID;
        this.equipType = equipType;
        this.equipLocId = equipLocId;
        this.isClean = isClean;
    }



    public String getID() {
        return equipID;
    }

    public MedEquip getClone() {
        return new MedEquip(equipID, equipType, equipLocId, isClean);
    }
}
