package com.tgelder.downhill.edgesplitters;

import com.tgelder.downhill.CasedMeshEdge;

public interface EdgeSplitter {
  
  public float split(CasedMeshEdge edge);

}
