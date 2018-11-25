package com.tgelder.downhill.terrain;

import java.util.*;

class DownhillComputer {
    
  static short[][] getDownhill(Mesh mesh, short[] dx, short[] dy) throws DownhillException {
    short[][] out = new short[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterateWithThrows((x, y) -> out[x][y] = getDownhill(mesh, x, y, dx, dy));
    
    return out;
  }
  
  private static short getDownhill(Mesh mesh, int x, int y, short[] dx, short[] dy) throws DownhillException {
    short out = -1;
    double minZ = mesh.getZ(x, y);

    List<Short> candidates = new ArrayList<>();

    for (short d = 0; d < dx.length; d++) {

      double focusZ = mesh.getZ(x + dx[d], y + dy[d], 0);
      if (focusZ < minZ) {
        candidates.add(d);
        //out = d;
        //minZ = focusZ;
      }
    }
    
    if (candidates.isEmpty()) {
      throw new DownhillException("No downhill from "+x+", "+y+ " (z = " + minZ+ ")");
    }
    else {
      Collections.shuffle(candidates);
      return candidates.get(0);
    }
  }

}
