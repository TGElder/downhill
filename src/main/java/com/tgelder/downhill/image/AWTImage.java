package com.tgelder.downhill.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.Getter;

public class AWTImage implements Image {

  @Getter
  private final int width;
  @Getter
  private final int height;
  private final BufferedImage image;
  private final Graphics2D canvas;

  public AWTImage(int width, int height) {
    this.width = width;
    this.height = height;
    
    GraphicsConfiguration GFX_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
        .getDefaultConfiguration();

    image = GFX_CONFIG.createCompatibleImage(width, height, Transparency.OPAQUE);
    canvas = image.createGraphics();
  }

  @Override
  public void setColor(int r, int g, int b) {
    canvas.setColor(new Color(r, g, b));
  }
  
  @Override
  public void setColor(Color color) {
    canvas.setColor(color);
  }

  @Override
  public void drawPoint(int x, int y) {
    drawLine(x, y, x, y);
  }

  @Override
  public void drawLine(int ax, int ay, int bx, int by) {
    canvas.drawLine(ax, ay, bx, by);
  }

  @Override
  public void save(String location) throws IOException {
    File file = new File(location + ".png");
    ImageIO.write(image, "png", file);
  }

}
