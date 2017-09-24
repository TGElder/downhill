package com.tgelder.downhill.geometry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;

public class TriangleZInterpolatorTest {

  private Point a = new Point(0,0,0);
  private Point b = new Point(2,2,1);
  private Point c = new Point(4,1,2);
  
  private Triangle t = new Triangle(a,b,c);
  
  private TriangleZInterpolator interpolator = new TriangleZInterpolator();
  
  @Before
  public void setUp() {
    interpolator.setTriangle(t);    
  }
  
  @Test
  public void testCornerInterpolations() {
    assertEquals(0, interpolator.getZ(0, 0), 0);
    assertEquals(1, interpolator.getZ(2, 2), 0);
    assertEquals(2, interpolator.getZ(4, 1), 0);
  }
  
  @Test
  public void testMidpointInterpolations() {
    assertEquals(0.5f, interpolator.getZ(1, 1),  0);
    assertEquals(1.5f, interpolator.getZ(3, 1.5f), 0);
    assertEquals(1, interpolator.getZ(2, 0.5f), 0);
  }
  
  @Test
  public void testInsideInterpolations() {
    assertEquals(1.5f, interpolator.getZ(3, 1), 0);
  }
  
  @Test
  public void testCornerPointsInside() {
    assertTrue(interpolator.pointIsInTriangle(0, 0));
    assertTrue(interpolator.pointIsInTriangle(2, 2));
    assertTrue(interpolator.pointIsInTriangle(4, 1));
  }
  
  @Test
  public void testMidpointPointsInside() {
    assertTrue(interpolator.pointIsInTriangle(1, 1));
    assertTrue(interpolator.pointIsInTriangle(3, 1.5f));
    assertTrue(interpolator.pointIsInTriangle(2, 0.5f));
  }
  
  @Test
  public void testInsidePointInside() {
    assertTrue(interpolator.pointIsInTriangle(3, 1));
    assertTrue(interpolator.pointIsInTriangle(2, 1));
  }
  
  
  @Test
  public void testOutsidePointsOutside() {
    assertFalse(interpolator.pointIsInTriangle(2, 0));
    assertFalse(interpolator.pointIsInTriangle(3, 2));
    assertFalse(interpolator.pointIsInTriangle(0, 1));
  }

}
