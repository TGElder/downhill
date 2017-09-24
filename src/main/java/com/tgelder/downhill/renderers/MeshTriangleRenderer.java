package com.tgelder.downhill.renderers;

import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshTriangle;
import com.tgelder.downhill.mesh.iterators.TriangleIterator;

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
