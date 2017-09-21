package com.tgelder.downhill;

import java.util.Iterator;

public class EdgeIterator implements Iterator<CasedMeshEdge> {

  private final Mesh mesh;
  private final CasedMeshEdge casedMeshEdge;
  private int x;
  private int y;
  private final int width;
  
  EdgeIterator(Mesh mesh) {
    this.mesh = mesh;
    casedMeshEdge = new CasedMeshEdge(mesh);
    width = mesh.getWidth() * 2 - 1;
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
  public CasedMeshEdge next() {
    mesh.getEdgeCaseAt(x, y).setEdge(casedMeshEdge, x, y);

    iterate();

    return casedMeshEdge;
  }

 
  

}
