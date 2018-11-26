package com.tgelder.downhill.terrain;

import com.tgelder.downhill.rngs.RNG;

import java.util.*;

class DownhillComputer {
    
  static short[][] getDownhill(Mesh mesh, short[] dx, short[] dy, RNG rng) throws DownhillException {
    short[][] out = new short[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterateWithThrows((x, y) -> out[x][y] = getDownhill(mesh, x, y, dx, dy, rng));
    
    return out;
  }
  
  private static short getDownhill(Mesh mesh, int x, int y, short[] dx, short[] dy, RNG rng) throws DownhillException {
    double minZ = mesh.getZ(x, y);

    List<Short> candidates = new ArrayList<>();
    List<Double> wheel = new ArrayList<>();

    double w = 0;

    for (short d = 0; d < dx.length; d++) {

      double focusZ = mesh.getZ(x + dx[d], y + dy[d], 0);
      if (focusZ < minZ) {
        candidates.add(d);
        w += 1;
        wheel.add(w);
      }
    }

    if (candidates.isEmpty()) {
      throw new DownhillException("No downhill from "+x+", "+y+ " (z = " + minZ+ ")");
    }
    else {
      double r = rng.getNext() * w;
      int i = 0;
      while (wheel.get(i) < r) {
        i++;
      }
      return candidates.get(i);
    }
  }

}
