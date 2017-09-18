package com.tgelder.downhill;

public class Scale {
  
  private float outMin;
  private float inMin;

  private float factor;
  
  public Scale() {
    
  }
  
  public Scale(float inMin, float inMax, float outMin, float outMax) {
    set(inMin, inMax, outMin, outMax);
  }
  
  public void set(float inMin, float inMax, float outMin, float outMax) {
    this.inMin = inMin;
    this.outMin = outMin;
    
    factor = (outMax - outMin)/(inMax - inMin);
  }
  
  public float scale(float value) {
    return (value - inMin)*factor + outMin;
  }
  
}
