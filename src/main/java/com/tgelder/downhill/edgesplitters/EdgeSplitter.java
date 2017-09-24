package com.tgelder.downhill.edgesplitters;

import com.tgelder.downhill.mesh.MeshEdge;

public interface EdgeSplitter {
  
  public float split(MeshEdge edge);

}
