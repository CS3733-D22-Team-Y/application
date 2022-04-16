package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class ChatManager {

  private ChatManager() {}

  private static HashMap<String, Chat> chats = new HashMap<String, Chat>();

  public static void init(String id) {
    // clear all chats
    chats.clear();
    Firebase.chatRef
        .child(id)
        .addValueEventListener(
            new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot snapshot) {
                Chat c = snapshot.getValue(Chat.class);
                chats.put(snapshot.getKey(), c);
                System.out.println("ID: " + snapshot.getKey() + "Added chat from listener: \n" + c);
              }

              @Override
              public void onCancelled(DatabaseError error) {}
            });
  }

  public static void sendMessage(String message, String... recipientIDs) {
    for (String id : recipientIDs) {
      Chat c;
      if (!(chats.containsKey(id))) {
        c = new Chat(recipientIDs);
        System.out.println("Created new chat: \n" + c);
      } else {
        c = chats.get(id);
      }
      c.addPost(new Post(message));
      Firebase.chatRef.child(id).setValueAsync(c);
      //      Firebase.chatRef.setValueAsync(c);
    }
  }
}
