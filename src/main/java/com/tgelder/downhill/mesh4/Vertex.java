package com.tgelder.downhill.mesh4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Vertex {

  private final Mesh mesh;
  private int mx;
  private int my;
  
  public Vertex(Mesh mesh) {
    this.mesh = mesh;
  }
  
  public void set(int mx, int my) {
    this.mx = mx;
    this.my = my;
  }
  
  public String toString() {
    return "("+mx+", "+my+")";
  }
  
  public float getX() {
    return mesh.getX(mx, my);
  }
  
  public float getY() {
    return mesh.getY(mx, my);
  }
  
  public float getZ() {
    return mesh.getZ(mx, my);
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Vertex)) {
      return false;
    }
    else {
      return ((Vertex) other).mx == mx
          && ((Vertex) other).my == my;
    }
  }
  
  public boolean atEdge() {
    return (mx == 0) || (my == 0) || (mx == mesh.getWidth() - 1) || (my == mesh.getWidth() - 1);
  }
  
  public boolean inBounds() {
    return (mx >= 0) && (mx < mesh.getWidth()) &&
        (my >= 0) && (my < mesh.getWidth());
  }
  
  public void copy(Vertex other) {
    mx = other.mx;
    my = other.my;
  }



}
