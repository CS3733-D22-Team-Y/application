package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import java.io.IOException;

public class Firebase {
  public static FirebaseDatabase database;
  public static DatabaseReference chatRef;

  public static void init() throws IOException {

    FirebaseOptions options =
        FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.getApplicationDefault())
            .setDatabaseUrl("https://get-wonged-default-rtdb.firebaseio.com/")
            .build();

    FirebaseApp.initializeApp(options);
    // Get a reference to our posts
    database = FirebaseDatabase.getInstance();
    chatRef = database.getReference("/chats/");
    //    createEventListeners();
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
          public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            //            System.out.println("onAddedChild");
            //            System.out.println(dataSnapshot.getValue());
          }

          @Override
          public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            //            System.out.println("onChildChanged");
          }

          @Override
          public void onChildRemoved(DataSnapshot dataSnapshot) {

            //              System.out.println("Child removed");
          }

          @Override
          public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            //            System.out.println("Child moved");
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

            //              System.out.println("The read failed");
          }
        });
  }
}
