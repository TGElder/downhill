package com.tgelder.downhill;

import org.junit.Test;

import com.tgelder.downhill.geometry.Point;
import com.tgelder.downhill.geometry.Triangle;
import com.tgelder.downhill.geometry.TriangleZInterpolator;
import com.tgelder.downhill.image.MockImage;

import static org.junit.Assert.assertEquals;

public class TriangleRendererTest {
  
  @Test
  public void testTriangleRenderer() {

    Mesh mesh = new Mesh(3);
    
    float min = Mesh.MIN_VALUE;
    float max = Mesh.MAX_VALUE;
    float mid = min/2f + max/2f;
    
    mesh.set(0, 0, min, min, min);
    mesh.set(1, 0, mid, min, min);
    mesh.set(1, 1, mid, mid, max);
    
    MeshTriangle triangle = new MeshTriangle(mesh);
    triangle.set(0, 0, 1, 0, 1, 1);
    
    MockImage image = new MockImage(100);
    
    TriangleRenderer renderer = new TriangleRenderer();
    renderer.render(triangle, image);
    
    assertEquals(image.getR(0, 0), 0);
    assertEquals(image.getR(50, 0), 0);
    assertEquals(image.getR(50, 50), 255);
    assertEquals(image.getR(25, 0), 0);
    assertEquals(image.getR(50, 25), 127);
    assertEquals(image.getR(25, 25), 127);
    
    TriangleZInterpolator interpolator = new TriangleZInterpolator();
    interpolator.setTriangle(new Triangle(new Point(0,0,0),new Point(50,0,0),new Point(50,50,255)));
    assertEquals(image.getR(38, 12), (int)interpolator.getZ(38,12)); 

  }

 

}
