package com.tgelder.downhill.mesh;

public enum VertexCase{
  TOPLEFT{

    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    public double getMinZ(Mesh parent, int x, int y) {
      return getMinZ(parent, x, y, -1, -1);
    }

  }, 
  TOPRIGHT{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    public double getMinZ(Mesh parent, int x, int y) {
      return getMinZ(parent, x, y, 1, -1);
    }

  },
  LOWERLEFT{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }

    @Override
    public double getMinZ(Mesh parent, int x, int y) {
      return getMinZ(parent, x, y, -1, 1);
    }

   
  },
  LOWERRIGHT{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 != 0);
    }

    @Override
    public double getMinZ(Mesh parent, int x, int y) {
      return getMinZ(parent, x, y, 1, 1);
    }
  
  };
  
  abstract public boolean appliesAt(int x, int y);
  public abstract double getMinZ(Mesh parent, int x, int y);
  public double getMaxZ(Mesh parent, int x, int y) {
    return parent.getZ(x / 2, y / 2); 
  }
  
  private static double getMinZ(Mesh parent, int x, int y, int xOffset, int yOffset) {
    return Math.min(
             Math.min(
               parent.getZ((x / 2) + xOffset, (y / 2), 0), 
               parent.getZ((x / 2), (y / 2) + yOffset, 0)
             ),
             parent.getZ(x / 2, y / 2)
           );
  }
  

}