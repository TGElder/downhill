package com.tgelder.downhill.terrain;

import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
class FlowProbabilityComputer {

  double[][] getFlow(Mesh mesh, double[][][] probabilities, short[] dx, short[] dy) {
    double[][] flow = new double[mesh.getWidth()][mesh.getWidth()];

    List<WorkItem> work = new ArrayList<>();
    
    mesh.iterate((x, y) -> work.add(new WorkItem(x, y)));

    Comparator<WorkItem> comparator = Comparator.comparingDouble(a -> mesh.getZ(a.x, a.y));
    work.sort(comparator.reversed());

    for (WorkItem focus : work) {
      computeFlow(mesh, probabilities, flow, focus.x, focus.y, dx, dy);
    }
    
    return flow;
  }
  
  private void computeFlow(Mesh mesh, double[][][] probabilities, double[][] flow, int x, int y, short[] dx, short[] dy) {

    flow[x][y] += 1;
    //System.out.println(x +", "+ y+ " = "+flow[x][y]);

    for (int d = 0; d < dx.length; d++) {
      double probability = probabilities[x][y][d];
      if (probability > 0) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (mesh.inBounds(nx, ny)) {
          flow[nx][ny] += (flow[x][y] * probability);

        }
      }
    }
  }

  @AllArgsConstructor
  private static class WorkItem {

    private final int x;
    private final int y;

  }

}
