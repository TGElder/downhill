package com.tgelder.downhill.terrain;

class FlowComputer {
  
  static int[][] getFlow(Mesh mesh, short[][] downhill, short[] dx, short[] dy) {
    int[][] flow = new int[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterate((x, y) -> computeFlow(mesh, downhill, flow, x, y, dx, dy));
    
    return flow;
  }
  
  private static void computeFlow(Mesh mesh, short[][] downhill, int[][] flow, int x, int y, short[] dx, short[] dy) {
    if (mesh.inBounds(x, y)) {
      final short d = downhill[x][y];

      flow[x][y] ++;
      computeFlow(mesh, downhill, flow, x + dx[d], y + dy[d], dx, dy);
    }
  }

}
