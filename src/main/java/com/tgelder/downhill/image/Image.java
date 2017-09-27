package com.tgelder.downhill.image;

import java.awt.Color;
import java.io.IOException;

public interface Image {

  public void setColor(int r, int g, int b);
  
  public void setColor(Color color);

  public void drawPoint(int x, int y);

  public void drawLine(int ax, int ay, int bx, int by);

  public void save(String location) throws IOException;
  
  public int getWidth();
  
  public int getHeight();

}
