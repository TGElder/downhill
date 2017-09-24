package com.tgelder.downhill.mesh.iterators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.iterators.EdgeIterator;

public class EdgeIteratorTest {

  @Test
  public void testEdgeCounts() {

    for (int i = 0; i <= 12; i++) {
      int size = (int) (Math.pow(2, i)) + 1;
      int edges = (int) (Math.pow(2, i + 1)) + 1;
      edges = edges * edges;
      assertEquals(edges, getEdgeCount(size));
    }

  }

  private int getEdgeCount(int size) {
    Mesh mesh = new Mesh(size);
    EdgeIterator iterator = new EdgeIterator(mesh);

    int count = 0;

    while (iterator.hasNext()) {
      iterator.next();
      count++;
    }

    return count;
  }

  @Test
  public void test2x2Edges() {

    Mesh mesh = new Mesh(2);
    EdgeIterator iterator = new EdgeIterator(mesh);

    List<Integer> actual = new ArrayList<>();

    while (iterator.hasNext()) {
      MeshEdge meshEdge = iterator.next();

      actual.add(meshEdge.getA().getMx());
      actual.add(meshEdge.getA().getMy());
      actual.add(meshEdge.getB().getMx());
      actual.add(meshEdge.getB().getMy());
    }

    Integer[] expected = { 0, 0, 0, 0, 
                           0, 0, 1, 0, 
                           1, 0, 1, 0, 
                           0, 0, 0, 1, 
                           0, 0, 1, 1, 
                           1, 0, 1, 1, 
                           0, 1, 0, 1, 
                           0, 1, 1, 1,
                           1, 1, 1, 1 };

    for (int i = 0; i < 9; i++) {
      assertEquals(expected[i], actual.get(i));
    }

  }

  @Test
  public void test3x3Edges() {

    Mesh mesh = new Mesh(3);
    EdgeIterator iterator = new EdgeIterator(mesh);

    List<Integer> actual = new ArrayList<>();

    while (iterator.hasNext()) {
      MeshEdge meshEdge = iterator.next();

      actual.add(meshEdge.getA().getMx());
      actual.add(meshEdge.getA().getMy());
      actual.add(meshEdge.getB().getMx());
      actual.add(meshEdge.getB().getMy());
    }

    Integer[] expected = { 0, 0, 0, 0,
                           0, 0, 1, 0, 
                           1, 0, 1, 0, 
                           1, 0, 2, 0, 
                           2, 0, 2, 0, 
                           0, 0, 0, 1, 
                           0, 0, 1, 1, 
                           1, 0, 1, 1, 
                           1, 1, 2, 0, 
                           2, 0, 2, 1, 
                           0, 1, 0, 1, 
                           0, 1, 1, 1, 
                           1, 1, 1, 1, 
                           1, 1, 2, 1, 
                           2, 1, 2, 1, 
                           0, 1, 0, 2, 
                           0, 2, 1, 1,
                           1, 1, 1, 2, 
                           1, 1, 2, 2, 
                           2, 1, 2, 2, 
                           0, 2, 0, 2, 
                           0, 2, 1, 2, 
                           1, 2, 1, 2, 
                           1, 2, 2, 2, 
                           2, 2, 2, 2 };

    for (int i = 0; i < 100; i++) {
      assertEquals(expected[i], actual.get(i));
    }

  }

}
