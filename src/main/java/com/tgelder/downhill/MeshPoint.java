package com.tgelder.downhill;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeshPoint {
  
  private final Mesh mesh;
  private int tx;
  private int ty;
  
  public MeshPoint(Mesh mesh) {
    this.mesh = mesh;
  }
  
  public void set(int tx, int ty) {
    this.tx = tx;
    this.ty = ty;
  }
  
  public String toString() {
    return "("+tx+", "+ty+")";
  }

}
