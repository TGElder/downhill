package com.tgelder.downhill.rivers;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.mesh.iterators.PointEdgeIterator;
import com.tgelder.downhill.mesh.iterators.PointIterator;

import lombok.Getter;

public class FlowComputer {
  
  @Getter
  private final Mesh mesh;
  private final MeshPoint current;
  
  private final MeshPoint focus;
  private MeshEdge focusEdge;
  private final MeshEdge downhillEdge;
  private final MeshPoint downhill;
  
  private final PointEdgeIterator pointEdgeIterator;
    
  @Getter
  private float [][] flow;
  
  public FlowComputer(Mesh mesh) {
    this.mesh = mesh;
    current = new MeshPoint(mesh);
    focus = new MeshPoint(mesh);
    downhillEdge = new MeshEdge(mesh);
    downhill = new MeshPoint(mesh);
    pointEdgeIterator = new PointEdgeIterator(mesh);
    int edgeWidth = mesh.getWidth()*2 - 1;
    flow = new float[edgeWidth][edgeWidth];
  }
  
  public void rain() throws FlowException {
    
    PointIterator pointIterator = new PointIterator(mesh);

    while (pointIterator.hasNext()) {
      rain(pointIterator.next(), 1);
    }
  }
  
  public void rain(MeshPoint point, float volume) throws FlowException {
    if (!point.atEdge()) {
      current.copy(point);
      computeDownhill();
      placeFlow(volume);
      rain(downhill, volume);
    }
    else {
      return;
    }
  }
  
  private void computeDownhill() throws FlowException {
    pointEdgeIterator.setPoint(current);
    downhill.copy(current);
    
    while (pointEdgeIterator.hasNext()) {
      focusEdge = pointEdgeIterator.next();
      focus.copy(focusEdge.getOtherEnd(current));
      
      if (focus.getZ() <= downhill.getZ()) {
        downhillEdge.copy(focusEdge);
        downhill.copy(focus);
      }
    }
    
    if (downhill.getZ() == current.getZ()) {
      String message = String.format("Point %s with elevation %s has no downhill path!", current, current.getZ());
      pointEdgeIterator.setPoint(current);
      while (pointEdgeIterator.hasNext()) {
        focusEdge = pointEdgeIterator.next();
        message += String.format("\nNeighbouring %s with elevation %s", focusEdge, focusEdge.getOtherEnd(current).getZ());
      }
      
      throw new FlowException(message);
    }
  }
  
  private void placeFlow(float volume) {
    flow[downhillEdge.getEdgeX()][downhillEdge.getEdgeY()] += volume;
  }
  
  
 

}
