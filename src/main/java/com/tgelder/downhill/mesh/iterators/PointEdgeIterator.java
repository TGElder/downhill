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
    current.copy(next);
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
      
      if (next.inBounds()) {
        hasNext = true;
        return;
      }
      
    }
  
  }
  
}
