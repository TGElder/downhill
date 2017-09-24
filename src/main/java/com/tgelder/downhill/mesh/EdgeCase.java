package com.tgelder.downhill.mesh;

public enum EdgeCase{
  POINT{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, x / 2, y / 2);
    }
    
  }, 
  HORIZONTAL{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() != meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, (x / 2) + 1, y / 2);
    }
  },
  VERTICAL{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() != meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }
    
    @Override
    public void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, x / 2, (y / 2) + 1);
    }
  },
  FORWARDSLASH{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() + 1 == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() -1 == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) != (y % 4 == 1));
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, (y / 2) + 1, (x / 2) + 1, y / 2);
    }
    
  },
  BACKSLASH{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() + 1 == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() + 1 == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) == (y % 4 == 1));
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, (x / 2) + 1, (y / 2) + 1);
    }
  };
  
  abstract public boolean appliesTo(MeshEdge edge);  
  abstract public boolean appliesAt(int x, int y);
  abstract public void setEdge(MeshEdge meshEdge, int x, int y);
}