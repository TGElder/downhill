package com.tgelder.downhill.image;

import java.io.IOException;

public class MockImage implements Image {

  private final int size;
  private final int[][] rs, gs, bs;
  private int r, g, b;

  public MockImage(int size) {
    this.size = size;
    rs = new int[size][size];
    gs = new int[size][size];
    bs = new int[size][size];
  }

  @Override
  public void setColor(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public void drawPoint(int x, int y) {
    rs[x][y] = r;
    gs[x][y] = g;
    bs[x][y] = b;
  }

  @Override
  public void drawLine(int ax, int ay, int bx, int by) {
  }

  @Override
  public void save(String location) throws IOException {
  }

  @Override
  public int getWidth() {
    return size;
  }

  @Override
  public int getHeight() {
    return size;
  }
  
  public int getR(int x, int y) {
    return rs[x][y];
  }
  
  public int getG(int x, int y) {
    return gs[x][y];
  }
  
  public int getB(int x, int y) {
    return bs[x][y];
  }

}
