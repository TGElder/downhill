package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;

class MeshZScaler {

  static Mesh scale(Mesh mesh, Scale scale) {
    double[][] heights = new double[mesh.getWidth()][mesh.getWidth()];

    mesh.iterate((x, y) -> heights[x][y] = scale.scale(mesh.getZ(x, y)));

    Mesh out = new Mesh(mesh.getWidth());
    out.setZ(heights);

    return out;
  }

}
