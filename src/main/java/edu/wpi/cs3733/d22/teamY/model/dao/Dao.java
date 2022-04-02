package edu.wpi.cs3733.d22.teamY.model.dao;

import edu.wpi.cs3733.d22.teamY.model.StringArrayConv;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoAddException;
import edu.wpi.cs3733.d22.teamY.model.dao.exception.DaoGetException;
import java.util.List;

public abstract class Dao<T extends StringArrayConv> {

  public abstract List<T> getAll() throws DaoGetException;

  public abstract void add(T t) throws DaoAddException;

  @SuppressWarnings("unchecked")
  public void addAll(List<StringArrayConv> l) throws DaoAddException {
    for (StringArrayConv t : l) {
      try {
        add((T) t);
      } catch (DaoAddException | ClassCastException e) {
        throw new DaoAddException(e);
      }
    }
  }
}
