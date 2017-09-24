package com.tgelder.downhill.mesh.iterators;

import java.util.Iterator;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshPoint;

public class PointIterator implements Iterator<MeshPoint> {

  private final MeshPoint point;
  private final int width;
  private int x;
  private int y;

  public PointIterator(Mesh mesh) {
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
