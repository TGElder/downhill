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

  public void set(int atx, int aty, int btx, int bty, int ctx, int cty) {
    ab.getA().set(atx, aty);
    bc.getA().set(btx, bty);
    ca.getA().set(ctx, cty);
  }

  public String toString() {
    return ab.getA() + " to " + bc.getA() + " to " + ca.getA();
  }

}
