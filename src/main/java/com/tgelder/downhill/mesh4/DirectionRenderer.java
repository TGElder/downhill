package com.tgelder.downhill.mesh4;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;


public class DirectionRenderer {
  
  private final Color [] colors = new Color[8];
  
  public DirectionRenderer() {
    colors[0] = new Color(127, 127, 127);
    colors[1] = new Color(63, 63, 63);
    colors[2] = new Color(0, 0, 0);
    colors[3] = new Color(63, 63, 63);
    colors[4] = new Color(127, 127, 127);
    colors[5] = new Color(191, 191, 191);
    colors[6] = new Color(255, 255, 255);
    colors[7] = new Color(191, 191, 191);
  }
  
  public void render(Mesh mesh, Image image) {
    VertexIterator iterator = new VertexIterator(mesh);

    Scale xScale = new Scale(0, mesh.getWidth(), 0, image.getWidth());
    Scale yScale = new Scale(0, mesh.getWidth(), 0, image.getHeight());
     
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();

      image.setColor(colors[vertex.getDownhill()]);

      image.drawPoint(
          (int)xScale.scale(vertex.getX()), 
          (int)yScale.scale(vertex.getY()));
      
    }

  }


}
