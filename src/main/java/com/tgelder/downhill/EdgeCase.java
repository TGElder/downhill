package com.tgelder.downhill;

public enum EdgeCase{
  POINT{
    @Override
    boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 == 0);
    }

    @Override
    void setEdge(CasedMeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, x / 2, y / 2);
      meshEdge.setEdgeCase(this);
    }
    
  }, 
  HORIZONTAL{
    @Override
    boolean appliesAt(int x, int y) {
      return (x % 2 != 0) && (y % 2 == 0);
    }

    @Override
    void setEdge(CasedMeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, (x / 2) + 1, y / 2);
      meshEdge.setEdgeCase(this);
    }
  },
  VERTICAL{
    @Override
    boolean appliesAt(int x, int y) {
      return (x % 2 == 0) && (y % 2 != 0);
    }

    @Override
    void setEdge(CasedMeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, x / 2, (y / 2) + 1);
      meshEdge.setEdgeCase(this);
    }
  },
  FORWARDSLASH{
    
    @Override
    boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) != (y % 4 == 1));
    }

    @Override
    void setEdge(CasedMeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, (y / 2) + 1, (x / 2) + 1, y / 2);
      meshEdge.setEdgeCase(this);
    }
    
  },
  BACKSLASH{
    @Override
    boolean appliesAt(int x, int y) {
      return ((x % 4 == 1) == (y % 4 == 1));
    }

    @Override
    void setEdge(CasedMeshEdge meshEdge, int x, int y) {
      meshEdge.set(x / 2, y / 2, (x / 2) + 1, (y / 2) + 1);
      meshEdge.setEdgeCase(this);
    }
  };
  
  abstract boolean appliesAt(int x, int y);

  abstract void setEdge(CasedMeshEdge meshEdge, int x, int y);
}