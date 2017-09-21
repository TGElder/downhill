package com.tgelder.downhill.edgesplitters;

import java.util.function.Function;

import com.tgelder.downhill.CasedMeshEdge;
import com.tgelder.downhill.MeshPoint;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MidpointEdgeSplitter implements EdgeSplitter {
  
  private final Function<MeshPoint, Float> axis;
  
  public float split(CasedMeshEdge edge) {
    return axis.apply(edge.getA())/2f + axis.apply(edge.getB())/2f;
  }

}
