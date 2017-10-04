package com.tgelder.downhill.geometry;

public class Scale {
  
  private double inMin;
  private double inMax;
  private double outMin;
  private double outMax;

  public Scale() {
    
  }
  
  public Scale(double inMin, double inMax, double outMin, double outMax) {
    set(inMin, inMax, outMin, outMax);
  }
  
  public void set(double inMin, double inMax, double outMin, double outMax) {
    this.inMin = inMin;
    this.inMax = inMax;
    this.outMin = outMin;
    this.outMax = outMax;
  }
  
  public double scale(double value) {
    return ((value - inMin)/(inMax - inMin))*(outMax - outMin) + outMin;
  }
  
}
