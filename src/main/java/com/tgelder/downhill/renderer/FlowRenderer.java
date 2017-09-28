package com.tgelder.downhill.renderer;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.Vertex;
import com.tgelder.downhill.mesh.VertexIterator;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FlowRenderer {

  private final float threshold;
  
  public void render(Mesh mesh, Image image) {
    VertexIterator iterator = new VertexIterator(mesh);

    Scale xScale = new Scale(0, mesh.getWidth(), 0, image.getWidth());
    Scale yScale = new Scale(0, mesh.getWidth(), 0, image.getHeight());
    Scale fScale = new Scale(0, mesh.getWidth() * mesh.getWidth(), 0, 1);
    
    image.setColor(Color.BLUE);
    
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
      

      double flow = (fScale.scale(vertex.getFlow()));

      if (flow >= threshold) {
        image.drawPoint(
            (int)xScale.scale(vertex.getX()), 
            (int)yScale.scale(vertex.getY()));
      }
      
    }

  }


}
