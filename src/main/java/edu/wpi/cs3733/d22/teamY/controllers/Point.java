package edu.wpi.cs3733.d22.teamY.controllers;

class Point {
  double x, y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public void add(Point p) {
    this.x += p.x;
    this.y += p.y;
  }
}
