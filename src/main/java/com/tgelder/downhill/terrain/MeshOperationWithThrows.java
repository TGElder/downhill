package com.tgelder.downhill.terrain;

interface MeshOperationWithThrows<T extends Throwable> {
  
  void operate(int x, int y) throws T;

}
