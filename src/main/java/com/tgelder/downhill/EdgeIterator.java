package com.tgelder.downhill;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EdgeIterator implements Iterator<Edge> {

  private final Edge edge = new Edge();
  private final Mesh mesh;
  private int x;
  private int y;
  private int c;
  private final int width;
  private final List<EdgeCase> edgeCases = new ArrayList<>();

  EdgeIterator(Mesh mesh) {
    this.mesh = mesh;
    width = mesh.getWidth() * 2 - 1;

    edgeCases.add(new Point());
    edgeCases.add(new Horizontal());
    edgeCases.add(new Vertical());
    edgeCases.add(new ForwardSlash());
    edgeCases.add(new BackSlash());
  }

  @Override
  public boolean hasNext() {
    return y != width;
  }

  private void iterate() {
    x++;
    if (x == width) {
      x = 0;
      y++;
    }
  }

  @Override
  public Edge next() {

    getEdgeCaseAt(x, y).setEdge(edge, x, y);

    iterate();

    return edge;
  }

  private EdgeCase getEdgeCaseAt(int x, int y) {
    for (c = 0; c < edgeCases.size(); c++) {
      if (edgeCases.get(c).appliesAt(x, y)) {
        return edgeCases.get(c);
      }
    }
    return null;
  }

  private interface EdgeCase {
    boolean appliesAt(int x, int y);

    void setEdge(Edge edge, int x, int y);
  }

  private class Point implements EdgeCase {

    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    public void setEdge(Edge edge, int x, int y) {
      edge.set(x / 2, y / 2, x / 2, y / 2);
    }

  }

  private class Horizontal implements EdgeCase {

    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    public void setEdge(Edge edge, int x, int y) {
      edge.set(x / 2, y / 2, (x / 2) + 1, y / 2);
    }

  }

  private class Vertical implements EdgeCase {

    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }

    @Override
    public void setEdge(Edge edge, int x, int y) {
      edge.set(x / 2, y / 2, x / 2, (y / 2) + 1);
    }

  }

  private class ForwardSlash implements EdgeCase {

    @Override
    public boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) != (y % 4 == 1));
    }

    @Override
    public void setEdge(Edge edge, int x, int y) {
      edge.set(x / 2, (y / 2) + 1, (x / 2) + 1, y / 2);
    }

  }

  private class BackSlash implements EdgeCase {

    @Override
    public boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) == (y % 4 == 1));
    }

    @Override
    public void setEdge(Edge edge, int x, int y) {
      edge.set(x / 2, y / 2, (x / 2) + 1, (y / 2) + 1);
    }

  }

}
