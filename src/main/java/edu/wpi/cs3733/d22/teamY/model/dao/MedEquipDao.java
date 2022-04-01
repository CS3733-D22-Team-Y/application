package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.MedEquip;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.util.List;

public interface MedEquipDao {
  List<MedEquip> getAllMedEquip() throws DaoGetException;

  MedEquip getMedEquip(String equipID) throws DaoGetException;

  void addMedEquip(MedEquip medEquip) throws DaoAddException;

  void updateMedEquip(MedEquip medEquip) throws DaoUpdateException;

  void deleteMedEquip(MedEquip medEquip) throws DaoDeleteException;
}
