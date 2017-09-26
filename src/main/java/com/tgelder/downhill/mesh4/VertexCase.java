package com.tgelder.downhill.mesh4;

public enum VertexCase{
  TOPLEFT{

    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(getZOrZero(parent, (x/2) - 1, (y/2)), getZOrZero(parent, (x/2), (y/2) -1 ));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      return parent.getZ(x / 2, y / 2);
    }

  }, 
  TOPRIGHT{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(getZOrZero(parent, (x/2) + 1, (y/2)), getZOrZero(parent, (x/2), (y/2) -1 ));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      return parent.getZ(x / 2, y / 2);
    }

  },
  LOWERLEFT{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(getZOrZero(parent, (x/2) - 1, (y/2)), getZOrZero(parent, (x/2), (y/2) + 1 ));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      return parent.getZ(x / 2, y / 2);
    }
  },
  LOWERRIGHT{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 != 0);
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(getZOrZero(parent, (x/2) + 1, (y/2)), getZOrZero(parent, (x/2), (y/2) + 1 ));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
       return parent.getZ(x / 2, y / 2);
    }
  
  };
  
  abstract public boolean appliesAt(int x, int y);
  abstract public float getMinZ(Mesh current, Mesh parent, int x, int y);
  abstract public float getMaxZ(Mesh current, Mesh parent, int x, int y);
  
  private static float getZOrZero(Mesh mesh, int x, int y) {
    if (inBounds(mesh, x, y)) {
      return mesh.getZ(x, y);
    }
    else {
      return 0;
    }
  }
  
  private static boolean inBounds(Mesh mesh, int x, int y) {
    return x>=0 && y>=0 && x<mesh.getWidth() && y<mesh.getWidth();
  }

}