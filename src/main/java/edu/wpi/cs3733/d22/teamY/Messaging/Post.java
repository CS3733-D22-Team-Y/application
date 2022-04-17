package edu.wpi.cs3733.d22.teamY.Messaging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

  public String message;
  public String time;
  public String sender;

  public Post(String message, String sender) {
    this.message = message;
    this.time = generateTime();
    this.sender = sender;
  }

  public Post() {}

  // returns ms since epoch
  public String generateTime() {
    return System.currentTimeMillis() + "";
  }

  @Override
  public String toString() {
    return "Post{" + "message='" + message + '\'' + ", time='" + time + '\'' + '}';
  }

  public String getMessage() {
    return message;
  }

  public String getTime() {
    return time;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String generateSimpleTime() {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    Date resultdate = new Date(Long.parseLong(this.getTime()));
    return sdf.format(resultdate);
  }
}
