package edu.wpi.cs3733.d22.teamY;

import java.io.Serializable;

public class Where {

  private final String dbField;
  private final Serializable dbValue;

  public Where(String field, Serializable value) {
    dbField = field;
    dbValue = value;
  }

  public String getField() {
    return dbField;
  }

  public Serializable getValue() {
    return dbValue;
  }
}
