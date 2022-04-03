package edu.wpi.cs3733.d22.teamY;

import java.io.Serializable;

public class Where<T extends Serializable> {

  private final String dbField;
  private final T dbValue;

  public Where(String field, T value) {
    dbField = field;
    dbValue = value;
  }

  public String getField() {
    return dbField;
  }

  public T getValue() {
    return dbValue;
  }
}
