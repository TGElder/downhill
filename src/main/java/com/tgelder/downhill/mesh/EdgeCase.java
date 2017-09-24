package com.tgelder.downhill.mesh;

public enum EdgeCase{
  POINT{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int edgeX, int edgeY) {
      return (edgeX % 2 == 0) && (edgeY % 2 == 0);
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int edgeX, int edgeY) {
      meshEdge.set(edgeX / 2, edgeY / 2, edgeX / 2, edgeY / 2);
    }

    @Override
    public int getEdgeX(int amx) {
      return amx * 2;
    }

    @Override
    public int getEdgeY(int amy) {
      return amy * 2;
    }
    
  }, 
  HORIZONTAL{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() != meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int edgeX, int edgeY) {
      return (edgeX % 2 != 0) && (edgeY % 2 == 0);
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int edgeX, int edgeY) {
      meshEdge.set(edgeX / 2, edgeY / 2, (edgeX / 2) + 1, edgeY / 2);
    }
    
    @Override
    public int getEdgeX(int amx) {
      return amx * 2 + 1;
    }

    @Override
    public int getEdgeY(int amy) {
      return amy * 2;
    }
  },
  VERTICAL{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() != meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int edgeX, int edgeY) {
      return (edgeX % 2 == 0) && (edgeY % 2 != 0);
    }
    
    @Override
    public void setEdge(MeshEdge meshEdge, int edgeX, int edgeY) {
      meshEdge.set(edgeX / 2, edgeY / 2, edgeX / 2, (edgeY / 2) + 1);
    }
    
    @Override
    public int getEdgeX(int amx) {
      return amx * 2;
    }

    @Override
    public int getEdgeY(int amy) {
      return amy * 2 + 1;
    }
  },
  FORWARDSLASH{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() + 1 == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() -1 == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int edgeX, int edgeY) {
      return ((edgeX % 4 == 1) != (edgeY % 4 == 1));
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int edgeX, int edgeY) {
      meshEdge.set(edgeX / 2, (edgeY / 2) + 1, (edgeX / 2) + 1, edgeY / 2);
    }
    
    @Override
    public int getEdgeX(int amx) {
      return amx * 2 + 1;
    }

    @Override
    public int getEdgeY(int amy) {
      return (amy - 1) * 2 + 1;
    }
    
  },
  BACKSLASH{
    @Override
    public boolean appliesTo(MeshEdge meshEdge) {
      return (meshEdge.getA().getMx() + 1 == meshEdge.getB().getMx())
          && (meshEdge.getA().getMy() + 1 == meshEdge.getB().getMy());
    }
    
    @Override
    public boolean appliesAt(int edgeX, int edgeY) {
      return ((edgeX % 4 == 1) == (edgeY % 4 == 1));
    }

    @Override
    public void setEdge(MeshEdge meshEdge, int edgeX, int edgeY) {
      meshEdge.set(edgeX / 2, edgeY / 2, (edgeX / 2) + 1, (edgeY / 2) + 1);
    }
    
    @Override
    public int getEdgeX(int amx) {
      return amx * 2 + 1;
    }

    @Override
    public int getEdgeY(int amy) {
      return amy * 2 + 1;
    }
  };
  
  abstract public boolean appliesTo(MeshEdge edge);  
  abstract public boolean appliesAt(int edgeX, int edgeY);
  abstract public void setEdge(MeshEdge meshEdge, int edgeX, int edgeY);
  abstract public int getEdgeX(int amx);
  abstract public int getEdgeY(int amy);
}