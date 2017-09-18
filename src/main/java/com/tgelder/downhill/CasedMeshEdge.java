package com.tgelder.downhill;

public class CasedMeshEdge extends MeshEdge {
  
  private EdgeCase edgeCase;
  
  public CasedMeshEdge(Mesh mesh) {
    super(mesh);
  }
  
  public EdgeCase getEdgeCase() {
    return edgeCase;
  }

  public void setEdgeCase(EdgeCase edgeCase) {
    this.edgeCase = edgeCase;
  }

}