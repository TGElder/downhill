package com.tgelder.downhill;

import java.util.Iterator;

public class TriangleIterator implements Iterator<MeshTriangle> {

  private final MeshTriangle triangle;
  private final int width;
  private int x;
  private int y;
  private int t;
  private boolean backSlash = true;

  TriangleIterator(Mesh mesh) {
    triangle = new MeshTriangle(mesh);
    width = mesh.getWidth() - 1;
  }

  @Override
  public boolean hasNext() {
    return y != width;
  }

  private void iterate() {
    t++;
    if (t == 2) {
      t = 0;
      x++;
      backSlash = !backSlash;
      if (x == width) {
        x = 0;
        y++;
        backSlash = !backSlash;
      }
    }
  }

  @Override
  public MeshTriangle next() {

    if (backSlash) {
      if (t == 0) {
        triangle.set(x, y, x + 1, y, x + 1, y + 1);
      } else {
        triangle.set(x, y, x + 1, y + 1, x, y + 1);
      }
    } else {
      if (t == 0) {
        triangle.set(x, y, x + 1, y, x, y + 1);
      } else {
        triangle.set(x + 1, y, x + 1, y + 1, x, y + 1);
      }
    }

    iterate();

    return triangle;
  }

}
