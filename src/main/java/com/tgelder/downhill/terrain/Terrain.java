package com.tgelder.downhill.terrain;

import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Terrain {

  private final int seed;
  private final int power;

  private Mesh mesh;
  private short[][] downhill;
  private int[][] flow;

  public double[][] getHeights() {
    return getMesh().getZ();
  }

  private Mesh getMesh() {
    if (mesh == null) {
      mesh = new Mesh(1);
      mesh.setZ(Mesh.MAX_VALUE);

      MeshSplitter splitter = new MeshSplitter(0.00, 0.95);
      RNG rng = new RandomRNG(seed);

      for (int i=0; i<power; i++) {
        mesh = splitter.split(mesh, rng);
      }
    }

    return mesh;
  }

  public short[][] getDownhill() throws DownhillException {
    if (downhill == null) {
      downhill = DownhillComputer.getDownhill(getMesh());
    }
    return downhill;
  }

  public int[][] getFlow() throws DownhillException {
    if (flow == null) {
      flow = FlowComputer.getFlow(getMesh(), getDownhill());
    }
    return flow;
  }

  public double getMinHeight() {
    return getMesh().getMinZ();
  }

  public double getMaxHeight() {
    return getMesh().getMaxZ();
  }

}
