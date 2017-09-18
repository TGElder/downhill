package com.tgelder.downhill;

import java.util.Iterator;

public class EdgeIterator implements Iterator<CasedMeshEdge> {

  private final CasedMeshEdge casedMeshEdge;
  private int x;
  private int y;
  private final int width;
  
  EdgeIterator(Mesh mesh) {
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
    getEdgeCaseAt(x, y).setEdge(casedMeshEdge, x, y);

    iterate();

    return casedMeshEdge;
  }

  private EdgeCase getEdgeCaseAt(int x, int y) {
    for (EdgeCase edgeCase : EdgeCase.values()) {
      if (edgeCase.appliesAt(x, y)) {
        return edgeCase;
      }
    }
    return null;
  }
  

}
