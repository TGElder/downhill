package com.tgelder.downhill.terrain;

class SlopeComputer {

  static double[][] getSlope(Mesh mesh) {
    double[][] out = new double[mesh.getWidth()][mesh.getWidth()];

    mesh.iterate((x, y) -> out[x][y] = getSlope(mesh, x, y));

    return out;
  }

  private static double getSlope(Mesh mesh, int x, int y) {

    double out = Math.PI / 2;

    for (int n = 0; n < Mesh.dx.length; n++) {

      int dx = Mesh.dx[n];
      int dy = Mesh.dy[n];

      if (dx != 0 || dy != 0) {
        if (mesh.inBounds(x + dx, y + dy)) {
          out = Math.max(out, getSlope(mesh, x, y, x + dx, y + dy));
        }
      }
    }

    return out;
  }

  private static double getSlope(Mesh mesh, int ax, int ay, int bx, int by) {

    double run = getDistance(ax, ay, bx, by);

    double az = mesh.getZ(ax, ay);
    double bz = mesh.getZ(bx, by);
    double rise = Math.abs(az - bz);

    return Math.atan(rise / run);
  }

  private static double getDistance(int ax, int ay, int bx, int by) {
    return Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
  }

}
