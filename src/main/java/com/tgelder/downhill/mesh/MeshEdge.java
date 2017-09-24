package com.tgelder.downhill.mesh;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeshEdge {

  private final MeshPoint a;
  private final MeshPoint b;

  public MeshEdge(Mesh mesh) {
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

  public EdgeCase getEdgeCase() {
    return computeEdgeCase();
  }

  private EdgeCase computeEdgeCase() {
    for (EdgeCase edgeCase : EdgeCase.values()) {
      if (edgeCase.appliesTo(this)) {
        return edgeCase;
      }
    }
    return null;
  }
}
