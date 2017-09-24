package com.tgelder.downhill.mesh.iterators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshTriangle;
import com.tgelder.downhill.mesh.iterators.TriangleIterator;

public class TriangleIteratorTest {

  @Test
  public void testTriangleCounts() {

    for (int i = 0; i <= 12; i++) {
      int size = (int) (Math.pow(2, i)) + 1;
      int triangles = (size - 1) * (size - 1) * 2;
      assertEquals(triangles, getTriangleCount(size));
    }

  }

  private int getTriangleCount(int size) {
    Mesh mesh = new Mesh(size);
    TriangleIterator iterator = new TriangleIterator(mesh);

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
    TriangleIterator iterator = new TriangleIterator(mesh);

    List<Integer> actual = new ArrayList<>();

    while (iterator.hasNext()) {
      MeshTriangle meshTriangle = iterator.next();

      actual.add(meshTriangle.getA().getMx());
      actual.add(meshTriangle.getA().getMy());
      actual.add(meshTriangle.getB().getMx());
      actual.add(meshTriangle.getB().getMy());
      actual.add(meshTriangle.getC().getMx());
      actual.add(meshTriangle.getC().getMy());
    }

    Integer[] expected = { 0, 0, 1, 0, 1, 1,
                           0, 0, 1, 1, 0, 1 };

    for (int i = 0; i < 12; i++) {
      assertEquals(expected[i], actual.get(i));
    }

  }

  @Test
  public void test3x3Edges() {

    Mesh mesh = new Mesh(3);
    TriangleIterator iterator = new TriangleIterator(mesh);

    List<Integer> actual = new ArrayList<>();

    while (iterator.hasNext()) {
      MeshTriangle meshTriangle = iterator.next();

      actual.add(meshTriangle.getA().getMx());
      actual.add(meshTriangle.getA().getMy());
      actual.add(meshTriangle.getB().getMx());
      actual.add(meshTriangle.getB().getMy());
      actual.add(meshTriangle.getC().getMx());
      actual.add(meshTriangle.getC().getMy());
    }

    Integer[] expected = { 0, 0, 1, 0, 1, 1,
                           0, 0, 1, 1, 0, 1,
                           1, 0, 2, 0, 1, 1,
                           2, 0, 2, 1, 1, 1,
                           0, 1, 1, 1, 0, 2,
                           1, 1, 1, 2, 0, 2,
                           1, 1, 2, 1, 2, 2,
                           1, 1, 2, 2, 1, 2 };

    for (int i = 0; i < 48; i++) {
      assertEquals(expected[i], actual.get(i));
    }

  }

}
