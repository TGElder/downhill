package com.tgelder.downhill.mesh;

public class DownhillException extends Exception {
  
  public DownhillException(String message) {
    super(message);
  }
  
  public DownhillException(String message, Exception exception) {
    super(message, exception);
  }

}
