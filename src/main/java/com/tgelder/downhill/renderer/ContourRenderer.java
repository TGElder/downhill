package com.tgelder.downhill.renderer;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;

import java.awt.*;

public class ContourRenderer {

  private static final short[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  private static final short[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

  public static void render(double[][] heights, double interval, double min, double max, Image image) {

    Scale xScale = new Scale(0, image.getWidth(), 0, heights.length);
    Scale yScale = new Scale(0, image.getHeight(), 0, heights[0].length);
    Scale zScale = new Scale(min, max, 0, 255);

    image.setColor(Color.WHITE);
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        image.drawPoint(x, y);
      }
    }

    image.setColor(Color.BLACK);
    for (double c = min; c < max; c += interval) {

      for (int x = 0; x < image.getWidth(); x++) {
        for (int y = 0; y < image.getHeight(); y++) {

          if (contour(heights, c, x, y)) {
            image.drawPoint(x, y);
          }
        }
      }
    }

  }

  private static boolean contour(double[][] heights, double level, int x, int y) {

    double z = heights[x][y];

    if (z >= level) {

      for (int n = 0; n < dx.length; n++) {

        int dx = ContourRenderer.dx[n];
        int dy = ContourRenderer.dy[n];

        if (dx != 0 || dy != 0) {
          int nx = x + dx;
          int ny = y + dy;
          if (inBounds(heights, nx, ny)) {

            double nz = heights[nx][ny];

            if (nz < level) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  private static boolean inBounds(double[][] heights, int x, int y) {
    return x > 0 && x < heights.length && y > 0 && y < heights[0].length;
  }


}
