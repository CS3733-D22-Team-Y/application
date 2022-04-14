package edu.wpi.cs3733.d22.teamY.controllers;

import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.Messaging.MessageService;
import edu.wpi.cs3733.d22.teamY.Where;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class MessageController {

  @FXML private MFXTextField messageText;
  @FXML private MFXTextField toUID;

  public void send() {
    String text = messageText.getText();
    Employee e =
        (Employee) DBManager.getAll(Employee.class, new Where("idNumber", toUID.getText())).get(0);
    MessageService.sendMessage(text, PersonalSettings.currentEmployee, e);
  }
}
