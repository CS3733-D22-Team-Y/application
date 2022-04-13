package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.firebase.database.DatabaseReference;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.util.ArrayList;
import java.util.Collections;

public class MessageService {

  private MessageService() {}

  public static void sendMessage(String message, Employee from, Employee... to) {
    Post post = new Post(from.getName(), message);
    String key = getChatKey(from, to);
    DatabaseReference chatRoom = Firebase.database.getReference("/posts/" + key);
    chatRoom.setValueAsync(post);
  }

  private static String getChatKey(Employee from, Employee... to) {
    ArrayList<String> members = new ArrayList<String>();
    for (Employee employee : to) {
      members.add(employee.getName());
    }
    members.add(from.getName());
    Collections.sort(members);
    String key = "";
    for (String member : members) {
      key += member + ",";
    }
    // remove trailing comma
    if (key.length() > 0) {
      key = key.substring(0, key.length() - 1);
    }
    return key;
  }
}
