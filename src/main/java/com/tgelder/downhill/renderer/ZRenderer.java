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
  
  public void render(Mesh mesh, double min, double max, Image image) {

    Scale xScale = new Scale(0, image.getWidth(), 0, mesh.getWidth());
    Scale yScale = new Scale(0, image.getHeight(), 0, mesh.getWidth());
    Scale zScale = new Scale(min, max, 0, 255);
    
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        
        int mx = (int) xScale.scale(x);
        int my = (int) yScale.scale(y);
        
        int color = (int) (zScale.scale(mesh.getZ(mx, my)));
                
        if (color < 0) {
          image.setColor(Color.BLUE);
        }
        else {
          image.setColor(colors[color]);
        }

        image.drawPoint(x, y);
      }
    }

  }


}
