package com.tgelder.downhill.mesh;

import java.util.Random;

import lombok.Getter;

public class Mesh {

  public static final double MAX_VALUE = Float.MAX_VALUE;
  public static final double MIN_VALUE = Float.MIN_VALUE;
  
  private static final Random random = new Random(7779);
  
  private static final short [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  private static final short [] dy = {0, -1, -1, -1, 0, 1, 1, 1};
  
  @Getter
  private final int width;
  private final double[][] z;
  private final short downhill[][];
  private final int flow[][];

  public Mesh(int width) {
    this.width = width;
    z = new double[width][width];
    downhill = new short[width][width];
    flow = new int[width][width];
  }


  public final double getZ(int x, int y) {
    return z[x][y];
  }
  
  public boolean inBounds(int x, int y){
    return x >= 0 && y >= 0 && x < width && y < width;
  }
  
  public double getZ(int x, int y, int outOfBoundsValue) {
    if (inBounds(x, y)) {
      return getZ(x, y);
    }
    else {
      return outOfBoundsValue;
    }
  }
  
  public final short getDownhill(int x, int y) {
    return downhill[x][y];
  }
  
  public final int getFlow(int x, int y) {
    return flow[x][y];
  }

  public final void setZ(int x, int y, double value) {
    z[x][y] = value;
  }
  
  public final void setDownhill(int x, int y, short value) {
    downhill[x][y] = value;
  }
  
  public final void setFlow(int x, int y, int value) {
    flow[x][y] = value;
  }

  public void setAllZ(double value) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        setZ(x, y, value);
      }
    }
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
      ox = vertex.getX();
      oy = vertex.getY();
      set(ox, oy, out);
    }
    
    out.computeDirections();
    out.computeFlow();
    
    return out;
  }
  
  private void set(int ox, int oy, Mesh next) {

    double minZ = Mesh.getVertexCaseAt(ox, oy).getMinZ(this, ox, oy);
    double maxZ = Mesh.getVertexCaseAt(ox, oy).getMaxZ(this, ox, oy);
    
    if (maxZ < minZ) {
      minZ = maxZ;
    }
    
    double r = random.nextFloat();
    
    double range = (maxZ - minZ);
    
    
    double z = minZ + range * (0.1 + 0.8 * r);
    
    next.setZ(ox, oy, z);
    
    next.setDownhill(ox, oy, getDownhill(ox / 2, oy / 2));
    
  }
  
  public void computeDirections() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        computeDirection(x, y);
      }
    }
  }
  
  public void computeFlow() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        computeFlow(x, y);
      }
    }
  }
  
  private void computeDirection(int x, int y) {
    
    double minZ = z[x][y];
       
    for (short d = 0; d < 8; d++) {
      final double focusZ = getZ(x + dx[d], y + dy[d], 0);
      if (focusZ < minZ) {
        downhill[x][y] = d;
        minZ = focusZ;
      }
    }
  }
  

  
  private void computeFlow(int x, int y) {
        
    if (inBounds(x, y)) {
      final short d = downhill[x][y];

      flow[x][y] ++;
      computeFlow(x + dx[d], y + dy[d]);
    }
    else {
      return;
    }
  }
 
  
  public double getMaxZ() {
    double out = Mesh.MIN_VALUE;
    for (int x=0; x<width; x++) {
      for (int y=0; y<width; y++) {
        out = Math.max(out, getZ(x, y));
      }
    }
    return out;
  }
  
  

}
