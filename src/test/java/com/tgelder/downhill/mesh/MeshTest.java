package com.tgelder.downhill.mesh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.TestUtils;
import com.tgelder.downhill.edgesplitters.EdgeSplitter;
import com.tgelder.downhill.edgesplitters.MidpointEdgeSplitter;
import com.tgelder.downhill.edgesplitters.RandomEdgeSplitter;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.mesh.iterators.PointIterator;
import com.tgelder.downhill.rngs.MockRNG;

public class MeshTest {
  
  private float min = Mesh.MIN_VALUE;
  private float mid = Mesh.MIN_VALUE/2 + Mesh.MAX_VALUE/2;
  private float max = Mesh.MAX_VALUE;
  
  
  @Test
  public void test3x3Vertices() {
    Mesh mesh = Mesh.of3x3();
    
    assertEquals(min, mesh.getX(0, 0), TestUtils.PRECISION);
    assertEquals(mid, mesh.getX(1, 0), TestUtils.PRECISION);
    assertEquals(max, mesh.getX(2, 0), TestUtils.PRECISION);
    assertEquals(min, mesh.getX(0, 1), TestUtils.PRECISION);
    assertEquals(mid, mesh.getX(1, 1), TestUtils.PRECISION);
    assertEquals(max, mesh.getX(2, 1), TestUtils.PRECISION);
    assertEquals(min, mesh.getX(0, 2), TestUtils.PRECISION);
    assertEquals(mid, mesh.getX(1, 2), TestUtils.PRECISION);
    assertEquals(max, mesh.getX(2, 2), TestUtils.PRECISION);
    
    assertEquals(min, mesh.getY(0, 0), TestUtils.PRECISION);
    assertEquals(min, mesh.getY(1, 0), TestUtils.PRECISION);
    assertEquals(min, mesh.getY(2, 0), TestUtils.PRECISION);
    assertEquals(mid, mesh.getY(0, 1), TestUtils.PRECISION);
    assertEquals(mid, mesh.getY(1, 1), TestUtils.PRECISION);
    assertEquals(mid, mesh.getY(2, 1), TestUtils.PRECISION);
    assertEquals(max, mesh.getY(0, 2), TestUtils.PRECISION);
    assertEquals(max, mesh.getY(1, 2), TestUtils.PRECISION);
    assertEquals(max, mesh.getY(2, 2), TestUtils.PRECISION);
    
    assertEquals(min, mesh.getZ(0, 0), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(1, 0), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(2, 0), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(0, 1), TestUtils.PRECISION);
    assertEquals(max, mesh.getZ(1, 1), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(2, 1), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(0, 2), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(1, 2), TestUtils.PRECISION);
    assertEquals(min, mesh.getZ(2, 2), TestUtils.PRECISION);
  }
  
  @Test
  public void test3x3Split() {
    
    float[] numbers = new float[25];
    
    numbers[6]  = 0.0f;
    numbers[7]  = 0.1f;
    numbers[8]  = 0.2f;
    numbers[11] = 0.3f;
    numbers[12] = 0.4f;
    numbers[13] = 0.5f;
    numbers[16] = 0.6f;
    numbers[17] = 0.7f;
    numbers[18] = 1.0f;
    
    EdgeSplitter xSplitter = new MidpointEdgeSplitter(MeshPoint::getX);
    EdgeSplitter ySplitter = new MidpointEdgeSplitter(MeshPoint::getY);
    EdgeSplitter zSplitter = new RandomEdgeSplitter(new MockRNG(numbers), MeshPoint::getZ, 0f, 1f);
    
    Mesh split = Mesh.of3x3().split(xSplitter, ySplitter, zSplitter);
    
    float[] expected = new float[25];
    
    for (int i=0; i<25; i++) {
      expected[i] = min;
    }
    
    expected[6] = max;
    expected[7] = 0.1f*min + (1 - 0.1f)*max;
    expected[8] = 0.2f*max + (1 - 0.2f)*min;
    expected[11] = 0.3f*min + (1 - 0.3f)*max;
    expected[12] = max;
    expected[13] = 0.5f*min + (1 - 0.5f)*max;
    expected[16] = 0.6f*min + (1 - 0.6f)*max;
    expected[17] = 0.7f*max + (1 - 0.7f)*min;
    expected[18] = max;
    
    PointIterator iterator = new PointIterator(split);
    
    int i = 0;
    
    while(iterator.hasNext()) {
      MeshPoint point = iterator.next();
      
      assertEquals(point.getZ(), expected[i], TestUtils.PRECISION);
      
      i++;
    }
   
    
  }

}
