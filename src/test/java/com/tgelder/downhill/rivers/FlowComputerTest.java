package com.tgelder.downhill.rivers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.TestUtils;
import com.tgelder.downhill.edgesplitters.MidpointEdgeSplitter;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshPoint;

public class FlowComputerTest {
  
  @Test
  public void test3x3Pyramid() throws FlowException {
    
    MidpointEdgeSplitter xSplitter = new MidpointEdgeSplitter(MeshPoint::getX);
    MidpointEdgeSplitter ySplitter = new MidpointEdgeSplitter(MeshPoint::getY);
    MidpointEdgeSplitter zSplitter = new MidpointEdgeSplitter(MeshPoint::getZ);
    Mesh mesh = Mesh.of3x3().split(xSplitter, ySplitter, zSplitter);
    
    for (int x=0; x<5; x++) {
      for (int y=0; y<5; y++) {
        mesh.setZ(x, y, 1);
      }
    }
    
    mesh.setZ(2, 3, 0.9f);
    mesh.setZ(3, 2, 0.8f);
    mesh.setZ(2, 2, 0.7f);
    
    mesh.setZ(1, 2, 0.6f);
    mesh.setZ(1, 1, 0.5f);
    mesh.setZ(2, 1, 0.4f);
    mesh.setZ(3, 1, 0.3f);
    
    mesh.setZ(2, 0, 0.2f);
    mesh.setZ(3, 0, 0.1f);
    mesh.setZ(4, 0, 0.1f);
    mesh.setZ(4, 1, 0.0f);

    float[][] expected = new float[8][8];
    expected[5][5] = 1f;
    expected[5][3] = 1f;
    expected[7][2] = 1f;
    
    FlowComputer computer = new FlowComputer(mesh);
    computer.rain(new MeshPoint(mesh, 3, 3), 1);
    
    float actual[][] = computer.getFlow();
    
    for (int x=0; x<8; x++) {
      for (int y=0; y<8; y++) {
        System.out.println(x+", "+y);
        assertEquals(expected[x][y], actual[x][y], TestUtils.PRECISION);
      }
    }
    
    
  }

}
