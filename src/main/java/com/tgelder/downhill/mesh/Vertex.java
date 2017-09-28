package com.tgelder.downhill.mesh;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Vertex {

  private final Mesh mesh;
  private int x;
  private int y;
  
  public Vertex(Mesh mesh) {
    this.mesh = mesh;
  }
  
  public void set(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public String toString() {
    return "("+x+", "+y+")";
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public double getZ() {
    return mesh.getZ(x, y);
  }
  
  public int getFlow() {
    return mesh.getFlow(x, y);
  }
  
  public short getDownhill() {
    return mesh.getDownhill(x, y);
  }

  public boolean inBounds() {
    return mesh.inBounds(x, y); 
  }
  
}
