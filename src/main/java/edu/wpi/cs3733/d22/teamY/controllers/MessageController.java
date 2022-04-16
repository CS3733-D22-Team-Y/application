package edu.wpi.cs3733.d22.teamY.controllers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import edu.wpi.cs3733.d22.teamY.Messaging.Chat;
import edu.wpi.cs3733.d22.teamY.Messaging.ChatManager;
import edu.wpi.cs3733.d22.teamY.Messaging.Firebase;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MessageController {

  @FXML private MFXTextField messageText;

  @FXML private Pane blankMessage;
  @FXML private Rectangle bRect;
  @FXML private HBox bHbox;
  @FXML private Circle bUnread;
  @FXML private Pane bPicPane;
  @FXML private Circle bCircle;
  @FXML private Label bInitials;
  @FXML private Label bPreview;

  @FXML private VBox chatSelector;

  private String chatID = "";
  private boolean chatOpen = false;

  // initialize the controller
  public void initialize() throws IOException {
    messageText.setPromptText("Enter your message here");
    String id = PersonalSettings.currentEmployee.getIDNumber();
    System.out.println("Init message controller here: " + id);
    Firebase.init();
    Firebase.chatRef
        .child(id)
        .addChildEventListener(
            new ChildEventListener() {

              @Override
              public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Chat c = snapshot.getValue(Chat.class);
                ChatManager.myChats.put(snapshot.getKey(), c);
                System.out.println("Added chat from listener: \n" + c);
                refresh();
              }

              @Override
              public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                Chat c = snapshot.getValue(Chat.class);
                ChatManager.myChats.put(snapshot.getKey(), c);
                for (String s : c.getUsers()) {
                  Firebase.chatRef.child(s).child(snapshot.getKey()).setValueAsync(c);
                }
                System.out.println("Added chat from listener: \n" + c);
                refresh();
              }

              @Override
              public void onChildRemoved(DataSnapshot snapshot) {
                ChatManager.myChats.remove(snapshot.getKey());
              }

              @Override
              public void onChildMoved(DataSnapshot snapshot, String previousChildName) {}

              @Override
              public void onCancelled(DatabaseError error) {}
            });

    //    this.refresh();
  }

  public void refresh() {
    System.out.println("refreshing");
    chatSelector.getChildren().clear();
    HashMap<String, Chat> chats = ChatManager.getChats();
    System.out.println(chats.size());
    for (String key : chats.keySet()) {
      Pane clone = getBlankMessageClone(key, chats.get(key));
      chatSelector.getChildren().add(clone);
    }
    System.out.println("Refreshed");
  }

  public void send() {
    String text = messageText.getText();
    ChatManager.sendMessage(text, PersonalSettings.currentEmployee.getIDNumber(), "2");
    refresh();
    // toUID.getText());
  }

  public Pane getBlankMessageClone(String chatID, Chat chat) {
    // create a clone of the blank message pane and all its children
    Pane clone = getPaneClone(blankMessage);
    Rectangle bRectClone = getRectClone(bRect);
    HBox bHboxClone = getHboxClone(bHbox);
    Circle bUnreadClone = getCircleClone(bUnread);
    Pane bPicPaneClone = getPaneClone(bPicPane);
    Circle bCircleClone = getCircleClone(bCircle);
    Label bInitialsClone = getLabelClone(bInitials);
    Label bPreviewClone = getLabelClone(bPreview);

    // set the rectangle and hbox to be children of the main pane
    clone.getChildren().add(bRectClone);
    clone.getChildren().add(bHboxClone);

    // set the unread circle, pane and preview to be children of the hbox
    bHboxClone.getChildren().add(bUnreadClone);
    bHboxClone.getChildren().add(bPicPaneClone);
    bHboxClone.getChildren().add(bPreviewClone);

    // set the circle and initials to be children of the pic pane
    bPicPaneClone.getChildren().add(bCircleClone);
    bPicPaneClone.getChildren().add(bInitialsClone);

    // set the preview text to be the chat's preview
    bPreviewClone.setText(chat.getPosts().get(chat.getPosts().size() - 1).getMessage());

    // on click of the message, set the chatID to the chatID of the message
    bHboxClone.setOnMouseClicked(
        e -> {
          this.chatID = chatID;
          chatOpen = true;
        });

    return clone;
  }

  public Pane getPaneClone(Pane p) {
    // clones the pane p
    Pane clone = new Pane();
    clone.setPrefSize(p.getPrefWidth(), p.getPrefHeight());
    clone.setLayoutX(p.getLayoutX());
    clone.setLayoutY(p.getLayoutY());
    clone.setStyle(p.getStyle());
    return clone;
  }

  public Rectangle getRectClone(Rectangle r) {
    // clones the rectangle r
    Rectangle clone = new Rectangle();
    clone.setWidth(r.getWidth());
    clone.setHeight(r.getHeight());
    clone.setX(r.getX());
    clone.setY(r.getY());
    clone.setArcHeight(r.getArcHeight());
    clone.setArcWidth(r.getArcWidth());
    clone.setFill(r.getFill());
    clone.setStroke(r.getStroke());
    clone.setStrokeWidth(r.getStrokeWidth());
    return clone;
  }

  public HBox getHboxClone(HBox h) {
    // clones the hbox h
    HBox clone = new HBox();
    clone.setPrefSize(h.getPrefWidth(), h.getPrefHeight());
    clone.setLayoutX(h.getLayoutX());
    clone.setLayoutY(h.getLayoutY());
    clone.setStyle(h.getStyle());
    clone.setAlignment(h.getAlignment());
    return clone;
  }

  public Circle getCircleClone(Circle c) {
    // clones the circle c
    Circle clone = new Circle();
    clone.setRadius(c.getRadius());
    clone.setCenterX(c.getCenterX());
    clone.setCenterY(c.getCenterY());
    clone.setFill(c.getFill());
    clone.setStroke(c.getStroke());
    clone.setStrokeWidth(c.getStrokeWidth());
    clone.setTranslateX(c.getTranslateX());
    clone.setTranslateY(c.getTranslateY());
    clone.setLayoutX(c.getLayoutX());
    clone.setLayoutY(c.getLayoutY());
    return clone;
  }

  public Label getLabelClone(Label l) {
    // clones the label l
    Label clone = new Label();
    clone.setPrefSize(l.getPrefWidth(), l.getPrefHeight());
    clone.setLayoutX(l.getLayoutX());
    clone.setLayoutY(l.getLayoutY());
    clone.setStyle(l.getStyle());
    clone.setText(l.getText());
    clone.setTextFill(l.getTextFill());
    clone.setFont(l.getFont());
    return clone;
  }
}
