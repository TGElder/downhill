package com.tgelder.downhill.edgesplitters;

import com.tgelder.downhill.CasedMeshEdge;
import com.tgelder.downhill.Mesh;
import com.tgelder.downhill.MeshPoint;
import com.tgelder.downhill.RNG;

public class SubpeakEdgeSplitter implements EdgeSplitter {

  private final RNG rng;
  
  private Mesh mesh;
  private MeshPoint a;
  private MeshPoint b;
  private MeshPoint c;
  private MeshPoint d;
  private float min, max;
  private float r;
  
  public SubpeakEdgeSplitter(RNG rng) {
    this.rng = rng;
  }

  @Override
  public float split(CasedMeshEdge edge) {
    
    a = edge.getA();
    b = edge.getB();
    
    mesh = a.getMesh();
    c = new MeshPoint(mesh);
    d = new MeshPoint(mesh); //TODO
    c.set(a.getMx(), b.getMy());
    d.set(b.getMx(), a.getMy());
    
    min = Math.min(a.getZ(), b.getZ());
    max = Math.max(a.getZ(), b.getZ());
    
    switch(edge.getEdgeCase()) {
      case BACKSLASH:
      case FORWARDSLASH:
        min = Math.min(min, Math.min(c.getZ(), d.getZ()));
        break;
      default:
        break;
    }
    
    r = rng.getNext();
    
    return min * r + max * (1 - r);
    
  }
}
