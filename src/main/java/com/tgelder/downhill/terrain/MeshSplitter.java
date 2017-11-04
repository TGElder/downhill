package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.rngs.RNG;

class MeshSplitter {
  
  private final Scale scale;

  MeshSplitter(double minSplit, double maxSplit) {
    scale = new Scale(0, 1, minSplit, maxSplit);
  }

  Mesh split(Mesh in, RNG rng) {
    Mesh out = new Mesh(in.getWidth() * 2);

    out.iterate((x, y) -> out.setZ(x, y, getOutZ(in, x, y, rng)));

    return out;
  }

  private double getOutZ(Mesh in, int x, int y, RNG rng) {
    int xOffset = ((x % 2) * 2) - 1;
    int yOffset = ((y % 2) * 2) - 1;

    int ix = x / 2;
    int iy = y / 2;

    double minZ = Math.min(in.getZ(ix, iy, Mesh.MIN_VALUE), Math.min(in.getZ(ix + xOffset, iy, 0), in.getZ(ix, iy + yOffset, 0)));
    double maxZ = in.getZ(ix, iy, Mesh.MIN_VALUE);

    double r = rng.getNext();

    double range = (maxZ - minZ);

    return minZ + range * scale.scale(r);
  }

}