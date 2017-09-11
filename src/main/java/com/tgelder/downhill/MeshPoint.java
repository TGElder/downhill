package com.tgelder.downhill;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeshPoint {
  
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


}
