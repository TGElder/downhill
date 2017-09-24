package com.tgelder.downhill.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.TestUtils;

public class TriangleAreaTest {

  @Test
  public void testArea() {
    Triangle triangle = new Triangle(
        new Point(1, 1, 3),
        new Point(2, -1, 5),
        new Point(-3, 3, 1));
    
    TriangleAreaCalculator calculator = new TriangleAreaCalculator();
    
    assertEquals(calculator.getArea(triangle), 4.24264, TestUtils.PRECISION); 
  }
  
 
}
