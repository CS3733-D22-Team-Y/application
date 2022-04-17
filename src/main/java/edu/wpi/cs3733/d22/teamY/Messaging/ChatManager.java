package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatManager {

  private ChatManager() {}

  public static HashMap<String, Chat> myChats = new HashMap<>();

  public static void init(String id) {
    // clear all chats
    myChats.clear();
    Firebase.chatRef
        .child(id)
        .addChildEventListener(
            new ChildEventListener() {

              @Override
              public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Chat c = snapshot.getValue(Chat.class);
                myChats.put(snapshot.getKey(), c);
                System.out.println("Added chat from listener: \n" + c);
              }

              @Override
              public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                Chat c = snapshot.getValue(Chat.class);
                myChats.put(snapshot.getKey(), c);
                for (String s : c.getUsers()) {
                  Firebase.chatRef.child(s).child(snapshot.getKey()).setValueAsync(c);
                }
                System.out.println("Added chat from listener: \n" + c);
              }

              @Override
              public void onChildRemoved(DataSnapshot snapshot) {
                myChats.remove(snapshot.getKey());
                System.out.println("Removed chat from listener: \n" + snapshot.getKey());
              }

              @Override
              public void onChildMoved(DataSnapshot snapshot, String previousChildName) {}

              @Override
              public void onCancelled(DatabaseError error) {}
            });
  }

  public static void sendMessage(String message, String myID, String... recipientIDs) {
    addMyMessage(message, myID, recipientIDs);
    String chatID = Chat.getChatID(myID, recipientIDs);
    for (String id : recipientIDs) {
      addTheirMessage(chatID, id);
    }
  }

  private static void addTheirMessage(String chatID, String id) {
    Chat c = myChats.get(chatID);
    Firebase.chatRef.child(id).child(chatID).setValueAsync(c);
    System.out.println("Added their message" + (c == null));
  }

  private static void addMyMessage(String message, String myID, String... recipientIDs) {
    Chat c;
    String chatID = Chat.getChatID(myID, recipientIDs);
    if (!(myChats.containsKey(chatID))) {
      c = new Chat(myID, recipientIDs);
      myChats.put(chatID, c);
      System.out.println("Created new chat: \n" + c);
    } else {
      c = myChats.get(chatID);
    }
    c.addPost(new Post(message, myID));
    Firebase.chatRef.child(myID).child(chatID).setValueAsync(c);
  }

  public static HashMap<String, Chat> getChats() {
    return myChats;
  }

  public static void sendMessage(String text, String idNumber, ArrayList<String> users) {
    ArrayList<String> usersCopy = (ArrayList<String>) users.clone();
    usersCopy.remove(idNumber);
    String[] res = new String[usersCopy.size()];
    res = usersCopy.toArray(res);
    sendMessage(text, idNumber, res);
  }
}
