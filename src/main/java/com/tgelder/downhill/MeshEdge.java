package com.tgelder.downhill;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeshEdge {

  private final MeshPoint a;
  private final MeshPoint b;

  MeshEdge(Mesh mesh) {
    a = new MeshPoint(mesh);
    b = new MeshPoint(mesh);
  }

  public void set(int atx, int aty, int btx, int bty) {
    a.set(atx, aty);
    b.set(btx, bty);
  }
  
  public String toString() {
    return a + " to " + b;
  }
}
