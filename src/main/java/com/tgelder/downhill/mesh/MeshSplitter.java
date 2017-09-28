package com.tgelder.downhill.mesh;

import com.tgelder.downhill.rngs.RNG;

public class MeshSplitter {
  
  private int oWidth;
  private int ox;
  private int oy;
  private int xOffset;
  private int yOffset;
  private int ix;
  private int iy;
  private double minZ;
  private double maxZ;
  private double r;
  private double range;
  private double z;
  
  public Mesh split(Mesh in, RNG rng) {
    oWidth = in.getWidth()*2;
    
    Mesh out = new Mesh(oWidth);
    VertexIterator iterator = new VertexIterator(out);
    
    while (iterator.hasNext()) {      
      final Vertex vertex = iterator.next();
      ox = vertex.getX();
      oy = vertex.getY();

      xOffset = ((ox % 2) * 2) - 1;
      yOffset = ((oy % 2) * 2) - 1;
      
      ix = ox / 2;
      iy = oy / 2;
      
      minZ = Math.min(in.getZ(ix, iy, 0), Math.min(in.getZ(ix + xOffset, iy, 0), in.getZ(ix, iy + yOffset, 0)));
      maxZ = in.getZ(ix, iy, 0);
      
      if (maxZ < minZ) {
        minZ = maxZ;
      }
      
      r = rng.getNext();

      range = (maxZ - minZ);
      
      z = minZ + range * (0.1 + 0.8 * r);
      
      out.setZ(ox, oy, z);
      
      out.setDownhill(ox, oy, in.getDownhill(ix, iy));
    }
    
    return out;
  }
  
}
