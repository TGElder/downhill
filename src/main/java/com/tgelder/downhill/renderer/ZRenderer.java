package com.tgelder.downhill.renderer;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;

public class ZRenderer {

  private final Color [] colors = new Color[256];
  
  public ZRenderer() {
    for (int i = 0; i < 256; i++) {
      colors[i] = new Color(i, i, i);
    }
  }
  
  public void render(Mesh mesh, double seaLevel, Image image) {

    Scale xScale = new Scale(0, mesh.getWidth(), 0, image.getWidth());
    Scale yScale = new Scale(0, mesh.getWidth(), 0, image.getHeight());
    Scale zScale = new Scale(seaLevel, mesh.getMaxZ(), 0, 255);
    
    mesh.iterate((x, y) -> {
 
      int color = (int) (zScale.scale(mesh.getZ(x, y)));
      
      if (color < 0) {
        image.setColor(Color.BLUE);
      }
      else {
        image.setColor(colors[color]);
      }

      image.drawPoint(
          (int)xScale.scale(x), 
          (int)yScale.scale(y));
    });

  }


}
