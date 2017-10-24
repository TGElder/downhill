package com.tgelder.downhill.terrain;

import lombok.Getter;

class Mesh {

  static final double MAX_VALUE = Double.MAX_VALUE;
  static final double MIN_VALUE = Double.MIN_VALUE;
    
  static final short [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static final short [] dy = {0, -1, -1, -1, 0, 1, 1, 1};
  
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
  
  

}
