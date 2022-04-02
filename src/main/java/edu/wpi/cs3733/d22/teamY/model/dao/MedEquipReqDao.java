package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.util.List;

public abstract class MedEquipReqDao extends Dao<MedEquipReq> {

  public abstract List<MedEquipReq> getAllMedEquipReq() throws DaoGetException;

  public abstract MedEquipReq getMedEquipReq(String requestNum) throws DaoGetException;

  public abstract void addMedEquipReq(MedEquipReq mreq) throws DaoAddException;

  public abstract void updateMedEquipReq(MedEquipReq mreq) throws DaoUpdateException;

  public abstract void deleteMedEquipReq(MedEquipReq mreq) throws DaoDeleteException;
}
