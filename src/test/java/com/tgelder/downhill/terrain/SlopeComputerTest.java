package com.tgelder.downhill.terrain;

import com.tgelder.downhill.TestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SlopeComputerTest {


  @Test
  public void testRunOf1() throws DownhillException {
    Mesh mesh = new Mesh(2);
    double[][] values = {{2, 1}, {0, 1}};
    mesh.setZ(values);

    double[][] actual = SlopeComputer.getSlope(mesh);

    assertEquals(1.10715, actual[0][0], TestUtils.PRECISION);
  }


  @Test
  public void testRunOfSqrt2() throws DownhillException {
    Mesh mesh = new Mesh(2);
    double[][] values = {{2, 1}, {1, 0}};
    mesh.setZ(values);

    double[][] actual = SlopeComputer.getSlope(mesh);

    assertEquals(0.95532, actual[0][0], TestUtils.PRECISION);
  }


  @Test
  public void testRiseOfZero() throws DownhillException {
    Mesh mesh = new Mesh(2);
    double[][] values = {{0, 0}, {0, 0}};
    mesh.setZ(values);

    double[][] actual = SlopeComputer.getSlope(mesh);

    assertEquals(0, actual[0][0], TestUtils.PRECISION);
  }

  @Test
  public void testEntireMesh() throws DownhillException {
    Mesh mesh = new Mesh(3);
    double[][] values = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    mesh.setZ(values);

    double[][] actual = SlopeComputer.getSlope(mesh);
    double[][] expected = {{0.61548, 0.78540, 0.61548}, {0.78540, 0.78540, 0.78540}, {0.61548, 0.78540, 0.61548}};

    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        assertEquals("Slope at " + x + ", " + y, expected[x][y], actual[x][y], TestUtils.PRECISION);
      }
    }

  }

}
