package com.tgelder.downhill;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.tgelder.downhill.edgesplitters.EdgeSplitter;
import com.tgelder.downhill.edgesplitters.MidpointEdgeSplitter;
import com.tgelder.downhill.edgesplitters.SubpeakEdgeSplitter;
import com.tgelder.downhill.image.AWTImage;
import com.tgelder.downhill.image.Image;

public class App {
  public static void main(String[] args) throws IOException {
          
     List<String> argList = Arrays.asList(args);
     
     if (argList.contains("-generateImagesForReadme")) {
       generateImagesForReadme();
     }
     if (argList.contains("-generateImagesPerSplit")) {
       
       int seed = Integer.parseInt(getArgument(argList, "seed", "1986"));
       int splits = Integer.parseInt(getArgument(argList, "splits", "10"));
       String saveLocation = getArgument(argList, "saveLocation", "");
       int imageSize = Integer.parseInt(getArgument(argList, "imageSize", "1024"));
       
       generateImagePerSplit(seed, splits, saveLocation, imageSize);  
     }
    
  }
  
  private static String getArgument(List<String> argList, String name, String defaultValue) { 
    int index = argList.indexOf("-"+name);
    if (index == - 1) {
      return defaultValue;
    }
    else {
      return argList.get(index + 1);
    }
  }
  
  private static void generateImagePerSplit(int seed, int splits, String saveLocation, int imageSize) throws IOException {
    MeshTriangleRenderer triangleRenderer = new MeshTriangleRenderer();
    MeshLineRenderer lineRenderer = new MeshLineRenderer(Color.BLACK);
    
    RandomRNG rng = new RandomRNG(seed);
    
    EdgeSplitter xSplitter = new MidpointEdgeSplitter(MeshPoint::getX);
    EdgeSplitter ySplitter = new MidpointEdgeSplitter(MeshPoint::getY);
    EdgeSplitter zSplitter = new SubpeakEdgeSplitter(rng);
    
    Mesh mesh = Mesh.of3x3();
    
    for (int s = 0; s <= splits; s++) {
      Image image = new AWTImage(imageSize, imageSize);
      triangleRenderer.render(mesh, image);
      lineRenderer.render(mesh, image);
      image.save(saveLocation + "mesh"+mesh.getWidth());
      mesh = mesh.split(xSplitter, ySplitter, zSplitter);
    }
  }
  
  private static void generateImagesForReadme() throws IOException {
    
    MeshTriangleRenderer triangleRenderer = new MeshTriangleRenderer();
    MeshLineRenderer lineRenderer = new MeshLineRenderer(Color.BLACK);
    
    Mesh mesh = Mesh.of3x3();
    
    Image image = new AWTImage(257, 257);
    triangleRenderer.render(mesh, image);
    lineRenderer.render(mesh, image);
    image.save("images/mesh3");
    
    RandomRNG rng = new RandomRNG(1986);
    
    EdgeSplitter xSplitter = new MidpointEdgeSplitter(MeshPoint::getX);
    EdgeSplitter ySplitter = new MidpointEdgeSplitter(MeshPoint::getY);
    EdgeSplitter zSplitter = new SubpeakEdgeSplitter(rng);
    
    mesh = mesh.split(xSplitter, ySplitter, zSplitter);
    
    triangleRenderer.render(mesh, image);
    lineRenderer.render(mesh, image);
    image.save("images/mesh5");
    
    mesh = mesh.split(xSplitter, ySplitter, zSplitter);
    
    triangleRenderer.render(mesh, image);
    lineRenderer.render(mesh, image);
    image.save("images/mesh9");
    
    mesh = mesh.split(xSplitter, ySplitter, zSplitter);
    
    triangleRenderer.render(mesh, image);
    lineRenderer.render(mesh, image);
    image.save("images/mesh17");
    
    for (int i=0; i<4; i++) {
      mesh = mesh.split(xSplitter, ySplitter, zSplitter);
    }
    
    triangleRenderer.render(mesh, image);
    image.save("images/mesh257");

  }
 
}
