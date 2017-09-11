package com.tgelder.downhill;

import lombok.Getter;

@Getter
public class MeshTriangle {

  private final MeshEdge ab;
  private final MeshEdge bc;
  private final MeshEdge ca;

  public MeshTriangle(Mesh mesh) {
    MeshPoint a = new MeshPoint(mesh);
    MeshPoint b = new MeshPoint(mesh);
    MeshPoint c = new MeshPoint(mesh);

    ab = new MeshEdge(a, b);
    bc = new MeshEdge(b, c);
    ca = new MeshEdge(c, a);
  }

  public void set(int amx, int amy, int bmx, int bmy, int cmx, int cmy) {
    ab.getA().set(amx, amy);
    bc.getA().set(bmx, bmy);
    ca.getA().set(cmx, cmy);
  }
  
  public MeshPoint getA() {
    return ab.getA();
  }
  
  public MeshPoint getB() {
    return bc.getA();
  }
  
  public MeshPoint getC() {
    return ca.getA();
  }

  public String toString() {
    return getA() + " to " + getA() + " to " + getA();
  }

}
