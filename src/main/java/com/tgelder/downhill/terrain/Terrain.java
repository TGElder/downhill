package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class Terrain {

  @Getter
  private final int seed;
  @Getter
  private final int power;
  @Getter
  private final double maxAltitude;

  private Mesh rawMesh;
  private Mesh scaledMesh;
  private short[][] downhill;
  private int[][] flow;
  private double[][] slope;

  private Mesh getRawMesh() {
    if (rawMesh == null) {
      rawMesh = new Mesh(1);
      rawMesh.setZ(Mesh.MAX_VALUE);

      MeshSplitter splitter = new MeshSplitter(0.01, 0.95);
      RNG rng = new RandomRNG(seed);

      for (int i=0; i<power; i++) {
        rawMesh = splitter.split(rawMesh, rng);
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
      downhill = DownhillComputer.getDownhill(getRawMesh());
    }
    return downhill;
  }

  public int[][] getFlow() throws DownhillException {
    if (flow == null) {
      flow = FlowComputer.getFlow(getRawMesh(), getDownhill());
    }
    return flow;
  }

  public double[][] getSlope() {
    if (slope == null) {
      slope = SlopeComputer.getSlope(getScaledMesh());
    }
    return slope;
  }

  public boolean inBounds(int x, int y) {
    return rawMesh.inBounds(x, y);
  }

  public int getWidth() {
    return getScaledMesh().getWidth();
  }

  public Optional<Point> getDownhill(int x, int y) throws DownhillException {
    int nx = x + Mesh.dx[getDownhill()[x][y]];
    int ny = y + Mesh.dy[getDownhill()[x][y]];
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
