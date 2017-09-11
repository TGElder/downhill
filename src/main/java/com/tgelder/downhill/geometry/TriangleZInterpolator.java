package com.tgelder.downhill.geometry;

public class TriangleZInterpolator {

  private final Point ta = new Point();
  private final Point tb = new Point();
  private final Point tc = new Point();
  private float taz;
  private float tbz;
  private float tcz;

  private final Point p = new Point();
  private final Point cross = new Point();

  private final Point pa = new Point();
  private final Point pb = new Point();
  private final Point pc = new Point();
  private float a;
  private float aa;
  private float ab;
  private float ac;

  private double s1;
  private double s2;
  private double s3;

  public void setTriangle(Triangle triangle) {
    ta.copy(triangle.getA()).setZ(1);
    tb.copy(triangle.getB()).setZ(1);
    tc.copy(triangle.getC()).setZ(1);

    taz = triangle.getA().getZ();
    tbz = triangle.getB().getZ();
    tcz = triangle.getC().getZ();
  }

  public float getZ(float x, float y) {
    p.set(x, y, 1);

    pa.subtract(ta, p);
    pb.subtract(tb, p);
    pc.subtract(tc, p);

    aa = cross.cross(pb, pc).magnitude();
    ab = cross.cross(pc, pa).magnitude();
    ac = cross.cross(pa, pb).magnitude();

    a = aa + ab + ac;

    return (taz * aa + tbz * ab + tcz * ac) / a;
  }

  private double sign(float x, float y, Point b, Point c) {
    return (x - c.getX()) * (b.getY() - c.getY()) - (b.getX() - c.getX()) * (y - c.getY());
  }

  public boolean pointIsInTriangle(float x, float y) {
    s1 = Math.signum(sign(x, y, ta, tb));
    s2 = Math.signum(sign(x, y, tb, tc));
    s3 = Math.signum(sign(x, y, tc, ta));

    return (((s1 == -1) || (s1 == 0)) && ((s2 == -1) || (s2 == 0)) && ((s3 == -1) || (s3 == 0)))
        || (((s1 == 1) || (s1 == 0)) && ((s2 == 1) || (s2 == 0)) && ((s3 == 1) || (s3 == 0)));
  }

}
