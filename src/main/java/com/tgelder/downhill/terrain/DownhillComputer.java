package com.tgelder.downhill.terrain;

class DownhillComputer {
    
  static short[][] getDownhill(Mesh mesh, short[] dx, short[] dy) throws DownhillException {
    short[][] out = new short[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterateWithThrows((x, y) -> out[x][y] = getDownhill(mesh, x, y, dx, dy));
    
    return out;
  }
  
  private static short getDownhill(Mesh mesh, int x, int y, short[] dx, short[] dy) throws DownhillException {
    short out = -1;
    double minZ = mesh.getZ(x, y);
    
    for (short d = 0; d < dx.length; d++) {
      double focusZ = mesh.getZ(x + dx[d], y + dy[d], 0);
      if (focusZ < minZ) {
        out = d;
        minZ = focusZ;
      }
    }
    
    if (out == -1) {
      throw new DownhillException("No downhill from "+x+", "+y+ " (z = " + minZ+ ")");
    }
    else {
      return out;
    }
  }

}
