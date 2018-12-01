package com.tgelder.downhill.terrain;

import com.tgelder.downhill.rngs.RNG;
import lombok.AllArgsConstructor;

import java.util.stream.IntStream;

@AllArgsConstructor
class FlowSampler {


  private final RNG rng;

  int[][][] getFlow(Mesh mesh, double[][][] downhillProbabilities, short[] dx, short[] dy, int samples) {

    int[][][] flow = new int[samples][mesh.getWidth()][mesh.getWidth()];

    IntStream.range(0, samples)
        .forEach(i -> {

          final short[][] downhill = new short[mesh.getWidth()][mesh.getWidth()];

          mesh.iterate((x, y) -> {
            final double r = this.rng.getNext();

            double cumulative = 0;
            short d = 0;

            while (cumulative <= r && d < dx.length) {
              cumulative += downhillProbabilities[x][y][d];
              d++;
            }

            d--;

            downhill[x][y] = d;
          });

          System.out.print(".");
          mesh.iterate((x, y) -> computeFlow(mesh, downhill, flow[i], x, y, dx, dy));
        });
    System.out.println();
    
    return flow;
  }
  
  private void computeFlow(Mesh mesh, short[][] downhill, int[][] flow, int x, int y, short[] dx, short[] dy) {
    if (mesh.inBounds(x, y)) {
      short d = downhill[x][y];

      flow[x][y] ++;
      computeFlow(mesh, downhill, flow, x + dx[d], y + dy[d], dx, dy);
    }
  }

}
