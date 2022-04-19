package edu.wpi.cs3733.d22.teamY.controllers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.DBUtils;
import edu.wpi.cs3733.d22.teamY.Messaging.Chat;
import edu.wpi.cs3733.d22.teamY.Messaging.ChatManager;
import edu.wpi.cs3733.d22.teamY.Messaging.Firebase;
import edu.wpi.cs3733.d22.teamY.Messaging.Post;
import edu.wpi.cs3733.d22.teamY.model.Employee;
import edu.wpi.cs3733.d22.teamY.utilTemp.SearchUtil;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MessageController {

  @FXML private MFXTextField messageText;
  @FXML private Button sendButton;

  @FXML private Pane blankMessage;
  @FXML private Rectangle bRect;
  @FXML private HBox bHbox;
  @FXML private Circle bUnread;
  @FXML private Pane bPicPane;
  @FXML private Circle bCircle;
  @FXML private Label bInitials;
  @FXML private Label bPreview;
  @FXML private Label bName;
  @FXML private VBox bTimeVbox;
  @FXML private VBox bNamePrevBox;
  @FXML private Label bTime;
  @FXML private Label chatSelectLabel;

  @FXML private VBox chatSelector;
  @FXML private VBox messageArea;
  @FXML private Pane sendPane;
  @FXML private MFXScrollPane messageAreaContainer;

  // variables associated with a message
  @FXML private Pane messageBarPane;
  @FXML private Pane textContainer;
  @FXML private HBox messageInfoPanel;
  @FXML private Rectangle messageBackground;
  @FXML private Label messageLabel;
  @FXML private Label author;
  @FXML private Label time;

  @FXML private VBox newChatArea;
  @FXML private MFXTextField toBox;
  @FXML private MFXScrollPane resultsArea;

  @FXML private AnchorPane sidebarPane;

  // variables associated with a result in the search
  @FXML private Pane resultItemPane;
  @FXML private Rectangle resultRect;
  @FXML private VBox resultVbox;
  @FXML private Label resultName;
  @FXML private Label resultRole;

  @FXML private Pane resultPane;

  ArrayList<EmployeeResult> results = new ArrayList<>();

  private String chatID = "";
  private boolean chatOpen = false;
  private boolean newChatOpen = false;

  // initialize the controller
  public void initialize() throws IOException {
    messageText.setPromptText("Enter your message here");
    messageArea.getChildren().clear();
    resultPane.getChildren().clear();
    setChatOpen(chatOpen);
    setChatPickerOpen(newChatOpen);
    String id = PersonalSettings.currentEmployee.getIDNumber();
    //    System.out.println("Init message controller here: " + id + " " +
    // ChatManager.getChats().size());
    Firebase.chatRef.addChildEventListener(childEventListener);
    List<Employee> employees = DBManager.getAll(Employee.class);
    for (Employee e : employees) {
      results.add(new EmployeeResult(e.getName(), e.getRole(), e.getIDNumber()));
    }

    this.refreshChats();
  }

  public void setChatOpen(boolean open) {
    chatOpen = open;
    sendPane.setVisible(chatOpen);
    chatSelectLabel.setVisible(!chatOpen);
    messageAreaContainer.setVisible(chatOpen);
    messageArea.setVisible(chatOpen);
    if (chatOpen) {
      refreshMessages();
      messageText.requestFocus();
    }
  }

  public void refreshChats() {
    chatSelector.getChildren().clear();

    HashMap<String, Chat> chats = ChatManager.getChats();

    System.out.println(chats.size());
    for (String key : chats.keySet()) {
      Pane clone = getBlankMessageClone(key, chats.get(key));
      chatSelector.getChildren().add(clone);
    }
    // scroll to bottom
    Platform.runLater(
        () -> {
          messageAreaContainer.setVvalue(1.0);
        });
  }

  public void refreshMessages() {
    messageArea.getChildren().clear();
    Chat c = ChatManager.myChats.get(chatID);
    ArrayList<Post> posts = new ArrayList<>();
    try {
      posts = c.getPosts();
    } catch (Exception e) {
      return;
    }
    for (Post p : posts) {
      try {
        messageArea.getChildren().add(getMessageClone(p));
      } catch (Exception e) {
        //        System.out.println("Error getting message clone");
      }
    }
    // scrolls to bottom
    Platform.runLater(
        () -> {
          messageAreaContainer.setVvalue(1.0);
        });
  }

  public void send() {
    String text = messageText.getText();
    if (text.length() == 0) {
      System.out.println("No text");
      return;
    }
    if(text.length() > 140) {
      System.out.println("Message too long");
      return;
    }
    ChatManager.sendMessage(
        text,
        PersonalSettings.currentEmployee.getIDNumber(),
        ChatManager.myChats.get(chatID).getUsers());
    messageText.setText("");
  }

  public void startChat() {
    setChatOpen(false);
    setChatPickerOpen(true);
    toBox.requestFocus();
    this.getResults();
  }

  private void setChatPickerOpen(boolean b) {
    newChatOpen = b;
    newChatArea.setVisible(b);
  }

  public void selectedChat() {
    String text = toBox.getText().trim().replaceAll(" ", "");
    if (text.length() > 0 && text.charAt(text.length() - 1) == ',') {
      text = text.substring(0, text.length() - 1);
    }
    String[] splitText = text.split(",");
    if (text.length() == 0) {
      System.out.println("No text in to box");
      return;
    }
    for (String s : splitText) {
      if (s.length() == 0 || !isNumeric(s)) {
        System.out.println("empty recipient");
        return;
      }
      if (s.equals(PersonalSettings.currentEmployee.getIDNumber())) {
        System.out.println("Cannot send to yourself");
        return;
      }
    }
    if (text.contains(",,")) {
      System.out.println("empty recipient");
      return;
    }

    chatID = Chat.getChatID(PersonalSettings.currentEmployee.getIDNumber(), splitText);
    System.out.println("Attempting to start chat with ID" + chatID);
    if (!ChatManager.myChats.containsKey(chatID)) {
      // send initial message
      ChatManager.sendMessage(
          "Chat Created", PersonalSettings.currentEmployee.getIDNumber(), splitText);
    } else {
      System.out.println("Chat already exists");
    }
    setChatPickerOpen(false);
    setChatOpen(true);
  }

  public boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // on key released handler for the to box:
  public void getResults() {
    // remove all spaces
    toBox.setText(toBox.getText().replaceAll(" ", ""));
    //    // remove all double commas
    toBox.setText(toBox.getText().replaceAll(",,", ","));
    // set the cursor to the end of the text
    toBox.positionCaret(toBox.getText().length());
    String text = toBox.getText();
    String[] splitText = text.split(",");
    // show search results
    String query = "";
    if (text.length() > 0) {
      query = splitText[splitText.length - 1];
      if (text.charAt(text.length() - 1) == ',') {
        query = "";
      }
    }
    // if a comma was the last character, query is empty

    showResults(query);
  }

  public void showResults(String query) {
    // clear the list
    resultPane.getChildren().clear();
    // get the results
    results.sort(Comparator.comparingInt(o -> SearchUtil.getMatchScore(o.getName(), query)));
    // add the results to the result pane
    for (EmployeeResult result : results) {
      resultPane.getChildren().add(result.getResultPane());
    }
  }

  public Pane getResultClone(String nameString, String roleString, String id) {
    // clone the result item pane
    Pane clone = getPaneClone(resultItemPane);
    Rectangle cloneResultRect = getRectClone(resultRect);
    VBox cloneResultVbox = getVboxClone(resultVbox);
    Label cloneResultName = getLabelClone(resultName);
    Label cloneResultRole = getLabelClone(resultRole);

    // set the clone to have the rectangle and vbox as children
    clone.getChildren().addAll(cloneResultRect, cloneResultVbox);

    // set the vbox to have the name and role as children
    cloneResultVbox.getChildren().addAll(cloneResultName, cloneResultRole);

    // set the name and role to have the text
    cloneResultName.setText(nameString);
    cloneResultRole.setText(roleString + "    |   ID#" + id);

    // if the main clone pane is clicked, print the id
    clone.setOnMouseClicked(
        e -> {
          addRecipientToChat(id);
        });

    return clone;
  }

  public void addRecipientToChat(String id) {
    System.out.println("Adding " + id + " to chat");
    // remove everything before the last comma and add the new id
    String text = toBox.getText();

    if (text.contains(",")) {
      // find the index of the last comma
      int index = text.lastIndexOf(",");
      // remove everything after the last comma
      text = text.substring(0, index);
      // add the new id
      text += "," + id + ",";
    } else {
      text = id + ",";
    }
    // set the text
    toBox.setText(text);
    toBox.requestFocus();
    // set the cursor to the end of the text
    toBox.positionCaret(toBox.getText().length());
  }

  public Pane getMessageClone(Post p) {
    // clones the pane messageBarPane
    Pane clone = getPaneClone(messageBarPane);
    Pane textContainerClone = getPaneClone(textContainer);
    HBox messageInfoPanelClone = getHboxClone(messageInfoPanel);
    Rectangle messageBackgroundClone = getRectClone(messageBackground);
    Label messageLabelClone = getLabelClone(messageLabel);
    Label authorClone = getLabelClone(author);
    Label timeClone = getLabelClone(time);

    // set text container and message info panel to be children of clone
    clone.getChildren().add(textContainerClone);
    clone.getChildren().add(messageInfoPanelClone);

    // set the message background and message label to be children of text container
    textContainerClone.getChildren().add(messageBackgroundClone);
    textContainerClone.getChildren().add(messageLabelClone);

    // set the author and time to be children of message info panel
    messageInfoPanelClone.getChildren().add(authorClone);
    messageInfoPanelClone.getChildren().add(timeClone);

    // set the message label to be the text of the post
    messageLabelClone.setText(p.getMessage());

    // set the author to be the author of the post
    try {
      authorClone.setText(DBUtils.getNameFromActualID(p.getSender()));
    } catch (IOException e) {
      e.printStackTrace();
      authorClone.setText("ID# " + p.getSender());
    }

    // set the time to be the time of the post
    timeClone.setText(p.generateSimpleTime());

    // if i am not the sender, make the message background grey
    if (!p.getSender().equals(PersonalSettings.currentEmployee.getIDNumber())) {
      messageBackgroundClone.setFill(Color.rgb(200, 200, 200));
    }

    // set the height property of the rectangle to be the height of the message label
    messageBackgroundClone.heightProperty().bind(messageLabelClone.heightProperty());

    // set the height property of the text container pane to be the height of the message label
    textContainerClone.setPrefHeight(messageLabelClone.getHeight());

    return clone;
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
    Label bNameClone = getLabelClone(bName);
    VBox bTimeVboxClone = getVboxClone(bTimeVbox);
    Label bTimeClone = getLabelClone(bTime);
    VBox bNamePrevBoxClone = getVboxClone(bNamePrevBox);

    // set the rectangle and hbox to be children of the main pane
    clone.getChildren().add(bRectClone);
    clone.getChildren().add(bHboxClone);

    // set the pic pane, time vbox and name prev box to be children of the hbox
    bHboxClone.getChildren().add(bPicPaneClone);
    bHboxClone.getChildren().add(bNamePrevBoxClone);
    bHboxClone.getChildren().add(bTimeVboxClone);

    // set the circle and initials and unread to be children of the pic pane
    bPicPaneClone.getChildren().add(bCircleClone);
    bPicPaneClone.getChildren().add(bInitialsClone);
    bPicPaneClone.getChildren().add(bUnreadClone);

    // set the name prev vbox to have the messagePreview and name as children
    bNamePrevBoxClone.getChildren().add(bNameClone);
    bNamePrevBoxClone.getChildren().add(bPreviewClone);

    // set the time label to be the child of the time vbox
    bTimeVboxClone.getChildren().add(bTimeClone);

    // set the preview text to be the chat's preview
    bPreviewClone.setText(chat.getPosts().get(chat.getPosts().size() - 1).getMessage());

    // set the name text to be employee's name
    String csvNames = "Guest";
    try {
      csvNames = DBUtils.getNamesFromIds(chat.getUsers(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    bNameClone.setText(csvNames);

    // set the time text to be the time of the last message
    bTimeClone.setText(chat.getPosts().get(chat.getPosts().size() - 1).generateSimpleTime());

    // set the initials text to be the initials of the first name
    String initials = "G";
    String[] names = csvNames.split(",");
    if (names.length > 0) {
      initials = names[0].substring(0, 1);
    }

    bInitialsClone.setText(initials);

    // on click of the message, set the chatID to the chatID of the message
    bHboxClone.setOnMouseClicked(
        e -> {
          this.chatID = chatID;
          setChatOpen(true);
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
    clone.setPadding(p.getPadding());
    clone.setMinHeight(p.getMinHeight());
    clone.setMinWidth(p.getMinWidth());
    clone.setMaxHeight(p.getMaxHeight());
    clone.setMaxWidth(p.getMaxWidth());
    clone.setPrefHeight(p.getPrefHeight());
    clone.setPrefWidth(p.getPrefWidth());
    clone.setStyle(p.getStyle());
    clone.setOpacity(p.getOpacity());

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
    clone.setOpacity(r.getOpacity());
    clone.setTranslateX(r.getTranslateX());
    clone.setTranslateY(r.getTranslateY());
    clone.setLayoutX(r.getLayoutX());
    clone.setLayoutY(r.getLayoutY());
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
    clone.setTranslateX(h.getTranslateX());
    clone.setTranslateY(h.getTranslateY());

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
    clone.setPadding(l.getPadding());
    clone.setAlignment(l.getAlignment());
    clone.setTranslateX(l.getTranslateX());
    clone.setTranslateY(l.getTranslateY());
    clone.setWrapText(l.isWrapText());
    clone.setMaxHeight(l.getMaxHeight());
    clone.setMaxWidth(l.getMaxWidth());
    clone.setPrefHeight(l.getPrefHeight());
    clone.setPrefWidth(l.getPrefWidth());
    clone.setMinHeight(l.getMinHeight());
    clone.setMinWidth(l.getMinWidth());

    return clone;
  }

  private VBox getVboxClone(VBox vbox) {
    // clones the vbox vbox
    VBox clone = new VBox();
    clone.setPrefSize(vbox.getPrefWidth(), vbox.getPrefHeight());
    clone.setLayoutX(vbox.getLayoutX());
    clone.setLayoutY(vbox.getLayoutY());
    clone.setStyle(vbox.getStyle());
    clone.setAlignment(vbox.getAlignment());
    clone.setTranslateX(vbox.getTranslateX());
    clone.setTranslateY(vbox.getTranslateY());
    return clone;
  }

  ChildEventListener childEventListener =
      new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
          Platform.runLater(
              () -> {
                refreshChats();
                if (chatOpen) {
                  refreshMessages();
                }
              });
        }

        @Override
        public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
          Platform.runLater(
              () -> {
                refreshChats();
                if (chatOpen) {
                  refreshMessages();
                }
              });
        }

        @Override
        public void onChildRemoved(DataSnapshot snapshot) {
          Platform.runLater(
              () -> {
                refreshChats();
                if (chatOpen) {
                  refreshMessages();
                }
              });
        }

        @Override
        public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
          Platform.runLater(
              () -> {
                refreshChats();
                if (chatOpen) {
                  refreshMessages();
                }
              });
        }

        @Override
        public void onCancelled(DatabaseError error) {}
      };

  class EmployeeResult {
    private String name, role, id;
    private Pane resultPane;

    public EmployeeResult(String name, String role, String id) {
      this.name = name;
      this.role = role;
      this.id = id;
      this.resultPane = getResultClone(name, role, id);
    }

    public String getName() {
      return name;
    }

    public String getRole() {
      return role;
    }

    public String getId() {
      return id;
    }

    public Pane getResultPane() {
      return resultPane;
    }
  }
}