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
  
  public int getEdgeX() {
    return getEdgeCase().getEdgeX(a.getMx());
  }
  
  public int getEdgeY() {
    return getEdgeCase().getEdgeY(a.getMy());
  }
  
  public MeshPoint getOtherEnd(MeshPoint point) {
    if (a.equals(point)) {
      return b;
    }
    else if (b.equals(point)) {
      return a;
    }
    else {
      return null;
    }
  }
  
  public boolean inBounds() {
    return a.inBounds() && b.inBounds();
  }
  
  public void copy(MeshEdge other) {
    a.copy(other.getA());
    b.copy(other.getB());
  }
}
