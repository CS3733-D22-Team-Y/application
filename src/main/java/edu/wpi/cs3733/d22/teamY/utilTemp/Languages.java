package edu.wpi.cs3733.d22.teamY.utilTemp;

import java.util.ArrayList;
import java.util.List;

public class Languages {
  List langs = new ArrayList();

  public Languages() {
    langs.add("English");
    langs.add("Mandarin Chinese");
    langs.add("Hindi");
    langs.add("Spanish");
    langs.add("French");
    langs.add("Arabic");
    langs.add("Bengali");
    langs.add("Russian");
    langs.add("Portuguese");
    langs.add("Urdu");
    langs.add("Indonesian");
    langs.add("German");
    langs.add("Japanese");
    langs.add("Nigerian Pidgin");
    langs.add("Marathi");
    langs.add("Telugu");
    langs.add("Turkish");
    langs.add("Tamil");
    langs.add("Yue Chinese");
    langs.add("Vietnamese");
  }

  public List getLanguages() {
    return langs;
  }

  public void addLanguage(String lang) {
    langs.add(lang);
  }

  public boolean isLanguage(String check) {
    System.out.println("check " + check);
    System.out.println(langs);

    System.out.println("is valid: " + langs.contains(check));
    return langs.contains(check);
  }
}
