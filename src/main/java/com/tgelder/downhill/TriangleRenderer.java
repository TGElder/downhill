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
  private int x, y, fx, tx, fy, ty;
  private final Scale xScale = new Scale();
  private final Scale yScale = new Scale();
  private final Scale zScale = new Scale();
  private int colour;
  private final TriangleZInterpolator interpolator = new TriangleZInterpolator();

  public TriangleRenderer() {
    points.add(a);
    points.add(b);
    points.add(c);
  }

  public void render(MeshTriangle triangle, Image image) {
    xScale.set(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, image.getWidth());
    yScale.set(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, image.getHeight());
    zScale.set(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, 255);

    setPointWithImageCoordinates(a, triangle.getA());
    setPointWithImageCoordinates(b, triangle.getB());
    setPointWithImageCoordinates(c, triangle.getC());

    fx = points.stream().mapToInt(p -> (int) Math.floor(p.getX())).min().getAsInt();
    tx = points.stream().mapToInt(p -> (int) Math.ceil(p.getX())).max().getAsInt();
    fy = points.stream().mapToInt(p -> (int) Math.floor(p.getY())).min().getAsInt();
    ty = points.stream().mapToInt(p -> (int) Math.ceil(p.getY())).max().getAsInt();

    interpolator.setTriangle(t);

    for (x = fx; x <= tx; x++) {
      for (y = fy; y <= ty; y++) {

        if (interpolator.pointIsInTriangle(x, y)) {

          colour = (int) interpolator.getZ(x, y);

          image.setColor(colour, colour, colour);
          image.drawPoint(x, y);
          
        }
      }
    }

  }
  
  private void setPointWithImageCoordinates(Point point, MeshPoint meshPoint) {
    point.set(xScale.scale(meshPoint.getX()), 
        yScale.scale(meshPoint.getY()),
        zScale.scale(meshPoint.getZ()));
  }
  
 

}
