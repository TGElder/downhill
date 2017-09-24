package com.tgelder.downhill.mesh;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeshPoint {
  
  public enum PointCase{
    FOUR_NEIGHBOURS,
    EIGHT_NEIGHBOURS
  }
  
  private final Mesh mesh;
  private int mx;
  private int my;
  
  public MeshPoint(Mesh mesh) {
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
  
  public PointCase getPointCase() {
    if ((mx % 2) == (my % 2)) {
      return PointCase.EIGHT_NEIGHBOURS;
    }
    else {
      return PointCase.FOUR_NEIGHBOURS;
    }
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof MeshPoint)) {
      return false;
    }
    else {
      return ((MeshPoint) other).mx == mx
          && ((MeshPoint) other).my == my;
    }
  }
  
  public boolean atEdge() {
    return (mx == 0) || (my == 0) || (mx == mesh.getWidth() - 1) || (my == mesh.getWidth() - 1);
  }
  
  public boolean inBounds() {
    return (mx >= 0) && (mx < mesh.getWidth()) &&
        (my >= 0) && (my < mesh.getWidth());
  }
  
  public void copy(MeshPoint other) {
    mx = other.mx;
    my = other.my;
  }



}
