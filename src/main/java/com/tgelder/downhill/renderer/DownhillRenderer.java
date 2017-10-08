package com.tgelder.downhill.renderer;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;

public class DownhillRenderer {
  
  private final Color [] colors = new Color[8];
  
  public DownhillRenderer() {
    colors[0] = new Color(127, 127, 127);
    colors[1] = new Color(63, 63, 63);
    colors[2] = new Color(0, 0, 0);
    colors[3] = new Color(63, 63, 63);
    colors[4] = new Color(127, 127, 127);
    colors[5] = new Color(191, 191, 191);
    colors[6] = new Color(255, 255, 255);
    colors[7] = new Color(191, 191, 191);
  }
  
  public void render(short[][] downhill, Image image) {
    Scale xScale = new Scale(0, image.getWidth(), 0, downhill.length);
    Scale yScale = new Scale(0, image.getHeight(), 0, downhill[0].length);
     
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        
        int mx = (int) xScale.scale(x);
        int my = (int) yScale.scale(y);
        
        image.setColor(colors[downhill[mx][my]]);

        image.drawPoint(x, y);
      }
    }
  }

}
