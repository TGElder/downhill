package com.tgelder.downhill.rngs;

import com.tgelder.downhill.rngs.RNG;

public class MockRNG implements RNG {
  
  private final float [] numbers;
  private int i = 0;
  
  public MockRNG(final float [] numbers) {
    this.numbers = numbers;
  }

  @Override
  public float getNext() {
    i++;
    return numbers[i-1];
  }
  
}
