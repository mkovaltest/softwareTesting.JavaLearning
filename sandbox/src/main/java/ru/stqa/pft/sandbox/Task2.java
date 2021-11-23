package ru.stqa.pft.sandbox;

public class Task2 {
  public static void main(String[] args) {
    System.out.println("Функция:");
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 3);
    System.out.println("Расстояние между точкой p1 с координатами " + p1.x + "," + p1.y + " и точкой p2 с координатами " + p2.x + "," + p2.y + " = " + distance(p1, p2));
    System.out.println("Метод:");
    Point p3 = new Point(4, 7);
    Point p4 = new Point(-2, 0);
    System.out.println("Расстояние между точкой p3 с координатами " + p3.x + "," + p3.y + " и точкой p4 с координатами " + p4.x + "," + p4.y + " = " + p3.distance(p3, p4));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(square(p2.x - p1.x) + square(p2.y - p1.y));
  }

  public static double square(double a) {
    double result = a * a;
    return result;
  }
}