package com.tgelder.downhill.geometry;

public class Scale {
  
  private double outMin;
  private double inMin;

  private double factor;
  
  public Scale() {
    
  }
  
  public Scale(double inMin, double inMax, double outMin, double outMax) {
    set(inMin, inMax, outMin, outMax);
  }
  
  public void set(double inMin, double inMax, double outMin, double outMax) {
    this.inMin = inMin;
    this.outMin = outMin;
    
    factor = (outMax - outMin)/(inMax - inMin);
  }
  
  public double scale(double value) {
    return (value - inMin)*factor + outMin;
  }
  
}
