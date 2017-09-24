package com.tgelder.downhill.mesh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.mesh.EdgeCase;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;

public class EdgeCaseTest {
  
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
    meshEdge.set(0, 1, 1, 0);
    assertEquals(EdgeCase.FORWARDSLASH, meshEdge.getEdgeCase());
  }
  
  @Test
  public void testBackSlash() {
    meshEdge.set(0, 0, 1, 1);
    assertEquals(EdgeCase.BACKSLASH, meshEdge.getEdgeCase());
  }

}
