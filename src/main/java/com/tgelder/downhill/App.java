package com.tgelder.downhill;

import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;

public class App {
  public static void main(String[] args) throws IOException {
     int side=3;
    
     Mesh mesh = Mesh.of3x3();
     
     TriangleIterator iterator = new TriangleIterator(mesh);
    
     int scale = 8;
    
     Image image = new AWTImage(side*scale,side*scale);
     Image render = new AWTImage(1024, 1024);
     TriangleRenderer renderer = new TriangleRenderer();
    
     image.setColor(255, 255, 255);
    
     while (iterator.hasNext()) {
      
       MeshTriangle triangle = iterator.next();
      
       renderer.render(triangle, render);
       
       image.drawLine(
           triangle.getA().getMx()*scale,
           triangle.getA().getMy()*scale,
           triangle.getB().getMx()*scale,
           triangle.getB().getMy()*scale);
       
       image.drawLine(
           triangle.getB().getMx()*scale,
           triangle.getB().getMy()*scale,
           triangle.getC().getMx()*scale,
           triangle.getC().getMy()*scale);
     }
    
     image.save("test");
     render.save("render");
     
     

  }
}
