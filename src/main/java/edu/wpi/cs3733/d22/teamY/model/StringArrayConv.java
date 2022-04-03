package edu.wpi.cs3733.d22.teamY.model;

// Interface for objects that need to be converted to CSV and back by CSVBackup.
public interface StringArrayConv {
  // Returns this object as an array of Strings, one per column of the corresponding table,
  // each representing that given column's value in String form.
  String[] toStringArray();

  // Initializes this object's fields from an array of Strings, where each String is the
  // value of the corresponding column in the type's relevant table.
  void fromStringArray(String[] args);
}
