package com.tgelder.downhill.renderers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.image.MockImage;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.renderer.DownhillRenderer;

public class DownhillRendererTest {

  @Test
  public void testDownhillRenderer() {
    
    Mesh mesh = new Mesh(4);
    
    short[][] downhill = { { 0, 0, 1, 1 }, { 2, 2, 3, 3 }, { 4, 4, 5, 5 }, { 6, 6, 7, 7 } };
    
    int[][] expected = { { 127, 127, 63, 63 }, { 0, 0, 63, 63 }, { 127, 127, 191, 191 }, { 255, 255, 191, 191 } }; 
   
    MockImage image = new MockImage(4);
    DownhillRenderer renderer = new DownhillRenderer();
    renderer.render(mesh, downhill, image);
    
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("R " + x + ", " + y, expected[x][y], image.getR(x, y), 0);
        assertEquals("G " + x + ", " + y, expected[x][y], image.getG(x, y), 0);
        assertEquals("B " + x + ", " + y, expected[x][y], image.getB(x, y), 0);
      }
    }
    
  }
  
}
