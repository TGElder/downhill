package com.tgelder.downhill.renderer;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.Vertex;
import com.tgelder.downhill.mesh.VertexIterator;


public class ZRenderer {

  private final Color [] colors = new Color[256];
  
  public ZRenderer() {
    for (int i = 0; i < 255; i++) {
      colors[i] = new Color(i, i, i);
    }
  }
  
  public void render(Mesh mesh, Image image) {
    VertexIterator iterator = new VertexIterator(mesh);

    Scale xScale = new Scale(0, mesh.getWidth(), 0, image.getWidth());
    Scale yScale = new Scale(0, mesh.getWidth(), 0, image.getHeight());
    Scale zScale = new Scale(Mesh.MIN_VALUE, mesh.getMaxZ(), 0, 255);
    
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
 
      int color = (int) (zScale.scale(vertex.getZ()));
      
    
      //int color = (int) (Math.log10(zScale.scale(vertex.getZ()))*255);
      
      if (color < 0) {
        image.setColor(Color.BLUE);
      }
      else {
        image.setColor(colors[color]);
      }
            
      
      
      image.drawPoint(
          (int)xScale.scale(vertex.getX()), 
          (int)yScale.scale(vertex.getY()));
    }

  }


}
