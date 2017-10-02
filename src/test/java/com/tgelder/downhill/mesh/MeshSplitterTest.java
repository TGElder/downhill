package com.tgelder.downhill.mesh;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tgelder.downhill.TestUtils;
import com.tgelder.downhill.rngs.MockRNG;

public class MeshSplitterTest {
  
  private Mesh mesh;
  
  @Before
  public void setUp() {
    mesh = new Mesh(2); 
    
    double[][] values = {{60, 45}, {15, 21}};
    
    VertexIterator iterator = new VertexIterator(mesh);
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
      int x = vertex.getX();
      int y = vertex.getY();
      mesh.setZ(x, y, values[x][y]);
    }
  }
  
  @Test
  public void testMaxValues() {
    MeshSplitter splitter = new MeshSplitter(1, 1);
    
    double[] fixedRandom = {1};
    Mesh split = splitter.split(mesh, new MockRNG(fixedRandom));
    
    double[][] actual = new double[4][4];
    
    VertexIterator iterator = new VertexIterator(split);
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
      int x = vertex.getX();
      int y = vertex.getY();
      actual[x][y] = vertex.getZ();
    }
    
    double[][] expected = {{60, 60, 45, 45}, {60, 60, 45, 45}, {15, 15, 21, 21}, {15, 15, 21, 21}};

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals(expected[x][y], actual[x][y], 0);  
      }
    }
    
  }
  
  @Test
  public void testMinValues() {
    MeshSplitter splitter = new MeshSplitter(0, 0);
    
    double[] fixedRandom = {1};
    Mesh split = splitter.split(mesh, new MockRNG(fixedRandom));
    
    double[][] actual = new double[4][4];
    
    VertexIterator iterator = new VertexIterator(split);
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
      int x = vertex.getX();
      int y = vertex.getY();
      actual[x][y] = vertex.getZ();
    }
    
    double[][] expected = {{0, 0, 0, 0}, {0, 15, 21, 0}, {0, 15, 15, 0}, {0, 0, 0, 0}};

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals(expected[x][y], actual[x][y], 0);  
      }
    }
    
  }
  
}
