package edu.wpi.cs3733.d22.teamY.Messaging;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import java.io.IOException;

public class Firebase {
  public static FirebaseDatabase database;
  public static DatabaseReference mainRef;

  public static void init() throws IOException {

    FirebaseOptions options =
        FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.getApplicationDefault())
            .setDatabaseUrl("https://get-wonged-default-rtdb.firebaseio.com/")
            .build();

    FirebaseApp.initializeApp(options);
    // Get a reference to our posts
    database = FirebaseDatabase.getInstance();
    mainRef = database.getReference("/");
    createEventListeners();
  }

  private static void createEventListeners() {
    mainRef.addValueEventListener(
        new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            Post post = dataSnapshot.getValue(Post.class);
            System.out.println(post);
            System.out.println("Data was changed");
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
          }
        });

    mainRef.addChildEventListener(
        new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            Post newPost = dataSnapshot.getValue(Post.class);
              System.out.println(newPost.toString());
          }

          @Override
          public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            System.out.println("Child changed");
            Post changedPost = dataSnapshot.getValue(Post.class);
            System.out.println("Author: " + changedPost.author);
            System.out.println("Title: " + changedPost.message);
          }

          @Override
          public void onChildRemoved(DataSnapshot dataSnapshot) {
            System.out.println("Child removed");
          }

          @Override
          public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            System.out.println("Child moved");
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed");
          }
        });
  }
}
