package edu.wpi.cs3733.d22.teamY.Messaging;

import java.util.ArrayList;

public class Chat {
  ArrayList<Post> posts;
  ArrayList<String> users;

  public Chat() {
    posts = new ArrayList<Post>();
    users = new ArrayList<String>();
  }

  public Chat(String sender, String... users) {
    this.users = new ArrayList<String>();
    for (String u : users) {
      this.users.add(u);
    }
    this.users.add(sender);
    posts = new ArrayList<Post>();
  }

  public static String getChatID(String myID, ArrayList<String> ids) {
    String chatID = "";
    ids.sort(String::compareTo);
    for (String id : ids) {
      chatID += id + ":";
    }
    // remove the last ":"
    chatID = chatID.substring(0, chatID.length() - 1);
    return chatID;
  }

  public void addPost(Post p) {
    posts.add(p);
  }

  public void addUser(String u) {
    users.add(u);
  }

  public ArrayList<Post> getPosts() {
    return posts;
  }

  public ArrayList<String> getUsers() {
    return users;
  }

  public void setPosts(ArrayList<Post> p) {
    posts = p;
  }

  public void setUsers(ArrayList<String> u) {
    users = u;
  }

  public String toString() {
    String res = "";
    res += users.size() + " members\n";
    for (Post p : posts) {
      res += p.toString() + "\n";
    }
    return res;
  }

  public static String getChatID(String myID, String... recipientIDs) {
    String chatID = "";
    ArrayList<String> ids = new ArrayList<>();
    for (String id : recipientIDs) {
      ids.add(id);
    }
    ids.add(myID);
    // sort the ids in alphabetical order
    ids.sort(String::compareTo);
    for (String id : ids) {
      chatID += id + ":";
    }
    // remove the last ":"
    chatID = chatID.substring(0, chatID.length() - 1);
    return chatID;
  }
}
