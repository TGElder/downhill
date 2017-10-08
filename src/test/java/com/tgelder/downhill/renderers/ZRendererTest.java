package com.tgelder.downhill.renderers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tgelder.downhill.image.MockImage;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.renderer.ZRenderer;

public class ZRendererTest {
  
  
  private Mesh mesh;
  
  @Before
  public void setUp() {
    mesh = new Mesh(4); 
    double[][] values = { { 53, 11, 13, 38 }, { 8, 50, 28, 33 }, { 10, 15, 18, 10 }, { 7, 13, 17, 16 } };
    mesh.setZ(values);
  }
  
  @Test
  public void testZRenderer() {
    
    MockImage image = new MockImage(4);
    ZRenderer renderer = new ZRenderer();
    renderer.render(mesh, 0, mesh.getMaxZ(), image);
    
    int[][] expected = { { 255, 52, 62, 182 }, { 38, 240, 134, 158 }, { 48, 72, 86, 48 }, { 33, 62, 81, 76 } };

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("R " + x + ", " + y, expected[x][y], image.getR(x, y), 0);
        assertEquals("G " + x + ", " + y, expected[x][y], image.getG(x, y), 0);
        assertEquals("B " + x + ", " + y, expected[x][y], image.getB(x, y), 0);
      }
    }
    
  }
  
  @Test
  public void testSeaLevel() {
    
    ZRenderer renderer = new ZRenderer();
    MockImage image = new MockImage(4);
    
    renderer.render(mesh, 15, mesh.getMaxZ(), image);
    
    int[][] expectedRG = { { 255, 0, 0, 154 }, { 0, 234, 87, 120 }, { 0, 0, 20, 0 }, { 0, 0, 13, 6 } };
    int[][] expectedB = { { 255, 255, 255, 154 }, { 255, 234, 87, 120 }, { 255, 0, 20, 255 }, { 255, 255, 13, 6 } };

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("R " + x + ", " + y, expectedRG[x][y], image.getR(x, y), 0);
        assertEquals("G " + x + ", " + y, expectedRG[x][y], image.getG(x, y), 0);
        assertEquals("B " + x + ", " + y, expectedB[x][y], image.getB(x, y), 0);
      }
    }
    
  }

}
