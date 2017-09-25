package com.tgelder.downhill.renderers;

import java.awt.Color;
import java.util.Collections;
import java.util.Map;

import com.tgelder.downhill.geometry.Scale;
import com.tgelder.downhill.image.Image;
import com.tgelder.downhill.mesh.EdgeCase;
import com.tgelder.downhill.mesh.Mesh;
import com.tgelder.downhill.mesh.MeshEdge;
import com.tgelder.downhill.mesh.iterators.EdgeIterator;

public class MeshLineRenderer {

  private final Color defaultColor;
  private final Map<EdgeCase, Color> colors;
  
  public MeshLineRenderer(Color defaultColor) {
    this.defaultColor = defaultColor;
    this.colors = Collections.emptyMap();
  }
  
  public MeshLineRenderer(Color defaultColor, Map<EdgeCase, Color> colors) {
    this.defaultColor = defaultColor;
    this.colors = colors;
  }
  
  public void render(Mesh mesh, Image image) {
    EdgeIterator iterator = new EdgeIterator(mesh);

    Scale xScale = new Scale(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, image.getWidth() - 1);
    Scale yScale = new Scale(Mesh.MIN_VALUE, Mesh.MAX_VALUE, 0, image.getHeight() - 1);
    
    while (iterator.hasNext()) {
      MeshEdge edge = iterator.next();
      
      Color color = getColor(edge.getEdgeCase());
      image.setColor(color.getRed(), color.getGreen(), color.getBlue());
      
      image.drawLine(
          (int)xScale.scale(edge.getA().getX()),
          (int)yScale.scale(edge.getA().getY()),
          (int)xScale.scale(edge.getB().getX()),
          (int)yScale.scale(edge.getB().getY()));
    }

  }
  
  public Color getColor(EdgeCase edgeCase) {
    Color color = colors.get(edgeCase);
    if (color==null) {
      color = defaultColor;
    }
    return color;
  }

}