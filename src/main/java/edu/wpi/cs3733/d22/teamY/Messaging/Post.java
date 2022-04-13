package edu.wpi.cs3733.d22.teamY.Messaging;

public class Post {

  public String author;
  public String message;

  public Post(String author, String message) {
    this.author = author;
    this.message = message;
  }

  public Post() {}

  @Override
  public String toString() {
    return "Post{" + "author='" + author + '\'' + ", message='" + message + '\'' + '}';
  }

  public String getAuthor() {
    return author;
  }

  public String getMessage() {
    return message;
  }
}
