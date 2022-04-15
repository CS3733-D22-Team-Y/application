package edu.wpi.cs3733.d22.teamY.Messaging;

import java.util.ArrayList;

public class ChatManager {

  private ChatManager() {}

  private static String chatName;
  private static ArrayList<Chat> chats;

  public static void addChat(Chat chat) {
    chats.add(chat);
  }
}
