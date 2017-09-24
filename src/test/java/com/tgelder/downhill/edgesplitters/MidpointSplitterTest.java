package com.tgelder.downhill.edgesplitters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tgelder.downhill.Mesh;
import com.tgelder.downhill.MeshEdge;
import com.tgelder.downhill.MeshPoint;
import com.tgelder.downhill.TestUtils;

public class MidpointSplitterTest {
    
  private Mesh mesh;
  private MidpointEdgeSplitter splitter;

  @Before
  public void setUp() {
    mesh = Mesh.of3x3();
  }

  @Test
  public void testSplitX() {
    splitter = new MidpointEdgeSplitter(MeshPoint::getX);
    
    float min = Mesh.MIN_VALUE;
    float max = Mesh.MIN_VALUE/2 + Mesh.MAX_VALUE/2;

    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);

    MeshEdge edge = new MeshEdge(a, b);

    assertEquals(splitter.split(edge), min/2 + max/2, TestUtils.PRECISION);
  }
  
  @Test
  public void testSplitY() {
    splitter = new MidpointEdgeSplitter(MeshPoint::getY);
    
    float min = Mesh.MIN_VALUE;
    float max = Mesh.MIN_VALUE/2 + Mesh.MAX_VALUE/2;

    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);

    MeshEdge edge = new MeshEdge(a, b);

    assertEquals(splitter.split(edge), min/2 + max/2, TestUtils.PRECISION);
  }
  
  @Test
  public void testSplitZ() {
    splitter = new MidpointEdgeSplitter(MeshPoint::getZ);

    float min = Mesh.MIN_VALUE;
    float max = Mesh.MAX_VALUE;

    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);

    MeshEdge edge = new MeshEdge(a, b);

    assertEquals(splitter.split(edge), min/2 + max/2, TestUtils.PRECISION);
  }
  
}
