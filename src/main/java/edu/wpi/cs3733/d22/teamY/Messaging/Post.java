package edu.wpi.cs3733.d22.teamY.Messaging;

public class Post {

  public String author;
  public String message;
  public String time;
  public String recipients;

  public Post(String author, String time, String recipients, String message) {
    this.author = author;
    this.message = message;
    this.time = time;
    this.recipients = recipients;
  }

  public Post(String author, String recipients, String message) {
    this.author = author;
    this.message = message;
    this.time = generateTime();
    this.recipients = recipients;
  }

  //returns ms since epoch
  public String generateTime() {
    return System.currentTimeMillis() + "";
  }

  public Post() {}

  @Override
  public String toString() {
    return "Post{"
        + "author='"
        + author
        + '\''
        + ", message='"
        + message
        + '\''
        + ", time='"
        + time
        + '\''
        + ", recipients='"
        + recipients
        + '\''
        + '}';
  }

  public String getAuthor() {
    return author;
  }

  public String getMessage() {
    return message;
  }

  public String getTime() {
    return time;
  }

  public String getRecipients() {
    return recipients;
  }
}
