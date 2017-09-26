package com.tgelder.downhill.mesh4;

import java.util.Random;

import lombok.Getter;

public class Mesh {

  public static final float MAX_VALUE = Float.MAX_VALUE;
  public static final float MIN_VALUE = Float.MIN_VALUE;
  
  private static final Random random = new Random(888);
  
  @Getter
  private final int width;
  private final Float[][] x;
  private final Float[][] y;
  private final Float[][] z;

  public Mesh(int width) {
    this.width = width;
    x = new Float[width][width];
    y = new Float[width][width];
    z = new Float[width][width];
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
  
  public static Mesh of2x2() {
    Mesh out = new Mesh(2);

    float max = MAX_VALUE;
    
    
    for (int x = 0; x < 2; x++) {
      for (int y = 0; y < 2; y++) {
        out.set(x, y, x, y, max);
      }
    }

    return out;
  }

  public static Mesh of3x3() {
    Mesh out = new Mesh(3);

    float max = MAX_VALUE;
    float min = MIN_VALUE;
    
    float [] values = {min, (min/2f) + (max/2f), max};
    
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        out.set(x, y, values[x], values[y], MIN_VALUE);
      }
    }

    out.setZ(1, 1, MAX_VALUE);

    return out;
  }
  
  public static VertexCase getVertexCaseAt(int x, int y) {
    for (VertexCase vertexCase : VertexCase.values()) {
      if (vertexCase.appliesAt(x, y)) {
        return vertexCase;
      }
    }
    return null;
  }
  
  public Mesh split() {
    int oWidth = width*2;
    
    Vertex vertex;
    int ox = 0;
    int oy = 0;
    
    Mesh out = new Mesh(oWidth);
    
    VertexIterator iterator = new VertexIterator(out);
    
    while (iterator.hasNext()) {      
      vertex = iterator.next();
      ox = vertex.getMx();
      oy = vertex.getMy();
      set(ox, oy, out);
    }
    
    return out;
  }
  
  private void set(int ox, int oy, Mesh next) {
    next.setX(ox, oy, ox);
    next.setY(ox, oy, oy);
    
    float minZ = Mesh.getVertexCaseAt(ox, oy).getMinZ(next, this, ox, oy);
    float maxZ = Mesh.getVertexCaseAt(ox, oy).getMaxZ(next, this, ox, oy);
    
    if (maxZ < minZ) {
      minZ = maxZ;
    }
    
    float r = random.nextFloat();
    
    float range = (maxZ - minZ);
    
    
    float z = minZ + (range * r);
    
    next.setZ(ox, oy, z);

  }
  
  public float getMaxZ() {
    float out = Mesh.MIN_VALUE;
    for (int x=0; x<width; x++) {
      for (int y=0; y<width; y++) {
        out = Math.max(out, getZ(x, y));
      }
    }
    return out;
  }
  
  

}
