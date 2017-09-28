package com.tgelder.downhill.mesh;

import com.tgelder.downhill.rngs.RNG;

public class MeshSplitter {
  
  public Mesh split(Mesh in, RNG rng) {
    int oWidth = in.getWidth()*2;
    
    Vertex vertex;
    int ox = 0;
    int oy = 0;
    
    Mesh out = new Mesh(oWidth);
    
    VertexIterator iterator = new VertexIterator(out);
    
    while (iterator.hasNext()) {      
      vertex = iterator.next();
      ox = vertex.getX();
      oy = vertex.getY();

      int xOffset = ((ox % 2) * 2) - 1;
      int yOffset = ((oy % 2) * 2) - 1;
      
      int ix = ox / 2;
      int iy = oy / 2;
      
      double minZ = Math.min(in.getZ(ix, iy, 0), Math.min(in.getZ(ix + xOffset, iy, 0), in.getZ(ix, iy + yOffset, 0)));
      double maxZ = in.getZ(ix, iy, 0);
      
      if (maxZ < minZ) {
        minZ = maxZ;
      }
      
      double r = rng.getNext();

      double range = (maxZ - minZ);
      
      double z = minZ + range * (0.1 + 0.8 * r);
      
      out.setZ(ox, oy, z);
      
      out.setDownhill(ox, oy, in.getDownhill(ix, iy));
    }
    
    return out;
  }
  
}
