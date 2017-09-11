package com.tgelder.downhill;

import lombok.Getter;

public class Mesh {

  @Getter
  private final int width;
  private final float[][] x;
  private final float[][] y;
  private final float[][] z;

  public Mesh(int width) {
    this.width = width;
    x = new float[width][width];
    y = new float[width][width];
    z = new float[width][width];
  }

  public final float getX(int mx, int my) {
    return x[mx][my];
  }

  public final float getY(int mx, int my) {
    return y[mx][my];
  }

  public final float getZ(int mx, int my) {
    return z[mx][my];
  }

  public final void setX(int mx, int my, float value) {
    x[mx][my] = value;
  }

  public final void setY(int mx, int my, float value) {
    y[mx][my] = value;
  }

  public final void setZ(int mx, int my, float value) {
    z[mx][my] = value;
  }

  public final void set(int mx, int my, float x, float y, float z) {
    setX(mx, my, x);
    setY(mx, my, y);
    setZ(mx, my, z);
  }

  public static Mesh of3x3() {
    Mesh out = new Mesh(3);

    float max = 512f;
    float min = 0f;

    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        out.set(x, y, min + (max * x), min + (max * y), 0);
      }
    }

    out.setZ(1, 1, max);

    return out;
  }

}
