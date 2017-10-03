package com.tgelder.downhill.mesh;

import lombok.Getter;

public class Mesh {

  public static final double MAX_VALUE = Float.MAX_VALUE;
  public static final double MIN_VALUE = Float.MIN_VALUE;
    
  public static final short [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  public static final short [] dy = {0, -1, -1, -1, 0, 1, 1, 1};
  
  @Getter
  private final int width;
  private double[][] z;

  public Mesh(int width) {
    this.width = width;
    z = new double[width][width];
  }

  public final double getZ(int x, int y) {
    return z[x][y];
  }
  
  public boolean inBounds(int x, int y){
    return x >= 0 && y >= 0 && x < width && y < width;
  }
  
  public double[][] getZ() {
    return z;
  }
  
  public double getZ(int x, int y, int outOfBoundsValue) {
    if (inBounds(x, y)) {
      return getZ(x, y);
    }
    else {
      return outOfBoundsValue;
    }
  }
  
  public final void setZ(int x, int y, double value) {
    z[x][y] = value;
  }
  
  public void setZ(double[][] values) {
    z = values;
  }
  
  public void setZ(double value) {
    iterate((x, y) -> setZ(x, y, value));
  }
 
  public double getMaxZ() {
    double out = Mesh.MIN_VALUE;
    for (int x=0; x<width; x++) {
      for (int y=0; y<width; y++) {
        out = Math.max(out, getZ(x, y));
      }
    }
    return out;
  }
  
  public void iterate(MeshOperation operation) {
    for (int y = 0; y < width; y++) {
      for (int x = 0; x < width; x++) {
        operation.operate(x, y);
      }
    }
  }
  
  public <T extends Throwable> void iterateWithThrows(MeshOperationWithThrows<T> operation) throws T {
    for (int y = 0; y < width; y++) {
      for (int x = 0; x < width; x++) {
        operation.operate(x, y);
      }
    }
  }
  
  

}
