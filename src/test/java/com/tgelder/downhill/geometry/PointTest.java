package com.tgelder.downhill.geometry;

import org.junit.Test;

import com.tgelder.downhill.TestUtils;

import static org.junit.Assert.assertEquals;

public class PointTest {

  private Point a = new Point(101, 107, 113);
  private Point b = new Point(103, 109, 127);
  private Point r = new Point();

  @Test
  public void testAdd() {
    r.add(a, b);
    assertEquals(204, r.getX(), 0);
    assertEquals(216, r.getY(), 0);
    assertEquals(240, r.getZ(), 0);
  }

  @Test
  public void testAverage() {
    r.average(a, b);
    assertEquals(102, r.getX(), 0);
    assertEquals(108, r.getY(), 0);
    assertEquals(120, r.getZ(), 0);
  }

  @Test
  public void testCopy() {
    r.copy(a);
    assertEquals(101, r.getX(), 0);
    assertEquals(107, r.getY(), 0);
    assertEquals(113, r.getZ(), 0);
  }

  @Test
  public void testCross() {
    r.cross(a, b);
    assertEquals(1272, r.getX(), 0);
    assertEquals(-1188, r.getY(), 0);
    assertEquals(-12, r.getZ(), 0);
  }

  @Test
  public void testDistanceTo() {
    float d = a.distanceTo(b);
    assertEquals(14.282857, d, TestUtils.PRECISION);
  }

  @Test
  public void testDivide() {
    r.divide(a, 3);
    assertEquals(33.666667, r.getX(), TestUtils.PRECISION);
    assertEquals(35.666667, r.getY(), TestUtils.PRECISION);
    assertEquals(37.666667, r.getZ(), TestUtils.PRECISION);
  }

  @Test
  public void testDot() {
    float d = a.dot(b);
    assertEquals(36417, d, 0);
  }

  @Test
  public void testMagnitude() {
    float m = a.magnitude();
    assertEquals(185.523583, m, TestUtils.PRECISION);
  }

  @Test
  public void testMultiply() {
    r.multiply(a, 3);
    assertEquals(303, r.getX(), 0);
    assertEquals(321, r.getY(), 0);
    assertEquals(339, r.getZ(), 0);
  }

  @Test
  public void testNormalize() {
    r.copy(a).normalize();
    assertEquals(0.544405, r.getX(), TestUtils.PRECISION);
    assertEquals(0.576746, r.getY(), TestUtils.PRECISION);
    assertEquals(0.609087, r.getZ(), TestUtils.PRECISION);
  }

  @Test
  public void testSquareDistanceTo() {
    float d = a.squareDistanceTo(b);
    assertEquals(204, d, 0);
  }

  @Test
  public void testSet() {
    r.set(1, 2, 3);
    assertEquals(1, r.getX(), 0);
    assertEquals(2, r.getY(), 0);
    assertEquals(3, r.getZ(), 0);
  }

  @Test
  public void testSubtract() {
    r.subtract(a, b);
    assertEquals(-2, r.getX(), 0);
    assertEquals(-2, r.getY(), 0);
    assertEquals(-14, r.getZ(), 0);
  }

}
