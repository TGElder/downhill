package com.tgelder.downhill.mesh.iterators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;

public class PointEdgeIteratorTest {
  
  private final Mesh mesh = new Mesh(5);
  private final MeshPoint point = new MeshPoint(mesh);
  
  @Test
  public void testEightNeighbours() {
    point.set(1, 1);
    
    int [] expectedAX = {1, 0, 1, 1, 0, 1, 0, 1};
    int [] expectedAY = {0, 1, 1, 1, 0, 1, 2, 1};
    int [] expectedBX = {1, 1, 2, 1, 1, 2, 1, 2};
    int [] expectedBY = {1, 1, 1, 2, 1, 0, 1, 2};
    int [] expectedEdgeX = {2, 1, 3, 2, 1, 3, 1, 3};
    int [] expectedEdgeY = {1, 2, 2, 3, 1, 1, 3, 3};
    
    PointEdgeIterator iterator = new PointEdgeIterator(mesh);
    iterator.setPoint(point);
    
    int [] actualAX = new int[8];
    int [] actualAY = new int[8];
    int [] actualBX = new int[8];
    int [] actualBY = new int[8];
    int [] actualEdgeX = new int[8];
    int [] actualEdgeY = new int[8];
    
    int a = 0;
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
      actualAX[a] = edge.getA().getMx();
      actualAY[a] = edge.getA().getMy();
      actualBX[a] = edge.getB().getMx();
      actualBY[a] = edge.getB().getMy();
      actualEdgeX[a] = edge.getEdgeX();
      actualEdgeY[a] = edge.getEdgeY();
      a++;
    }
    
    for (int i = 0; i < 8; i++) {
      assertEquals(expectedAX[i], actualAX[i]);
      assertEquals(expectedAY[i], actualAY[i]);
      assertEquals(expectedBX[i], actualBX[i]);
      assertEquals(expectedBY[i], actualBY[i]);
      assertEquals(expectedEdgeX[i], actualEdgeX[i]);
      assertEquals(expectedEdgeY[i], actualEdgeY[i]);
    }
  }
  
  @Test
  public void testFourNeighbours() {
    point.set(2, 1);
    
    int [] expectedAX = {2, 1, 2, 2};
    int [] expectedAY = {0, 1, 1, 1};
    int [] expectedBX = {2, 2, 3, 2};
    int [] expectedBY = {1, 1, 1, 2};
    int [] expectedEdgeX = {4, 3, 5, 4};
    int [] expectedEdgeY = {1, 2, 2, 3};
    
    PointEdgeIterator iterator = new PointEdgeIterator(mesh);
    iterator.setPoint(point);
    
    int [] actualAX = new int[4];
    int [] actualAY = new int[4];
    int [] actualBX = new int[4];
    int [] actualBY = new int[4];
    int [] actualEdgeX = new int[4];
    int [] actualEdgeY = new int[4];
    
    int a = 0;
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
      actualAX[a] = edge.getA().getMx();
      actualAY[a] = edge.getA().getMy();
      actualBX[a] = edge.getB().getMx();
      actualBY[a] = edge.getB().getMy();
      actualEdgeX[a] = edge.getEdgeX();
      actualEdgeY[a] = edge.getEdgeY();
      a++;
    }
    
    for (int i = 0; i < 4; i++) {
      assertEquals(expectedAX[i], actualAX[i]);
      assertEquals(expectedAY[i], actualAY[i]);
      assertEquals(expectedBX[i], actualBX[i]);
      assertEquals(expectedBY[i], actualBY[i]);
      assertEquals(expectedEdgeX[i], actualEdgeX[i]);
      assertEquals(expectedEdgeY[i], actualEdgeY[i]);
    }
  }
  
  @Test
  public void testTopLeftCorner() {
    point.set(0, 0);
    
    int [] expectedAX = {0, 0, 0};
    int [] expectedAY = {0, 0, 0};
    int [] expectedBX = {1, 0, 1};
    int [] expectedBY = {0, 1, 1};
    int [] expectedEdgeX = {1, 0, 1};
    int [] expectedEdgeY = {0, 1, 1};
    
    PointEdgeIterator iterator = new PointEdgeIterator(mesh);
    iterator.setPoint(point);
    
    int [] actualAX = new int[3];
    int [] actualAY = new int[3];
    int [] actualBX = new int[3];
    int [] actualBY = new int[3];
    int [] actualEdgeX = new int[3];
    int [] actualEdgeY = new int[3];
    
    int a = 0;
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
      actualAX[a] = edge.getA().getMx();
      actualAY[a] = edge.getA().getMy();
      actualBX[a] = edge.getB().getMx();
      actualBY[a] = edge.getB().getMy();
      actualEdgeX[a] = edge.getEdgeX();
      actualEdgeY[a] = edge.getEdgeY();
      a++;
    }
    
    for (int i = 0; i < 3; i++) {
      assertEquals(expectedAX[i], actualAX[i]);
      assertEquals(expectedAY[i], actualAY[i]);
      assertEquals(expectedBX[i], actualBX[i]);
      assertEquals(expectedBY[i], actualBY[i]);
      assertEquals(expectedEdgeX[i], actualEdgeX[i]);
      assertEquals(expectedEdgeY[i], actualEdgeY[i]);
    }
  }
  
  @Test
  public void testBottomRightCorner() {
    point.set(4, 4);
    
    int [] expectedAX = {4, 3, 3};
    int [] expectedAY = {3, 4, 3};
    int [] expectedBX = {4, 4, 4};
    int [] expectedBY = {4, 4, 4};
    int [] expectedEdgeX = {8, 7, 7};
    int [] expectedEdgeY = {7, 8, 7};
    
    PointEdgeIterator iterator = new PointEdgeIterator(mesh);
    iterator.setPoint(point);
    
    int [] actualAX = new int[3];
    int [] actualAY = new int[3];
    int [] actualBX = new int[3];
    int [] actualBY = new int[3];
    int [] actualEdgeX = new int[3];
    int [] actualEdgeY = new int[3];
    
    int a = 0;
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
      actualAX[a] = edge.getA().getMx();
      actualAY[a] = edge.getA().getMy();
      actualBX[a] = edge.getB().getMx();
      actualBY[a] = edge.getB().getMy();
      actualEdgeX[a] = edge.getEdgeX();
      actualEdgeY[a] = edge.getEdgeY();
      a++;
    }
    
    for (int i = 0; i < 3; i++) {
      assertEquals(expectedAX[i], actualAX[i]);
      assertEquals(expectedAY[i], actualAY[i]);
      assertEquals(expectedBX[i], actualBX[i]);
      assertEquals(expectedBY[i], actualBY[i]);
      assertEquals(expectedEdgeX[i], actualEdgeX[i]);
      assertEquals(expectedEdgeY[i], actualEdgeY[i]);
    }
  }

}
