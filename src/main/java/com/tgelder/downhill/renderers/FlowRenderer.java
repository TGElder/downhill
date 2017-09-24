package com.tgelder.downhill.renderers;

import java.awt.Color;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.iterators.EdgeIterator;
import com.tgelder.downhill.rivers.FlowComputer;

public class FlowRenderer {

  private final Color color;

  public FlowRenderer(Color color) {
    this.color = color;
  }

  public void render(FlowComputer flowComputer, Image image, float percentageThreshold) {
    
    float flowThreshold = (float)(Math.pow(flowComputer.getMesh().getWidth(), 2) * percentageThreshold);
    
    EdgeIterator iterator = new EdgeIterator(flowComputer.getMesh());

    Scale xScale = new Scale(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, image.getWidth() - 1);
    Scale yScale = new Scale(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, image.getHeight() - 1);
    
    image.setColor(color.getRed(), color.getGreen(), color.getBlue());
    
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
      
      if (flowComputer.getFlow()[edge.getEdgeX()][edge.getEdgeY()] >= flowThreshold) {
        image.drawLine(
            (int)xScale.scale(edge.getA().getX()),
            (int)yScale.scale(edge.getA().getY()),
            (int)xScale.scale(edge.getB().getX()),
            (int)yScale.scale(edge.getB().getY()));
      }
    }

  }

}
