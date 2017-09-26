package com.tgelder.downhill.mesh4;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;


public class TileMeshRenderer {

  
  public static void render(Mesh mesh, Image image) {
    VertexIterator iterator = new VertexIterator(mesh);

    Scale xScale = new Scale(0, mesh.getWidth(), 0, image.getWidth());
    Scale yScale = new Scale(0, mesh.getWidth(), 0, image.getHeight());
    Scale zScale = new Scale(Mesh.MIN_VALUE, mesh.getMaxZ(), -255, 255);
    
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
 
      int color = (int) (zScale.scale(vertex.getZ()));
      
    
      //int color = (int) (Math.log10(zScale.scale(vertex.getZ()))*255);
      
      if (color < 0) {
        image.setColor(0, 0, 255);
      }
      else {
        image.setColor(color, color, color);
      }
            
      
      
      image.drawPoint(
          (int)xScale.scale(vertex.getX()), 
          (int)yScale.scale(vertex.getY()));
    }

  }


}
