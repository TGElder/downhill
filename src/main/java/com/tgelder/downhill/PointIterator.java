package com.tgelder.downhill;

import java.util.Iterator;

public class PointIterator implements Iterator<MeshPoint> {

  private final MeshPoint point;
  private final int width;
  private int x;
  private int y;

  PointIterator(Mesh mesh) {
    point = new MeshPoint(mesh);
    width = mesh.getWidth();
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
  public MeshPoint next() {
    point.set(x, y);
    iterate();
    return point;
  }

}
