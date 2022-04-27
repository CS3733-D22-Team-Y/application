package edu.wpi.cs3733.d22.teamY.controllers;

public class Camera {
  public static double xOff = 0;
  public static double yOff = 0;
  public static final int imgW = 7500;
  public static final int imgH = 5100;
  public static final int screenW = 930;
  public static final int screenH = 800;
  public static double scale = 1;
  public static float scaleNotches = 0;

  public static void changeScale(double scroll) {
    scaleNotches += scroll;
    scale = Math.pow(2, scaleNotches);

    if (Camera.scale > 5.0) Camera.setScale(Math.log(5) / Math.log(2));
    if (Camera.scale < .1) Camera.setScale(Math.log(.1) / Math.log(2));
  }

  public static void setScale(double notches) {
    scaleNotches = (float) notches;
    scale = Math.pow(2, scaleNotches);
  }

  public static void moveScreenCoords(double x, double y) {
    xOff += x;
    yOff += y;
  }

  public static void setScreenCoords(double x, double y) {
    xOff = x;
    yOff = y;
  }

  /**
   * Converts a point on the screen to an x,y coordinate on the image
   *
   * @param x the x coordinate of the point on the screen
   * @param y the y coordinate of the point on the screen
   * @return a Point object containing the x,y coordinate on the image
   */
  public static Point toMapCoords(double x, double y) {
    Point currScreenImageDimensions = getCurrScreenImageDimensions();
    System.out.println(currScreenImageDimensions);
    // xoff & yoff are how far the image has been translated in screen coords
    System.out.println(x + " " + y);
    double dx = (x - xOff - currScreenImageDimensions.x / 2);
    double dy = (y - yOff - currScreenImageDimensions.y / 2);
    // dx & dy are the screen distance from the center of the image to the point
    System.out.println("dx: " + dx + " dy: " + dy);
    // px and py is the screen distance from the center of the image

    double px = (currScreenImageDimensions.x / 2 + dx) / currScreenImageDimensions.x;
    double py = (currScreenImageDimensions.y / 2 + dy) / currScreenImageDimensions.y;

    return new Point(px * imgW, py * imgH);
  }

  public static Point getCurrScreenImageDimensions() {
    // if scaling is 1 then the image fills the width
    // height is filled by the aspect ratio of the image
    return new Point(screenW * scale, screenW * ((double) imgH / imgW) * scale);
  }
}
