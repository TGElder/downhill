package com.tgelder.downhill.edgesplitters;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.rngs.RNG;

public class SubpeakEdgeSplitter implements EdgeSplitter {

  private final RNG rng;
  
  private Mesh mesh;
  private MeshPoint a;
  private MeshPoint b;
  private MeshPoint c;
  private MeshPoint d;
  private float min, max;
  private float r;
  private float split;
  private final Scale scale;
  
  public SubpeakEdgeSplitter(RNG rng, float min, float max) {
    this.rng = rng;
    scale = new Scale(0, 1, min, max);
  }

  @Override
  public float split(MeshEdge edge) {
    
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
        max = Math.max(max, Math.max(c.getZ(), d.getZ()));
//        min = Math.min(min, Math.min(c.getZ(), d.getZ()));
        break;
      case FORWARDSLASH:
//        min = Math.min(min, Math.min(c.getZ(), d.getZ()));
        break;
      default:
        break;
    }
    
    r = scale.scale(rng.getNext());
    
    return min * r + max * (1 - r);
    
//    if (!a.equals(b) && !(a.atEdge() && b.atEdge()) && (split <= min || split >= max)) {
//      return split(edge);
//    }
//    else {
//      return split;
//    }
    
  }
}
