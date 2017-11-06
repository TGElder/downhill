package com.tgelder.downhill.terrain;

public class DownhillException extends Exception {
  
  DownhillException(String message) {
    super(message);
  }
  
  DownhillException(String message, Exception exception) {
    super(message, exception);
  }

}
