package com.tgelder.downhill.terrain;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.rngs.RNG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Mesh {

  static final double MAX_VALUE = Double.MAX_VALUE;
  static final double MIN_VALUE = Double.MIN_VALUE;
    
  static final short [] dx8 = {-1, -1, 0, 1, 1, 1, 0, -1};
  static final short [] dy8 = {0, -1, -1, -1, 0, 1, 1, 1};

  static final short [] dx4 = {-1, 0, 1, 0};
  static final short [] dy4 = {0, -1, 0, 1};
  
  @Getter
  private final int width;
  private double[][] z;

  Mesh(int width) {
    this.width = width;
    z = new double[width][width];
  }

  final double getZ(int x, int y) {
    return z[x][y];
  }
  
  boolean inBounds(int x, int y){
    return x >= 0 && y >= 0 && x < width && y < width;
  }
  
  double[][] getZ() {
    return z;
  }
  
  double getZ(int x, int y, double outOfBoundsValue) {
    if (inBounds(x, y)) {
      return getZ(x, y);
    }
    else {
      return outOfBoundsValue;
    }
  }
  
  final void setZ(int x, int y, double value) {
    z[x][y] = value;
  }
  
  void setZ(double[][] values) {
    z = values;
  }
  
  void setZ(double value) {
    iterate((x, y) -> setZ(x, y, value));
  }
 
  double getMinZ() {
    double out = Mesh.MAX_VALUE;
    for (int x=0; x<width; x++) {
      for (int y=0; y<width; y++) {
        out = Math.min(out, getZ(x, y));
      }
    }
    return out;
  }
  
  double getMaxZ() {
    double out = Mesh.MIN_VALUE;
    for (int x=0; x<width; x++) {
      for (int y=0; y<width; y++) {
        out = Math.max(out, getZ(x, y));
      }
    }
    return out;
  }
  
  void iterate(MeshOperation operation) {
    for (int y = 0; y < width; y++) {
      for (int x = 0; x < width; x++) {
        operation.operate(x, y);
      }
    }
  }
  
  <T extends Throwable> void iterateWithThrows(MeshOperationWithThrows<T> operation) throws T {
    for (int y = 0; y < width; y++) {
      for (int x = 0; x < width; x++) {
        operation.operate(x, y);
      }
    }
  }

  List<Split> splitCell(int x, int y, BiFunction<Integer, Integer, Double> rng, Scale scale) {

    List<SplitRule> splitRules = new ArrayList<>();

    for (int offsetX = 0; offsetX < 2; offsetX++) {
      for (int offsetY = 0; offsetY < 2; offsetY++) {
        int xNeighbour = (offsetX * 2) - 1;
        int yNeighbour = (offsetY * 2) - 1;
        double xNeighbourZ = getZ(x + xNeighbour, y, Mesh.MIN_VALUE);
        double yNeighbourZ = getZ(x, y + yNeighbour, Mesh.MIN_VALUE);
        double z = getZ(x, y);

        double minZ = Math.min(Math.min(xNeighbourZ, yNeighbourZ), z);

        splitRules.add(new SplitRule(offsetX, offsetY, minZ, z));

      }
    }

    List<SplitRule> sorted = splitRules.stream()
        .sorted(Comparator.comparingDouble(sr -> sr.minZ))
        .collect(Collectors.toList());

    List<Split> out = new ArrayList<>();

//    System.out.println("Starting rules = " + sorted);

    for (SplitRule rule : sorted) {

      //System.out.println(rule.minZ);

      double r = rng.apply(x, y);
      Split split = rule.generateSplit(r, scale);


      out.add(split);

      for (SplitRule other : sorted) {
        if (other != rule) {
          if (other.offsetX == rule.offsetX || other.offsetY == rule.offsetY) {
            other.minZ = Math.min(other.minZ, split.z);

          }
        }
      }
    }

//    System.out.println("Final rules = " + sorted);
//    System.out.println("Final splits = " + out);

    return out;

  }

  @AllArgsConstructor
  @Data
  private static class SplitRule {
    final int offsetX;
    final int offsetY;
    double minZ;
    final double maxZ;

    private Split generateSplit(double r, Scale scale) {
      double range = (maxZ - minZ);
      double z = minZ + range * scale.scale(r);

      return new Split(offsetX, offsetY, z);
    }
  }

  @AllArgsConstructor
  @Data
  static class Split {
    final int offsetX;
    final int offsetY;
    final double z;
  }
  
  

}
