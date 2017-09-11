package com.tgelder.downhill;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TriangleIteratorTest {

  @Test
  public void testTriangleCounts() {

    for (int i = 0; i <= 12; i++) {
      System.out.println(i); // TODO replace with logging
      int size = (int) (Math.pow(2, i)) + 1;
      int triangles = (size - 1) * (size - 1) * 2;
      assertEquals(triangles, getTriangleCount(size));
    }

  }

  private int getTriangleCount(int size) {
    System.out.println("Creating mesh");
    Mesh mesh = new Mesh(size);
    TriangleIterator iterator = new TriangleIterator(mesh);

    int count = 0;
    
    System.out.println("Iterating");
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

      actual.add(meshTriangle.getA().getTx());
      actual.add(meshTriangle.getA().getTy());
      actual.add(meshTriangle.getB().getTx());
      actual.add(meshTriangle.getB().getTy());
      actual.add(meshTriangle.getC().getTx());
      actual.add(meshTriangle.getC().getTy());
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

      actual.add(meshTriangle.getA().getTx());
      actual.add(meshTriangle.getA().getTy());
      actual.add(meshTriangle.getB().getTx());
      actual.add(meshTriangle.getB().getTy());
      actual.add(meshTriangle.getC().getTx());
      actual.add(meshTriangle.getC().getTy());
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
