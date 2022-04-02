package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import java.util.List;

public abstract class Dao<T> {

  public abstract List<T> getAll() throws DaoGetException;

  public abstract void add(T t) throws DaoAddException;

  public void addAll(List<T> l) throws DaoAddException {
    for (T t : l) {
      try {
        add(t);
      } catch (DaoAddException e) {
        throw new DaoAddException(e);
      }
    }
  }
}
