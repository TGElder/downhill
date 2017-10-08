package com.tgelder.downhill.renderers;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import com.tgelder.downhill.image.MockImage;
import com.tgelder.downhill.renderer.FlowRenderer;

public class FlowRendererTest {

  @Test
  public void testFlowRenderer() {
    
    int[][] flow = { { 6, 1, 2, 1 }, { 1, 5, 2, 1 }, { 4, 3, 2, 1 }, { 5, 6, 7, 8 } };

    MockImage image = new MockImage(4);
    
    image.setColor(Color.BLACK);
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        image.drawPoint(x, y);
      }
    }

    FlowRenderer flowRenderer = new FlowRenderer(5);
    flowRenderer.render(flow, image);

    int[][] expectedB = { { 255, 0, 0, 0 }, { 0, 255, 0, 0 }, { 0, 0, 0, 0 }, { 255, 255, 255, 255 } };
       
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("R " + x + ", " + y, 0, image.getR(x, y), 0);
        assertEquals("G " + x + ", " + y, 0, image.getG(x, y), 0);
        assertEquals("B " + x + ", " + y, expectedB[x][y], image.getB(x, y), 0);
      }
    }
    
  }
  
}
