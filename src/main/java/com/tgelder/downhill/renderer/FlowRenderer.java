package com.tgelder.downhill.renderer;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlowRenderer {

  private final float threshold;
  
  public void render(int[][] flow, Image image) {
    Scale xScale = new Scale(0, image.getWidth(), 0, flow.length);
    Scale yScale = new Scale(0, image.getHeight(), 0, flow[0].length);
     
    image.setColor(Color.BLUE);
    
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        
        int mx = (int) xScale.scale(x);
        int my = (int) yScale.scale(y);
        
        if (flow[mx][my] >= threshold) {
          image.drawPoint(x, y);
        }
      }
    }
  }


}
