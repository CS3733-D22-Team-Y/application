package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import edu.wpi.cs3733.d22.teamY.controllers.PersonalSettings;
import java.io.IOException;
import java.io.InputStream;

public class Firebase {
  public static FirebaseDatabase database;
  public static DatabaseReference chatRef;

  public static void init() throws IOException {
    if (FirebaseApp.getApps().size() == 0) {
      InputStream serviceAccount =
          Firebase.class
              .getClassLoader()
              .getResourceAsStream("get-wonged-firebase-adminsdk-dc3b7-37496f4921.json");

      // print the service account
      System.out.println(serviceAccount);

      FirebaseOptions options =
          FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .setDatabaseUrl("https://get-wonged-default-rtdb.firebaseio.com/")
              .build();

      FirebaseApp.initializeApp(options);
      // Get a reference to our posts
      database = FirebaseDatabase.getInstance();
      chatRef = database.getReference("/chats/");
    }
    ChatManager.myChats.clear();
  }

  public static void initListeners() {
    ChatManager.init(PersonalSettings.currentEmployee.getIDNumber());
  }

  private static void createEventListeners() {
    chatRef.addValueEventListener(
        new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            System.out.println("onDataChange");
            System.out.println(dataSnapshot.getValue());
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
            System.out.println("onCancel" + databaseError.getCode());
          }
        });

    chatRef.addChildEventListener(
        new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {}

          @Override
          public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

          @Override
          public void onChildRemoved(DataSnapshot dataSnapshot) {}

          @Override
          public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

          @Override
          public void onCancelled(DatabaseError databaseError) {}
        });
  }
}
