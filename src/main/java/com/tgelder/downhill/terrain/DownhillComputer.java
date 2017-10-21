package com.tgelder.downhill.terrain;

class DownhillComputer {
    
  static short[][] getDownhill(Mesh mesh) throws DownhillException {
    short[][] out = new short[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterateWithThrows((x, y) -> out[x][y] = getDownhill(mesh, x, y));
    
    return out;
  }
  
  private static short getDownhill(Mesh mesh, int x, int y) throws DownhillException {
    short out = -1;
    double minZ = mesh.getZ(x, y);
    
    for (short d = 0; d < Mesh.dx.length; d++) {
      double focusZ = mesh.getZ(x + Mesh.dx[d], y + Mesh.dy[d], 0);
      if (focusZ < minZ) {
        out = d;
        minZ = focusZ;
      }
    }
    
    if (out == -1) {
      throw new DownhillException("No downhill from "+x+", "+y);
    }
    else {
      return out;
    }
  }

}
