package com.tgelder.downhill.geometry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {

  private float x;
  private float y;
  private float z;

  public Point() {
  }

  public Point set(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }

  public Point(float x, float y, float z) {
    set(x, y, z);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ", " + z + ")";
  }

  public float dot(Point other) {
    return x * other.x + y * other.y + z * other.z;
  }

  public Point cross(Point a, Point b) {
    return set(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
  }

  public Point add(Point a, Point b) {
    return set(a.x + b.x, a.y + b.y, a.z + b.z);
  }

  public Point subtract(Point a, Point b) {
    return set(a.x - b.x, a.y - b.y, a.z - b.z);
  }

  public Point multiply(Point a, float m) {
    return set(a.x * m, a.y * m, a.z * m);
  }

  public Point divide(Point a, float d) {
    return set(a.x / d, a.y / d, a.z / d);
  }

  public float magnitude() {
    return (float) Math.sqrt((x * x) + (y * y) + (z * z));
  }

  public Point normalize() {
    return divide(this, magnitude());
  }

  public Point average(Point a, Point b) {
    subtract(a, b);
    divide(this, 2);
    add(b, this);
    return this;
  }

  public float distanceTo(Point other) {
    return (float) Math.sqrt(squareDistanceTo(other));
  }

  public float squareDistanceTo(Point other) {
    return (float) (Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
  }

  public Point copy(Point other) {
    return set(other.x, other.y, other.z);
  }
  
}
