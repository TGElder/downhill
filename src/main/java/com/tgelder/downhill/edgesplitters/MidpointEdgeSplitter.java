package com.tgelder.downhill.edgesplitters;

import java.util.function.Function;

import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MidpointEdgeSplitter implements EdgeSplitter {
  
  private final Function<MeshPoint, Float> axis;
  
  public float split(MeshEdge edge) {
    return axis.apply(edge.getA())/2f + axis.apply(edge.getB())/2f;
  }

}
