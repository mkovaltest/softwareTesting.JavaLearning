package ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2) {
    return Math.sqrt(square(p2.x - this.x) + square(p2.y - this.y));
  }

  public double square(double a) {
    double result = a * a;
    return result;
  }
}