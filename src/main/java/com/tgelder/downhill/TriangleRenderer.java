package com.tgelder.downhill;

import java.util.ArrayList;
import java.util.List;

import com.tgelder.downhill.geometry.Point;
import com.tgelder.downhill.geometry.Triangle;
import com.tgelder.downhill.geometry.TriangleZInterpolator;
import com.tgelder.downhill.image.Image;

public class TriangleRenderer {

  private final Point a = new Point();
  private final Point b = new Point();
  private final Point c = new Point();
  private final Triangle t = new Triangle(a, b, c);
  private final List<Point> points = new ArrayList<Point>();
  int x, y, fx, tx, fy, ty;
  float z;
  int colour;
  private final TriangleZInterpolator interpolator = new TriangleZInterpolator();

  public TriangleRenderer() {
    points.add(a);
    points.add(b);
    points.add(c);
  }

  public void render(MeshTriangle triangle, Image image) {

    a.set(triangle.getA().getX(), triangle.getA().getY(), triangle.getA().getZ());
    b.set(triangle.getB().getX(), triangle.getB().getY(), triangle.getB().getZ());
    c.set(triangle.getC().getX(), triangle.getC().getY(), triangle.getC().getZ());

    fx = points.stream().mapToInt(p -> (int)Math.floor(p.getX())).min().getAsInt();
    tx = points.stream().mapToInt(p -> (int)Math.ceil(p.getX())).max().getAsInt();
    fy = points.stream().mapToInt(p -> (int)Math.floor(p.getY())).min().getAsInt();
    ty = points.stream().mapToInt(p -> (int)Math.ceil(p.getY())).max().getAsInt();

    interpolator.setTriangle(t);

    for (x = fx; x <= tx; x++) {
      for (y = fy; y <= ty; y++) {

        if (interpolator.pointIsInTriangle(x, y)) {

          z = (interpolator.getZ(x, y)/1024f);
          
          colour = (int) (z*255);
          
          image.setColor(colour, colour, colour);
          image.drawPoint(x, y);
        }
      }
    }

  }

}
