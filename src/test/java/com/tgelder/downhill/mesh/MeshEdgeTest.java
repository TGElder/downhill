package com.tgelder.downhill.mesh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.mesh.EdgeCase;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.iterators.EdgeIterator;

public class MeshEdgeTest {
  
  private final Mesh mesh = Mesh.of3x3();
  private final MeshEdge meshEdge = new MeshEdge(mesh);
  
  @Test
  public void testPoint() {
    meshEdge.set(0, 0, 0, 0);
    assertEquals(EdgeCase.POINT, meshEdge.getEdgeCase());
  }
  
  @Test
  public void testHorizontal() {
    meshEdge.set(0, 0, 1, 0);
    assertEquals(EdgeCase.HORIZONTAL, meshEdge.getEdgeCase());
  }
  
  @Test
  public void testVertical() {
    meshEdge.set(0, 0, 0, 1);
    assertEquals(EdgeCase.VERTICAL, meshEdge.getEdgeCase());
  }
  
  @Test
  public void testForwardSlash() {
    meshEdge.set(0, 2, 1, 1);
    assertEquals(EdgeCase.FORWARDSLASH, meshEdge.getEdgeCase());
  }
  
  @Test
  public void testBackSlash() {
    meshEdge.set(0, 0, 1, 1);
    assertEquals(EdgeCase.BACKSLASH, meshEdge.getEdgeCase());
  }
  
  @Test
  public void testGetEdgeCoordinates() {
    
    EdgeIterator iterator = new EdgeIterator(new Mesh(9));
    
    int i=0;
    int [] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
            
      assertEquals(expected[i % 17], edge.getEdgeX());
      assertEquals(expected[i / 17], edge.getEdgeY());
      
      i++;
    }
  }

}
