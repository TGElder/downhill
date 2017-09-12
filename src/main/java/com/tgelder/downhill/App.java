package com.tgelder.downhill;

import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;

public class App {
  public static void main(String[] args) throws IOException {
     int side=3;
    
     
     Mesh mesh = Mesh.of3x3();
         
     for (int i=0; i<9; i++) {
       mesh = mesh.split();
     }
     
     TriangleIterator iterator = new TriangleIterator(mesh);

     Image render = new AWTImage(1024, 1024);
     TriangleRenderer renderer = new TriangleRenderer();
        
     while (iterator.hasNext()) {
      
       MeshTriangle triangle = iterator.next();
      
       renderer.render(triangle, render);
     
     }
    
     render.save("render");
     
     

  }
}
