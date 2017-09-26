package com.tgelder.downhill.mesh4;

import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;

public class App {
  
  public static void main(String [] args) throws IOException {
    Mesh mesh = Mesh.of2x2();
    
    for (int i=0; i<10; i++) {
      Image image = new AWTImage(mesh.getWidth(), mesh.getWidth());
      TileMeshRenderer.render(mesh, image);
      image.save("gitignore/tile"+mesh.getWidth());
      mesh = mesh.split();
    }
  }

}
