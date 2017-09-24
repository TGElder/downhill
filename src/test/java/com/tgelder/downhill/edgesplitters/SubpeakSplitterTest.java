package com.tgelder.downhill.edgesplitters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.MeshPoint;
import com.tgelder.downhill.rngs.MockRNG;

public class SubpeakSplitterTest {
  
  private static final float PRECISION = 0.00001f;
  
  private Mesh mesh;
  private SubpeakEdgeSplitter splitter;
  
  @Before
  public void setUp() {
    mesh = Mesh.of3x3();
    mesh.setZ(0, 0, 1);
    mesh.setZ(1, 0, 2);
    mesh.setZ(2, 0, 3);
    mesh.setZ(0, 1, 4);
    mesh.setZ(1, 1, 5);
    mesh.setZ(2, 1, 1);
    mesh.setZ(0, 2, 2);
    mesh.setZ(1, 2, 3);
    mesh.setZ(2, 2, 4);
    
    float [] numbers = {0.4f};
    splitter = new SubpeakEdgeSplitter(new MockRNG(numbers));
  }
  
  @Test
  public void splitForwardSlash() {
    float min = 1;
    float max = 5;
    
    MeshPoint a = new MeshPoint(mesh, 1, 1);
    MeshPoint b = new MeshPoint(mesh, 2, 0);
    
    MeshEdge edge = new MeshEdge(a, b);
    
    assertEquals(splitter.split(edge), min*0.4f + max*(1- 0.4f), PRECISION);
  }
  
  @Test
  public void splitBackSlash() {
    float min = 1;
    float max = 5;
    
    MeshPoint a = new MeshPoint(mesh, 1, 1);
    MeshPoint b = new MeshPoint(mesh, 2, 2);
    
    MeshEdge edge = new MeshEdge(a, b);
    
    assertEquals(splitter.split(edge), min*0.4f + max*(1- 0.4f), PRECISION);
  }
  
  @Test
  public void splitPoint() {
    MeshPoint a = new MeshPoint(mesh, 1, 1);
    MeshPoint b = new MeshPoint(mesh, 1, 1);
    
    MeshEdge edge = new MeshEdge(a, b);
    
    assertEquals(splitter.split(edge), 5, PRECISION);
  }
  
  
  @Test
  public void splitHorizontal() {
    float min = 1;
    float max = 2;
    
    MeshPoint a = new MeshPoint(mesh, 0, 0);
    MeshPoint b = new MeshPoint(mesh, 1, 0);
    
    MeshEdge edge = new MeshEdge(a, b);
    
    assertEquals(splitter.split(edge), min*0.4f + max*(1- 0.4f), PRECISION);
  }
  
  @Test
  public void splitVertical() {
    float min = 3;
    float max = 5;
    
    MeshPoint a = new MeshPoint(mesh, 1, 1);
    MeshPoint b = new MeshPoint(mesh, 1, 2);
    
    MeshEdge edge = new MeshEdge(a, b);
    
    assertEquals(splitter.split(edge), min*0.4f + max*(1- 0.4f), PRECISION);
  }
  

}
