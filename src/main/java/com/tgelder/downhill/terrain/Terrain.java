package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.renderer.FlowSamplerRenderer;
import com.tgelder.downhill.renderer.HeightRenderer;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Terrain {

  @Getter
  private final int seed;
  @Getter
  private final int power;
  @Getter
  private final double maxAltitude;

  private final RNG rng;

  private Mesh rawMesh;
  private Mesh scaledMesh;
  private short[][] downhill;
  private int[][] flow;
  private double[][] slope;
  private double[][][] downhillProbabilities;

  Terrain(int seed, int power, double maxAltitude) {
    this.seed = seed;
    this.power = power;
    this.maxAltitude = maxAltitude;
    this.rng = new RandomRNG(seed);
  }

  private Mesh getRawMesh() {
    if (rawMesh == null) {
      rawMesh = new Mesh(1);
      rawMesh.setZ(Mesh.MAX_VALUE);

      MeshSplitter splitter = new MeshSplitter(0.05, 0.5);

      for (int i=0; i<power; i++) {
        System.out.println(i);
        rawMesh = splitter.split(rawMesh, rng);
        int size = rawMesh.getWidth() * rawMesh.getWidth();

        try {
//          Image image = new AWTImage(rawMesh.getWidth(), rawMesh.getWidth());
//          new HeightRenderer().render(MeshZScaler.scale(rawMesh, new Scale(rawMesh.getMinZ(), rawMesh.getMaxZ(), 0, maxAltitude)).getZ(), 0, maxAltitude, image);
//          try {
//            image.save("z" + i );
//          } catch (IOException e) {
//            e.printStackTrace();
//          }

          if (i < 9) {
            downhillProbabilities = DownhillProbabilityComputer.getProbabilities(rawMesh, Mesh.dx8, Mesh.dy8);
            int samples = 8;
            int flow[][][] = new FlowSampler(rng).getFlow(rawMesh, downhillProbabilities, Mesh.dx8, Mesh.dy8, samples);
            int threshold = i * 2;
//            Image flowImage = new AWTImage(rawMesh.getWidth(), rawMesh.getWidth());
//            new FlowSamplerRenderer(threshold).render(flow, flowImage);
//
//            try {
//              flowImage.save("f" + i );
//            } catch (IOException e) {
//              e.printStackTrace();
//            }

            rawMesh.iterateWithThrows((x, y) -> {



              for (int s = 0; s < samples; s++) {
                if (flow[s][x][y] > 1 && flow[s][x][y] >= threshold) {
                  double after = rawMesh.getZ(x, y) * 0.85;
//                  rawMesh.setZ(x, y, after);

//                  double factor = ((size * 1.0 - flow[0][x][y]) / (size * 1.0));
//                 factor = Math.floor(factor * 10.0) / 10.0;
//                  double after = rawMesh.getZ(x, y) * factor;
////
                  rawMesh.setZ(x, y, after);
                  break;
                }
              }

//              if (flow[0][x][y] > 1 && flow[0][x][y] >= Math.pow(size / 65536, 2.0)) {
//                double factor = ((size * 1.0 - flow[0][x][y]) / (size * 1.0));
//                factor = Math.floor(factor * 10.0) / 10.0;
//                double after = rawMesh.getZ(x, y) * factor;
//
//                //System.out.println(String.format("%s, %s, %s, %s", size, flow[x][y], factor, after));
//                rawMesh.setZ(x, y, after);
//              }

            });

          }

        } catch (DownhillException e) {
          throw new RuntimeException(e);
        }

      }
    }

    return rawMesh;
  }

  private double[][] getRawAltitudes() {
    return getRawMesh().getZ();
  }

  private Mesh getScaledMesh() {
    if (scaledMesh == null) {
      Mesh mesh = getRawMesh();
      scaledMesh = MeshZScaler.scale(mesh, new Scale(mesh.getMinZ(), mesh.getMaxZ(), 0, maxAltitude));
    }
    return scaledMesh;
  }

  public double[][] getAltitudes() {
    return getScaledMesh().getZ();
  }

  public short[][] getDownhill() throws DownhillException {
    if (downhill == null) {
      downhill = DownhillComputer.getDownhill(getRawMesh(), Mesh.dx8, Mesh.dy8, rng);
    }
    return downhill;
  }

  public int[][] getFlow() throws DownhillException {
    if (flow == null) {
      flow = FlowComputer.getFlow(getRawMesh(), getDownhill(), Mesh.dx8, Mesh.dy8);
    }
    return flow;
  }

  public double[][] getSlope() {
    if (slope == null) {
      slope = SlopeComputer.getSlope(getScaledMesh());
    }
    return slope;
  }

  private double[][][] getDownhillProbabilities() throws DownhillException {
    if (downhillProbabilities == null) {
      downhillProbabilities = DownhillProbabilityComputer.getProbabilities(getRawMesh(), Mesh.dx8, Mesh.dy8);
    }
    return downhillProbabilities;
  }

  public int[][][] getFlowSample(int samples) throws DownhillException {
    FlowSampler sampler = new FlowSampler(rng);
    return sampler.getFlow(getRawMesh(), getDownhillProbabilities(), Mesh.dx8, Mesh.dy8, samples);
  }

  private boolean inBounds(int x, int y) {
    return rawMesh.inBounds(x, y);
  }

  public int getWidth() {
    return getScaledMesh().getWidth();
  }

  public Optional<Point> getDownhill(int x, int y) throws DownhillException {
    int nx = x + Mesh.dx8[getDownhill()[x][y]];
    int ny = y + Mesh.dy8[getDownhill()[x][y]];
    if (inBounds(nx, ny)) {
      return Optional.of(new Point(nx, ny));
    } else {
      return Optional.empty();
    }
  }

  @AllArgsConstructor
  public static class Point {
    public final int x;
    public final int y;
  }

}
