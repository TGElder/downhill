package com.tgelder.downhill.renderer;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.util.Arrays;

public class FlowProbabilityRenderer {

  private final Color [] colors = new Color[256];

  public FlowProbabilityRenderer() {
    for (int i = 0; i < 256; i++) {
      colors[i] = new Color(i, i, i);
    }
  }

  public void render(double[][] flow, Image image) {
    final Scale xScale = new Scale(0, image.getWidth(), 0, flow.length);
    final Scale yScale = new Scale(0, image.getHeight(), 0, flow[0].length);
     
    final double max = getMax(flow);
    
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        
        int mx = (int) xScale.scale(x);
        int my = (int) yScale.scale(y);

        image.setColor(colors[(int)((flow[mx][my] * 255) / max)]);

        image.drawPoint(x, y);
      }
    }
  }

  private static double getMax(double[][] flow) {
    double out = flow[0][0];
    for (double[] fs : flow) {
      for (double f : fs) {
        out = Math.max(out, f);
      }
    }
    return out;
  }


}
