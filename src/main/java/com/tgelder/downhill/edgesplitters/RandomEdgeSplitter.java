package com.tgelder.downhill.edgesplitters;

import java.util.function.Function;

import com.tgelder.downhill.CasedMeshEdge;
import com.tgelder.downhill.MeshPoint;
import com.tgelder.downhill.RNG;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandomEdgeSplitter implements EdgeSplitter {

  private final RNG rng;
  private final Function<MeshPoint, Float> axis;
  private float r;
  
  public RandomEdgeSplitter(RNG rng, Function<MeshPoint, Float> axis) {
    this.rng = rng;
    this.axis = axis;
  }

  public float split(CasedMeshEdge edge) {
    r = rng.getNext();
    return axis.apply(edge.getA()) * r + axis.apply(edge.getB()) * (1 - r);
  }

}
