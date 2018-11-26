package com.tgelder.downhill.terrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DownhillProbabilityComputer {
    
  static double[][][] getProbabilities(Mesh mesh, short[] dx, short[] dy) throws DownhillException {
    double[][][] out = new double[mesh.getWidth()][mesh.getWidth()][dx.length];
    
    mesh.iterateWithThrows((x, y) -> out[x][y] = getDownhill(mesh, x, y, dx, dy));
    
    return out;
  }
  
  private static double[] getDownhill(Mesh mesh, int x, int y, short[] dx, short[] dy) throws DownhillException {
    double minZ = mesh.getZ(x, y);

    double[] drops = {0, 0, 0, 0};

    double total = 0;

    for (short d = 0; d < dx.length; d++) {
      double focusZ = mesh.getZ(x + dx[d], y + dy[d], 0);
      if (focusZ < minZ) {
        double drop = minZ - focusZ;
        drops[d] = drop;
        total += drop;
      }
    }

    for (short d = 0; d < dx.length; d++) {
      drops[d] /= total;
    }

    return drops;
  }

}
