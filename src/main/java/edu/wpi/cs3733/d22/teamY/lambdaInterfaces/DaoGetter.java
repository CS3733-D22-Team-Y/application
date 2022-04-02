package edu.wpi.cs3733.d22.teamY.lambdaInterfaces;

import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import edu.wpi.cs3733.d22.teamY.model.dao.Dao;

public interface DaoGetter<T extends StringArrayConv> {
  Dao<T> run();
}
