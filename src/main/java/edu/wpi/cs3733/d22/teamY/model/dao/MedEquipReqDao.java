package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.MedEquipReq;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoDeleteException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoUpdateException;
import java.util.List;

public interface MedEquipReqDao {
  List<MedEquipReq> getAllMedEquipReq() throws DaoGetException;

  MedEquipReq getMedEquipReq(String requestNum) throws DaoGetException;

  void addMedEquipReq(MedEquipReq mreq) throws DaoAddException;

  void updateMedEquipReq(MedEquipReq mreq) throws DaoUpdateException;

  void deleteMedEquipReq(MedEquipReq mreq) throws DaoDeleteException;
}
