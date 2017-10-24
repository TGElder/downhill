package com.tgelder.downhill.terrain;

import com.tgelder.downhill.TestUtils;
import com.tgelder.downhill.geometry.Scale;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MeshZScalerTest {

  @Test
  public void testScale() throws DownhillException {
    Mesh mesh = new Mesh(4);
    double[][] values = {{53, 11, 13, 38}, {8, 50, 28, 33}, {10, 15, 18, 10}, {7, 13, 17, 16}};
    mesh.setZ(values);

    double[][] actual =
            MeshZScaler
                    .scale(mesh, new Scale(mesh.getMinZ(), mesh.getMaxZ(), 0, 1000))
                    .getZ();

    double[][] expected = {
            {1000.00000, 86.95652, 130.43478, 673.91304},
            {21.73913, 934.78261, 456.52174, 565.21739},
            {65.21739, 173.91304, 239.13043, 65.21739},
            {0.00000, 130.43478, 217.39130, 195.65217}};


    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        assertEquals("Z at " + x + ", " + y, expected[x][y], actual[x][y], TestUtils.PRECISION);
      }
    }

  }

}
