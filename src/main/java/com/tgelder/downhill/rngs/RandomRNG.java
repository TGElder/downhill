package com.tgelder.downhill.rngs;

import java.util.Random;

public class RandomRNG implements RNG {
  
  private final Random random;
  
  public RandomRNG(int seed) {
    random = new Random(seed); 
  }

  @Override
  public float getNext() {
    return random.nextFloat();
  }
  
  
}
