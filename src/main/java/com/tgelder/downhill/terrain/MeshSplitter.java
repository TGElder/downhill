package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.rngs.RNG;

import java.util.function.BiFunction;

class MeshSplitter {
  
  private final Scale scale;

  MeshSplitter(double minSplit, double maxSplit) {
    scale = new Scale(0, 1, minSplit, maxSplit);
  }

  Mesh split(Mesh in, BiFunction<Integer, Integer, Double> rng) {
    Mesh out = new Mesh(in.getWidth() * 2);

    in.iterate((x, y) -> in.splitCell(x, y, rng, scale)
        .forEach(split -> out.setZ((x * 2) + split.offsetX, (y * 2) + split.offsetY, split.z)));

    return out;
  }


}
