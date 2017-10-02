package com.tgelder.downhill;

import java.io.IOException;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshSplitter;
import com.tgelder.downhill.renderer.FlowRenderer;
import com.tgelder.downhill.renderer.ZRenderer;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;

public class App {
  
  public static void main(String [] args) throws IOException {
    Mesh mesh = new Mesh(1);
    mesh.setAllZ(Mesh.MAX_VALUE);
    mesh.computeDirections();

    //ZRenderer zRenderer = new ZRenderer();

//    DirectionRenderer directionRenderer = new DirectionRenderer();
    
    MeshSplitter splitter = new MeshSplitter(0.1, 0.9);
    RNG rng = new RandomRNG(1986);
    
    for (int i=0; i<1; i++) {
      

      mesh = splitter.split(mesh, rng);
      //mesh.computeDirections();
      //mesh.computeFlow();
      
//      Image image = new AWTImage(mesh.getWidth(), mesh.getWidth());
//      zRenderer.render(mesh, image);
//      
//      FlowRenderer flowRenderer = new FlowRenderer(0.5f/mesh.getWidth());
//      flowRenderer.render(mesh, image);
//      image.save("gitignore/tile"+mesh.getWidth());
//      Image directionImage = new AWTImage(mesh.getWidth(), mesh.getWidth());
//      directionRenderer.render(mesh, directionImage);
//      directionImage.save("gitignore/direction"+mesh.getWidth());
    }
    
    Scale scale = new Scale(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, 100);
    
    System.out.println(mesh.getWidth());
    
    System.out.print("{");
    for (int y=0; y<2; y++) {
      System.out.print("{");
      for (int x=0; x<2; x++) {
        System.out.print(Math.round(scale.scale(mesh.getZ(x, y))) + ",");
      }
      System.out.println("},");
    }
    System.out.println("}");
  }
  

  

}
