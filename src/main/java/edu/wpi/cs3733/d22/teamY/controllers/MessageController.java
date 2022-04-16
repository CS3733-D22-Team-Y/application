package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.Messaging.ChatManager;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class MessageController {

  @FXML private MFXTextField messageText;
  @FXML private MFXTextField toUID;

  public void send() {
    String text = messageText.getText();
    ChatManager.sendMessage(text, PersonalSettings.currentEmployee.getIDNumber(), toUID.getText());
  }
}
