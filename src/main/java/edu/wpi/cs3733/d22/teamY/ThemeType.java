package edu.wpi.cs3733.d22.teamY;

import edu.wpi.cs3733.d22.teamY.model.*;

public class ThemeType {
  public static String convertTheme(String s) {
    String themePath = null;
    if (s.equals("dark")) {
      themePath = "views/css/Theme1.css";
    } else {
      themePath = "views/css/NormalTheme.css";
    }
    return themePath;
  }
}
