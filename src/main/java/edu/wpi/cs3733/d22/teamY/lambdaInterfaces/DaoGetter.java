package edu.wpi.cs3733.d22.teamY.lambdaInterfaces;

import edu.wpi.cs3733.d22.teamY.model.dao.Dao;

public interface DaoGetter<T> {
  Dao<T> run();
}
