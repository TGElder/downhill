package com.tgelder.downhill.mesh4;

import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;

public class App {
  
  public static void main(String [] args) throws IOException {
    Mesh mesh = Mesh.of(2);

    TileMeshRenderer tileMeshRenderer = new TileMeshRenderer();

//    DirectionRenderer directionRenderer = new DirectionRenderer();

    
    for (int i=0; i<10; i++) {
      mesh = mesh.split();
      Image image = new AWTImage(mesh.getWidth(), mesh.getWidth());
      tileMeshRenderer.render(mesh, image);
      
      FlowRenderer flowRenderer = new FlowRenderer(0.5f/mesh.getWidth());
      flowRenderer.render(mesh, image);
      image.save("gitignore/tile"+mesh.getWidth());
//      Image directionImage = new AWTImage(mesh.getWidth(), mesh.getWidth());
//      directionRenderer.render(mesh, directionImage);
//      directionImage.save("gitignore/direction"+mesh.getWidth());
    }
  }

}
