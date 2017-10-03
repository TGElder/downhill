package com.tgelder.downhill;

import java.io.IOException;

import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.DownhillComputer;
import com.tgelder.downhill.mesh.DownhillException;
import com.tgelder.downhill.mesh.FlowComputer;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshSplitter;
import com.tgelder.downhill.renderer.DownhillRenderer;
import com.tgelder.downhill.renderer.FlowRenderer;
import com.tgelder.downhill.renderer.ZRenderer;
import com.tgelder.downhill.rngs.RNG;
import com.tgelder.downhill.rngs.RandomRNG;

public class App {
  
  public static void main(String [] args) throws IOException, DownhillException {
    Mesh mesh = new Mesh(1);
    mesh.setZ(Mesh.MAX_VALUE);

    ZRenderer zRenderer = new ZRenderer();
    DownhillRenderer downhillRenderer = new DownhillRenderer();
    
    MeshSplitter splitter = new MeshSplitter(0.1, 0.9);
    RNG rng = new RandomRNG(1986);
    
    for (int i=0; i<10; i++) {

      mesh = splitter.split(mesh, rng);
      short[][] downhill = DownhillComputer.getDownhill(mesh);
      
      Image image = new AWTImage(mesh.getWidth(), mesh.getWidth());
      zRenderer.render(mesh, image);
      FlowRenderer flowRenderer = new FlowRenderer(0.5f/mesh.getWidth());
      flowRenderer.render(mesh, image, FlowComputer.getFlow(mesh, downhill));
      image.save("gitignore/tile"+mesh.getWidth());
      
      Image downhillImage = new AWTImage(mesh.getWidth(), mesh.getWidth());
      downhillRenderer.render(mesh, downhill, downhillImage);
      downhillImage.save("gitignore/direction"+mesh.getWidth());
    }
    
  }
  
}
