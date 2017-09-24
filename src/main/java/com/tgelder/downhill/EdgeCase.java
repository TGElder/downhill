package com.tgelder.downhill;

public enum EdgeCase{
  POINT{
    @Override
    boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() == meshEdge.getB().getMy());
    }
    
    @Override
    boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, x / 2, y / 2);
    }
    
  }, 
  HORIZONTAL{
    @Override
    boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() != meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() == meshEdge.getB().getMy());
    }
    
    @Override
    boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, (x / 2) + 1, y / 2);
    }
  },
  VERTICAL{
    @Override
    boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() != meshEdge.getB().getMy());
    }
    
    @Override
    boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }
    
    @Override
    void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, x / 2, (y / 2) + 1);
    }
  },
  FORWARDSLASH{
    @Override
    boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() + 1 == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() -1 == meshEdge.getB().getMy());
    }
    
    @Override
    boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) != (y % 4 == 1));
    }

    @Override
    void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, (y / 2) + 1, (x / 2) + 1, y / 2);
    }
    
  },
  BACKSLASH{
    @Override
    boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() + 1 == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() + 1 == meshEdge.getB().getMy());
    }
    
    @Override
    boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) == (y % 4 == 1));
    }

    @Override
    void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, (x / 2) + 1, (y / 2) + 1);
    }
  };
  
  abstract boolean appliesTo(MeshEdge edge);  
  abstract boolean appliesAt(int x, int y);
  abstract void setEdge(MeshEdge meshEdge, int x, int y);
}