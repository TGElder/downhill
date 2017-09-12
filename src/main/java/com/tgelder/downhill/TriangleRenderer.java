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
  double xScale, yScale, zScale;
  int colour;
  private final TriangleZInterpolator interpolator = new TriangleZInterpolator();

  public TriangleRenderer() {
    points.add(a);
    points.add(b);
    points.add(c);
  }

  public void render(MeshTriangle triangle, Image image) {

    xScale = image.getWidth()/(Mesh.MAX_VALUE - Mesh.MIN_VALUE);
    yScale = image.getHeight()/(Mesh.MAX_VALUE - Mesh.MIN_VALUE);
    zScale = 255/(Mesh.MAX_VALUE - Mesh.MIN_VALUE);
    
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
    point.set(scale(meshPoint.getX(), xScale), 
        scale(meshPoint.getY(), yScale),
        scale(meshPoint.getZ(), zScale));
  }
  
  private float scale(float value, double scale) {
    return (float)((value - Mesh.MIN_VALUE)*scale);
  }

}
