package com.tgelder.downhill.mesh4;

public enum VertexCase{
  EXISTING{

    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    public float getX(Mesh current, Mesh parent, int x, int y) {
      return parent.getX(x / 2, y / 2);
    }

    @Override
    public float getY(Mesh current, Mesh parent, int x, int y) {
      return parent.getY(x / 2, y / 2);
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return parent.getZ(x / 2, y / 2);
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      return parent.getZ(x / 2, y / 2);
    }

  }, 
  HORIZONTAL{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    public float getX(Mesh current, Mesh parent, int x, int y) {
      return current.getX(x - 1, y)/2f + current.getX(x + 1, y)/2f;
    }

    @Override
    public float getY(Mesh current, Mesh parent, int x, int y) {
      return current.getY(x - 1, y);
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(current.getZ(x - 1, y), current.getZ(x + 1, y));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      return Math.max(current.getZ(x - 1, y), current.getZ(x + 1, y));
    }

  },
  VERTICAL{
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }
    
    @Override
    public float getX(Mesh current, Mesh parent, int x, int y) {
      return current.getX(x, y - 1);
    }

    @Override
    public float getY(Mesh current, Mesh parent, int x, int y) {
      return current.getY(x, y - 1)/2f + current.getY(x, y + 1)/2f;
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(current.getZ(x, y - 1), current.getZ(x, y + 1));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      return Math.max(current.getZ(x, y - 1), current.getZ(x, y + 1));
    }
  },
  CENTRE{
    @Override
    public boolean appliesAt(int x, int y) {
      return ((x % 2 != 0) != (y % 2 != 1));
    }
    
    @Override
    public float getX(Mesh current, Mesh parent, int x, int y) {
      return current.getX(x - 1, y)/2f + current.getX(x + 1, y)/2f;
    }

    @Override
    public float getY(Mesh current, Mesh parent, int x, int y) {
      return current.getY(x, y - 1)/2f + current.getY(x, y + 1)/2f;
    }

    @Override
    public float getMinZ(Mesh current, Mesh parent, int x, int y) {
      return Math.min(
          Math.min(current.getZ(x - 1, y), current.getZ(x + 1, y)),
          Math.min(current.getZ(x, y - 1), current.getZ(x, y + 1)));
    }

    @Override
    public float getMaxZ(Mesh current, Mesh parent, int x, int y) {
      float r = 1/(current.getWidth() - 1);
      
      return Math.max(Mesh.MAX_VALUE * r + Mesh.MIN_VALUE*(1 + r),
          
            Math.max(
            Math.max(current.getZ(x - 1, y), current.getZ(x + 1, y)),
            Math.max(current.getZ(x, y - 1), current.getZ(x, y + 1))));
//            
//            Mesh.MAX_VALUE * r + Mesh.MIN_VALUE*(1 + r));
////          
////            Math.max(
////                Math.max(current.getZ(x - 1, y - 1), current.getZ(x - 1, y + 1)),
////                Math.max(current.getZ(x + 1, y + 1), current.getZ(x + 1, y - 1)))
////            );
            
    }

    
  
  };
  
  abstract public boolean appliesAt(int x, int y);
  abstract public float getX(Mesh current, Mesh parent, int x, int y);
  abstract public float getY(Mesh current, Mesh parent, int x, int y);
  abstract public float getMinZ(Mesh current, Mesh parent, int x, int y);
  abstract public float getMaxZ(Mesh current, Mesh parent, int x, int y);

}