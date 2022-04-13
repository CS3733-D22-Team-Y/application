package edu.wpi.cs3733.d22.teamY.model;

/** Interface for request objects. Add methods that are common across all request objects. */
public interface Requestable {
  public String getLocID();
  public String getRequestNum();
  public String getRequestType();

  public String getStatus();
  public void setStatus(String status);

  public String getAssignedNurse();
  public void setAssignedNurse(String nurse);

  public String getDescription();


}
