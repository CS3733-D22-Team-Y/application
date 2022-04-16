package edu.wpi.cs3733.d22.teamY.Messaging;

public class Post {

  public String message;
  public String time;

  public Post(String message) {
    this.message = message;
    this.time = generateTime();
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
}
