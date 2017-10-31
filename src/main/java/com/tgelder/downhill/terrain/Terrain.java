package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Terrain {

  private final int seed;
  private final int power;
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

}
