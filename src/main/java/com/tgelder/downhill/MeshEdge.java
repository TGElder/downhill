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

  public void set(int amx, int amy, int bmx, int bmy) {
    a.set(amx, amy);
    b.set(bmx, bmy);
  }
  
  public String toString() {
    return a + " to " + b;
  }
}
