package com.tgelder.downhill.rngs;

import com.tgelder.downhill.rngs.RNG;

public class MockRNG implements RNG {
  
  private final double [] numbers;
  private int i = 0;
  
  public MockRNG(final double [] numbers) {
    this.numbers = numbers;
  }

  @Override
  public double getNext() {
    double out = numbers[i];
    i = (i + 1) % numbers.length;
    return out;
  }
  
}
