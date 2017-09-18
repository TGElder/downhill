package com.tgelder.downhill;

import com.tgelder.downhill.image.Image;

public class MeshTriangleRenderer {

  private final TriangleRenderer renderer = new TriangleRenderer();

  public void render(Mesh mesh, Image image) {
    TriangleIterator iterator = new TriangleIterator(mesh);

    while (iterator.hasNext()) {
      MeshTriangle triangle = iterator.next();
      renderer.render(triangle, image);
    }

  }

}
