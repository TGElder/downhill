package com.tgelder.downhill.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tgelder.downhill.TestUtils;

public class ScaleTest {
  
  @Test
  public void testScale() {
    Scale scale = new Scale(2000, 2016, 11, 55);
    
    assertEquals(19.25, scale.scale(2003), TestUtils.PRECISION);
  }

}
