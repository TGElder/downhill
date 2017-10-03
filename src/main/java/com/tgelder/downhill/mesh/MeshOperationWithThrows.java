package com.tgelder.downhill.mesh;

public interface MeshOperationWithThrows<T extends Throwable> {
  
  public void operate(int x, int y) throws T;

}
