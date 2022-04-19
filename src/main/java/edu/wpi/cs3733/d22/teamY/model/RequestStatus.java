package edu.wpi.cs3733.d22.teamY.model;

public enum RequestStatus {
  INCOMPLETE,
  IN_PROGRESS,
  COMPLETE,
  CANCELED;

  public static RequestStatus toStatus(String in) {
    switch (in) {
      case "INCOMPLETE":
        return INCOMPLETE;
      case "IN_PROGRESS":
        return IN_PROGRESS;
      case "COMPLETE":
        return COMPLETE;
      case "CANCELED":
        return CANCELED;
    }
    return CANCELED;
  }
}
