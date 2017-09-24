package com.tgelder.downhill.mesh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MeshPointTest {
  
  private final Mesh mesh = Mesh.of3x3();
  private final MeshPoint meshPoint = new MeshPoint(mesh);
  
  @Test
  public void test8Neighbours() {
    meshPoint.set(0, 0);
    assertEquals(MeshPoint.PointCase.EIGHT_NEIGHBOURS, meshPoint.getPointCase());
  }
  
  @Test
  public void test4Neighbours() {
    meshPoint.set(1, 0);
    assertEquals(MeshPoint.PointCase.FOUR_NEIGHBOURS, meshPoint.getPointCase());
  }
  
  @Test
  public void testEqual() {
    assertTrue(new MeshPoint(mesh, 0, 0).equals(new MeshPoint(mesh, 0, 0)));
  }
  
  @Test
  public void testNotEqual() {
    assertFalse(new MeshPoint(mesh, 0, 0).equals(new MeshPoint(mesh, 1, 0)));
  }
  
  @Test
  public void testAtEdge() {
    meshPoint.set(0, 1);
    assertTrue(meshPoint.atEdge());
  }
  
  @Test
  public void testNotAtEdge() {
    meshPoint.set(1, 1);
    assertFalse(meshPoint.atEdge());
  }
  
  @Test
  public void testInBounds() {
    meshPoint.set(0, 0);
    assertTrue(meshPoint.inBounds());
    meshPoint.set(2, 2);
    assertTrue(meshPoint.inBounds());
  }
  
  @Test
  public void testOutOfBounds() {
    meshPoint.set(-1, 0);
    assertFalse(meshPoint.inBounds());
    meshPoint.set(0, -1);
    assertFalse(meshPoint.inBounds());
    meshPoint.set(3, 2);
    assertFalse(meshPoint.inBounds());
    meshPoint.set(2, 3);
    assertFalse(meshPoint.inBounds());
  }


}
