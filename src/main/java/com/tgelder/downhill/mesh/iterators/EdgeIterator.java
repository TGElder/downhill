package com.tgelder.downhill.mesh.iterators;

import java.util.Iterator;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;

public class EdgeIterator implements Iterator<MeshEdge> {

  private final MeshEdge meshEdge;
  private int x;
  private int y;
  private final int width;
  
  public EdgeIterator(Mesh mesh) {
    meshEdge = new MeshEdge(mesh);
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
  public MeshEdge next() {
    Mesh.getEdgeCaseAt(x, y).setEdge(meshEdge, x, y);

    iterate();

    return meshEdge;
  }

 
  

}
