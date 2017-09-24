package com.tgelder.downhill;

import com.tgelder.downhill.image.Image;

public class MeshMaskRenderer {

  private final MaskRenderer renderer = new MaskRenderer();

  public void render(Mesh mesh, Image image) {
    TriangleIterator iterator = new TriangleIterator(mesh);

    while (iterator.hasNext()) {
      MeshTriangle triangle = iterator.next();
      renderer.render(triangle, image);
    }

  }

}
