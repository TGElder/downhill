package com.tgelder.downhill.renderer;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlowRenderer {

  private final float threshold;
  
  public void render(Mesh mesh, int[][] flow, Image image) {

    Scale xScale = new Scale(0, mesh.getWidth(), 0, image.getWidth());
    Scale yScale = new Scale(0, mesh.getWidth(), 0, image.getHeight());
    
    image.setColor(Color.BLUE);
    
    mesh.iterate((x, y) -> {

      if (flow[x][y] >= threshold) {
        image.drawPoint(
            (int)xScale.scale(x), 
            (int)yScale.scale(y));
      }
      
    });

  }


}
