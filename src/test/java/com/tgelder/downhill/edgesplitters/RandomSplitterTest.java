package com.tgelder.downhill.edgesplitters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tgelder.downhill.TestUtils;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.rngs.MockRNG;
import com.tgelder.downhill.rngs.RNG;

public class RandomSplitterTest {

  private Mesh mesh;
  private RandomEdgeSplitter splitter;
  private static float[] numbers = { 0.4f };
  private RNG rng;
  
  @Before
  public void setUp() {
    mesh = Mesh.of3x3();
    rng = new MockRNG(numbers);
  }

  @Test
  public void testSplitX() {
    splitter = new RandomEdgeSplitter(rng, MeshPoint::getX);
    
    float min = Mesh.MIN_VALUE;
    float max = Mesh.MIN_VALUE/2 + Mesh.MAX_VALUE/2;

    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);

    MeshEdge edge = new MeshEdge(a, b);

    assertEquals(splitter.split(edge), min * 0.4f + max * (1 - 0.4f), TestUtils.PRECISION);
  }
  
  @Test
  public void testSplitY() {
    splitter = new RandomEdgeSplitter(rng, MeshPoint::getY);
    
    float min = Mesh.MIN_VALUE;
    float max = Mesh.MIN_VALUE/2 + Mesh.MAX_VALUE/2;

    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);

    MeshEdge edge = new MeshEdge(a, b);

    assertEquals(splitter.split(edge), min * 0.4f + max * (1 - 0.4f), TestUtils.PRECISION);
  }
  
  @Test
  public void testSplitZ() {
    splitter = new RandomEdgeSplitter(rng, MeshPoint::getZ);
    
    float min = Mesh.MIN_VALUE;
    float max = Mesh.MAX_VALUE;

    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 1);

    MeshEdge edge = new MeshEdge(a, b);

    assertEquals(splitter.split(edge), min * 0.4f + max * (1 - 0.4f), TestUtils.PRECISION);
  }

}
