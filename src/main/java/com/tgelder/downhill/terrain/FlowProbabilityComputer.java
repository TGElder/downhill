package com.tgelder.downhill.terrain;

import lombok.AllArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;

@AllArgsConstructor
class FlowProbabilityComputer {

  private final double threshold;
  
  double[][] getFlow(Mesh mesh, double[][][] probabilities, short[] dx, short[] dy) {
    double[][] flow = new double[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterate((x, y) -> computeFlow(mesh, probabilities, flow, x, y, dx, dy));
    
    return flow;
  }
  
  private void computeFlow(Mesh mesh, double[][][] probabilities, double[][] flow, int x, int y, short[] dx, short[] dy) {

    System.out.println(y);

    final Deque<Flow> stack = new ArrayDeque<>();

    stack.push(new Flow(x, y, 1));

    while (!stack.isEmpty()) {

      Flow focus = stack.pop();

      if (focus.flow > this.threshold) {

        if (mesh.inBounds(focus.x, focus.y)) {

          flow[focus.x][focus.y] += 1;

          for (int d = 0; d < dx.length; d++) {
            double probability = probabilities[focus.x][focus.y][d];
            if (probability > 0) {
              int nx = focus.x + dx[d];
              int ny = focus.y + dy[d];
              stack.push(new Flow(nx, ny, probability * focus.flow));
            }
          }

        }
      }

    }
  }

  @AllArgsConstructor
  private static class Flow {

    private final int x;
    private final int y;
    private final double flow;

  }

}
