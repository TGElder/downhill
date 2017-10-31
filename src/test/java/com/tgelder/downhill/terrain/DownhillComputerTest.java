package com.tgelder.downhill.terrain;

import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DownhillComputerTest {
 
  
  @Test
  public void testDownhill() throws DownhillException {
    Mesh mesh = new Mesh(4); 
    double[][] values = { { 53, 11, 13, 38 }, { 8, 50, 28, 33 }, { 10, 15, 18, 10 }, { 7, 13, 17, 16 } };
    mesh.setZ(values);
    
    short[][] actual = DownhillComputer.getDownhill(mesh);
    short[][] expected = { { 0, 0, 0, 0 }, { 1, 2, 5, 5 }, { 1, 3, 6, 5 }, { 1, 3, 3, 3 } };
    
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("Downhill at " + x + ", " + y, expected[x][y], actual[x][y], 0);  
      }
    }
    
  }
  
  @Test
  public void stressTest() throws DownhillException {
    Mesh mesh = new Mesh(1);
    mesh.setZ(Mesh.MAX_VALUE);
    
    MeshSplitter splitter = new MeshSplitter(0.1, 0.9);
    RNG rng = new RandomRNG(1986);

    for (int i = 0; i < 12; i++) {
      mesh = splitter.split(mesh, rng);
      DownhillComputer.getDownhill(mesh);
    }
   
  }
  
  
}
