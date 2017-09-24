package com.tgelder.downhill.mesh.iterators;

import java.util.Iterator;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.mesh.MeshPoint.PointCase;

public class PointEdgeIterator implements Iterator<MeshEdge> {

  private final MeshEdge next;
  private final MeshEdge current;
  
  private boolean hasNext;
  
  private MeshPoint point;
  private int width;
  
  private int n;
  private int maxN;
  private final int[][] a = {{0, -1}, {-1, 0}, {0, 0}, {0, 0},
      {-1, -1}, {0, 0}, {-1, 1}, {0, 0}};
  private final int[][] b = {{0, 0}, {0, 0}, {1, 0}, {0, 1}, 
      {0, 0}, {1, -1}, {0, 0}, {1, 1}};
  
  public PointEdgeIterator(Mesh mesh) {
    next = new MeshEdge(mesh);
    current = new MeshEdge(mesh);
  }
  
  public void setPoint(MeshPoint point) {
    this.point = point;
    
    width = point.getMesh().getWidth();
    
    n = 0;
    
    if (point.getPointCase() == PointCase.EIGHT_NEIGHBOURS) {
      maxN = 8;
    }
    else {
      maxN = 4;
    }
    
    getNext();
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public MeshEdge next() {
    current.set(next.getA().getMx(),
        next.getA().getMy(),
        next.getB().getMx(),
        next.getB().getMy());
    getNext();
    return current;
  }
  
  private void getNext() {
 
    hasNext = false;
    
    while (n < maxN) {
      next.set(point.getMx() + a[n][0],
          point.getMy() + a[n][1],
          point.getMx() + b[n][0],
          point.getMy() + b[n][1]);
      
      n++;
      
      if (inBounds(next)) {
        hasNext = true;
        return;
      }
      
    }
  
  }
  
  private boolean inBounds(MeshEdge edge) {
    return inBounds(edge.getA()) && inBounds(edge.getB());
  }
  
  private boolean inBounds(MeshPoint point) {
    return (point.getMx() >= 0) && (point.getMx() < width) &&
        (point.getMy() >= 0) && (point.getMy() < width);
  }

}
