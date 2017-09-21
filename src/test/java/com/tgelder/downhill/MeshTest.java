package com.tgelder.downhill;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.edgesplitters.EdgeSplitter;
import com.tgelder.downhill.edgesplitters.MidpointEdgeSplitter;
import com.tgelder.downhill.edgesplitters.RandomEdgeSplitter;

public class MeshTest {
  
  private static final float PRECISION = 0.00001f;
  private float min = Mesh.MIN_VALUE;
  private float mid = Mesh.MIN_VALUE/2 + Mesh.MAX_VALUE/2;
  private float max = Mesh.MAX_VALUE;
  
  
  @Test
  public void test3x3Vertices() {
    Mesh mesh = Mesh.of3x3();
    
    assertEquals(mesh.getX(0, 0), min, PRECISION);
    assertEquals(mesh.getX(1, 0), mid, PRECISION);
    assertEquals(mesh.getX(2, 0), max, PRECISION);
    assertEquals(mesh.getX(0, 1), min, PRECISION);
    assertEquals(mesh.getX(1, 1), mid, PRECISION);
    assertEquals(mesh.getX(2, 1), max, PRECISION);
    assertEquals(mesh.getX(0, 2), min, PRECISION);
    assertEquals(mesh.getX(1, 2), mid, PRECISION);
    assertEquals(mesh.getX(2, 2), max, PRECISION);
    
    assertEquals(mesh.getY(0, 0), min, PRECISION);
    assertEquals(mesh.getY(1, 0), min, PRECISION);
    assertEquals(mesh.getY(2, 0), min, PRECISION);
    assertEquals(mesh.getY(0, 1), mid, PRECISION);
    assertEquals(mesh.getY(1, 1), mid, PRECISION);
    assertEquals(mesh.getY(2, 1), mid, PRECISION);
    assertEquals(mesh.getY(0, 2), max, PRECISION);
    assertEquals(mesh.getY(1, 2), max, PRECISION);
    assertEquals(mesh.getY(2, 2), max, PRECISION);
    
    assertEquals(mesh.getZ(0, 0), min, PRECISION);
    assertEquals(mesh.getZ(1, 0), min, PRECISION);
    assertEquals(mesh.getZ(2, 0), min, PRECISION);
    assertEquals(mesh.getZ(0, 1), min, PRECISION);
    assertEquals(mesh.getZ(1, 1), max, PRECISION);
    assertEquals(mesh.getZ(2, 1), min, PRECISION);
    assertEquals(mesh.getZ(0, 2), min, PRECISION);
    assertEquals(mesh.getZ(1, 2), min, PRECISION);
    assertEquals(mesh.getZ(2, 2), min, PRECISION);
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
    EdgeSplitter zSplitter = new RandomEdgeSplitter(new MockRNG(numbers), MeshPoint::getZ);
    
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
      
      assertEquals(expected[i], point.getZ(), PRECISION);
      
      i++;
    }
   
    
  }

}
