package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.util.List;

public abstract class MedEquipDao extends Dao<MedEquip> {
  public abstract List<MedEquip> getAllMedEquip() throws DaoGetException;

  public abstract MedEquip getMedEquip(String equipID) throws DaoGetException;

  public abstract void addMedEquip(MedEquip medEquip) throws DaoAddException;

  public abstract void updateMedEquip(MedEquip medEquip) throws DaoUpdateException;

  public abstract void deleteMedEquip(MedEquip medEquip) throws DaoDeleteException;
}
