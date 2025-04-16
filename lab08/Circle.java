/**
 * Circle.java
 * Emma Jaskowiec / Thurs 4:30 (Lab 02B)
 *
 * EXTRA CREDIT INCLUDED
 * 
 * Represents a circle with a radius and a color.
 */

public class Circle {
  final private static double PI = 3.14159;

  private double radius;
  private String color;

  public Circle() {
    this.setRadius(1.0);
    this.setColor("purple");
  }

  public Circle(double radius, String color) {
    this.setRadius(radius);
    this.setColor(color);
  }

  public double getRadius() {
    return this.radius;
  }

  public String getColor() {
    return this.color;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public void setRadius(String radius) throws NumberFormatException {
    this.radius = Double.parseDouble(radius);
  }

  public void setColor(String color) {
    this.color = color;
  }

  private double findArea() {
    return this.radius * this.radius * PI;
  }

  public boolean equals(Circle another) {
    return this.radius == another.radius && this.color.equalsIgnoreCase(another.color);
  }

  @Override
  public String toString() {
    return String.format("Color: %s\nRadius: %.1f\nArea: %.1f", this.color, this.radius, this.findArea());
  }
}
