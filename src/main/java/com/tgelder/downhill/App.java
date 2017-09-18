package com.tgelder.downhill;

import java.awt.Color;
import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;

public class App {
  public static void main(String[] args) throws IOException {
          
     Mesh mesh = Mesh.of3x3();
         
     for (int i=0; i<5; i++) {
       mesh = mesh.split();
     }
     
     Image render = new AWTImage(1024, 1024);
     MeshTriangleRenderer triangleRenderer = new MeshTriangleRenderer();
     MeshLineRenderer lineRenderer = new MeshLineRenderer(Color.RED);
     
     triangleRenderer.render(mesh, render);
     lineRenderer.render(mesh, render);
     
     render.save("render");
    
  }
 
}
