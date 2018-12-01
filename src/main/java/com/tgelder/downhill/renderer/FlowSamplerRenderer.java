package com.tgelder.downhill.renderer;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public class FlowSamplerRenderer {

  private final int threshold;
  
  public void render(int[][][] flow, Image image) {
    Scale xScale = new Scale(0, image.getWidth(), 0, flow.length);
    Scale yScale = new Scale(0, image.getHeight(), 0, flow[0].length);
     
    image.setColor(Color.BLUE);
    
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        
        int mx = (int) xScale.scale(x);
        int my = (int) yScale.scale(y);

        for (int[][] f : flow) {
          if (f[x][y] >= threshold) {
            image.drawPoint(x, y);
            break;
          }
        }
      }
    }
  }


}
