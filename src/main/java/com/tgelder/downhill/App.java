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
    
    MeshSplitter splitter = new MeshSplitter(0.05, 0.95);
    RNG rng = new RandomRNG(808);
    
    for (int i=0; i<10; i++) {

      mesh = splitter.split(mesh, rng);
      short[][] downhill = DownhillComputer.getDownhill(mesh);
      
      Image image = new AWTImage(mesh.getWidth(), mesh.getWidth());
      zRenderer.render(mesh, Mesh.MIN_VALUE * 0.75 + Mesh.MAX_VALUE * 0.25, image);
      FlowRenderer flowRenderer = new FlowRenderer(1000f);
      flowRenderer.render(mesh, FlowComputer.getFlow(mesh, downhill), image);
      image.save("gitignore/tile"+mesh.getWidth());
      
      Image downhillImage = new AWTImage(mesh.getWidth(), mesh.getWidth());
      downhillRenderer.render(mesh, downhill, downhillImage);
      downhillImage.save("gitignore/direction"+mesh.getWidth());
    }
    
  }
  
}
