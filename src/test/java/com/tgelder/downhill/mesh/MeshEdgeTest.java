package com.tgelder.downhill.mesh;

import static org.junit.Assert.*;

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
  
  @Test
  public void testGetOtherEnd() {
    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);
    MeshEdge edge = new MeshEdge(a, b);
    
    assertEquals(b, edge.getOtherEnd(a));
    assertEquals(a, edge.getOtherEnd(b));
  }
  
  @Test
  public void testInBounds() {
    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);
    MeshEdge edge = new MeshEdge(a, b);
    assertTrue(edge.inBounds());    
  }
  
  @Test
  public void testOutOfBounds() {
    MeshPoint a = new MeshPoint(mesh, -1, 0);
    MeshPoint b = new MeshPoint(mesh, 0, 0);
    MeshEdge edge = new MeshEdge(a, b);
    assertFalse(edge.inBounds());    
    
    edge.set(2, 2, 3, 2);
    assertFalse(edge.inBounds());
    
    edge.set(3, 3, 4, 4);
    assertFalse(edge.inBounds());
  }

}
