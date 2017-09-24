package com.tgelder.downhill.mesh;

import static org.junit.Assert.assertEquals;

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
  
  

}
