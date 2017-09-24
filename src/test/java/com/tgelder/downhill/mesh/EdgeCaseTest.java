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
    assertEquals(meshEdge.getEdgeCase(), EdgeCase.POINT);
  }
  
  @Test
  public void testHorizontal() {
    meshEdge.set(0, 0, 1, 0);
    assertEquals(meshEdge.getEdgeCase(), EdgeCase.HORIZONTAL);
  }
  
  @Test
  public void testVertical() {
    meshEdge.set(0, 0, 0, 1);
    assertEquals(meshEdge.getEdgeCase(), EdgeCase.VERTICAL);
  }
  
  @Test
  public void testForwardSlash() {
    meshEdge.set(0, 1, 1, 0);
    assertEquals(meshEdge.getEdgeCase(), EdgeCase.FORWARDSLASH);
  }
  
  @Test
  public void testBackSlash() {
    meshEdge.set(0, 0, 1, 1);
    assertEquals(meshEdge.getEdgeCase(), EdgeCase.BACKSLASH);
  }

}
