package com.tgelder.downhill;

import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.renderer.FlowRenderer;
import com.tgelder.downhill.renderer.ZRenderer;

public class App {
  
  public static void main(String [] args) throws IOException {
    Mesh mesh = new Mesh(2);
    mesh.setAllZ(Mesh.MAX_VALUE);
    mesh.computeDirections();

    ZRenderer zRenderer = new ZRenderer();

//    DirectionRenderer directionRenderer = new DirectionRenderer();

    
    for (int i=0; i<10; i++) {
      mesh = mesh.split();
      Image image = new AWTImage(mesh.getWidth(), mesh.getWidth());
      zRenderer.render(mesh, image);
      
      FlowRenderer flowRenderer = new FlowRenderer(0.5f/mesh.getWidth());
      flowRenderer.render(mesh, image);
      image.save("gitignore/tile"+mesh.getWidth());
//      Image directionImage = new AWTImage(mesh.getWidth(), mesh.getWidth());
//      directionRenderer.render(mesh, directionImage);
//      directionImage.save("gitignore/direction"+mesh.getWidth());
    }
  }

}
