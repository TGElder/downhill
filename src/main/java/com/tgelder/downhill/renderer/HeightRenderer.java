package com.tgelder.downhill.renderer;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;

import java.awt.*;

public class HeightRenderer {

  private final Color [] colors = new Color[256];
  
  public HeightRenderer() {
    for (int i = 0; i < 256; i++) {
      colors[i] = new Color(i, i, i);
    }
  }
  
  public void render(double[][] heights, double min, double max, Image image) {

    Scale xScale = new Scale(0, image.getWidth(), 0, heights.length);
    Scale yScale = new Scale(0, image.getHeight(), 0, heights[0].length);
    Scale zScale = new Scale(min, max, 0, 255);
    
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        
        int mx = (int) xScale.scale(x);
        int my = (int) yScale.scale(y);
        
        int color = (int) (zScale.scale(heights[mx][my]));
                
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
