package com.tgelder.downhill.edgesplitters;

import java.util.function.Function;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.rngs.RNG;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandomEdgeSplitter implements EdgeSplitter {

  private final RNG rng;
  private final Function<MeshPoint, Float> axis;
  private float r;
  private final Scale scale;
  
  public RandomEdgeSplitter(RNG rng, Function<MeshPoint, Float> axis, float min, float max) {
    this.rng = rng;
    this.axis = axis;
    this.scale = new Scale(0, 1, min, max);
  }

  public float split(MeshEdge edge) {
    r = scale.scale(rng.getNext());
    return axis.apply(edge.getA()) * r + axis.apply(edge.getB()) * (1 - r);
  }

}
