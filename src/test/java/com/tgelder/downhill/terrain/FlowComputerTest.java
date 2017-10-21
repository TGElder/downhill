package com.tgelder.downhill.terrain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlowComputerTest {
  
  @Test
  public void testFlow() throws DownhillException {
    Mesh mesh = new Mesh(4);
    mesh.setZ(1);
    
    short[][] downhill = { { 1, 0, 3, 2 }, { 2, 1, 2, 2 }, { 4, 2, 2, 2 }, { 6, 6, 6, 6 } };
    
    int[][] actual = FlowComputer.getFlow(mesh, downhill);
    int[][] expected = { { 6, 1, 2, 1 }, { 1, 5, 2, 1 }, { 4, 3, 2, 1 }, { 5, 6, 7, 8 } };
    
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("Flow at " + x + ", " + y, expected[x][y], actual[x][y], 0);  
      }
    }
    
  }
  
  
}
