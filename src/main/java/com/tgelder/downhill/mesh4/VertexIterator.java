package com.tgelder.downhill.mesh4;

import java.util.Iterator;

public class VertexIterator implements Iterator<Vertex> {

  private final Vertex vertex;
  private int x;
  private int y;
  private final int width;
  
  public VertexIterator(Mesh mesh) {
    vertex = new Vertex(mesh);
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
  public Vertex next() {
    vertex.set(x, y);

    iterate();

    return vertex;
  }

 
  

}
