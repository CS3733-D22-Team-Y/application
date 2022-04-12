package edu.wpi.cs3733.d22.teamY;

public enum AuthTypes {
  NONE("none", 0),
  SMS("sms", 1),
  EMAIL("email", 1),
  PUSH_BULLET("pushbullet", 1),
  YUBIKEY("yubikey", 1);
  private String NAME;
  private int ARGS;

  AuthTypes(String name, int args) {
    NAME = name;
    ARGS = args;
  }

  public String getName() {
    return NAME;
  }

  public int getArgs() {
    return ARGS;
  }
}
