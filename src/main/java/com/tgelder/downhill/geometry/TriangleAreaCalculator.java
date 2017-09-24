package com.tgelder.downhill.geometry;

public class TriangleAreaCalculator {

  private final Point cross = new Point();

  private final Point ab = new Point();
  private final Point ac = new Point();

  public float getArea(Triangle triangle) {
    ab.subtract(triangle.getB(), triangle.getA());
    ac.subtract(triangle.getC(), triangle.getA());

    return cross.cross(ab, ac).magnitude() / 2f;
  }
  
}
