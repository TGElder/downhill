package com.tgelder.downhill.mesh;

public class FlowComputer {
  
  public static int[][] getFlow(Mesh mesh, short[][] downhill) {
    int[][] flow = new int[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterate((x, y) -> computeFlow(mesh, downhill, flow, x, y));
    
    return flow;
  }
  
  private static void computeFlow(Mesh mesh, short[][] downhill, int[][] flow, int x, int y) {
    if (mesh.inBounds(x, y)) {
      final short d = downhill[x][y];

      flow[x][y] ++;
      computeFlow(mesh, downhill, flow, x + Mesh.dx[d], y + Mesh.dy[d]);
    }
    else {
      return;
    }
  }

}
