package com.tgelder.downhill;

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
