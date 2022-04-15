package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.firebase.database.DatabaseReference;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MessageService {

  private MessageService() {}

  public static void sendMessage(String message, Employee from, Employee... to) {
    String key = getChatKey(from, to);
    Post post = new Post(from.getIDNumber(), key, message);

    DatabaseReference chatArea =
        Firebase.database.getReference("/chats/" + key + "/" + post.getTime());
    HashMap<String, Post> postMap = new HashMap<String, Post>();
    chatArea.setValueAsync(post);
  }

  private static String getChatKey(Employee from, Employee... to) {
    ArrayList<String> members = new ArrayList<String>();
    for (Employee employee : to) {
      members.add(employee.getIDNumber());
    }
    members.add(from.getIDNumber());
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
