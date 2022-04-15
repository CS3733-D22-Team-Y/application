package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;

public class ChatManager {

  private ChatManager() {}

  public static ArrayList<String> chats = new ArrayList<String>();

  public static void addChat(String chat) {
    if (!chats.contains(chat)) {
      chats.add(chat);

      Firebase.chatRef
          .child(chat)
          .addChildEventListener(
              new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                  //                  System.out.println("onAddedChild");
                  //                  System.out.println(dataSnapshot.getValue());
                  Post p = dataSnapshot.getValue(Post.class);
                  System.out.println("New Message" + p.getMessage());
                  //                  System.out.println(p);
                }

                @Override
                public void onChildChanged(DataSnapshot snapshot, String previousChildName) {}

                @Override
                public void onChildRemoved(DataSnapshot snapshot) {}

                @Override
                public void onChildMoved(DataSnapshot snapshot, String previousChildName) {}

                @Override
                public void onCancelled(DatabaseError error) {}
              });
    }
  }

  public boolean contains(String chat) {
    return chats.contains(chat);
  }
}
