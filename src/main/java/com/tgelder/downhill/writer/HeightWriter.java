package com.tgelder.downhill.writer;

import com.sun.rowset.internal.Row;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HeightWriter {

  public static void write(double[][] heights, String file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.csv", file)))) {
      for (double[] column : heights) {
        String line = Arrays.stream(column).mapToObj(Double::toString).collect(Collectors.joining(","));
        writer.write(line);
        writer.newLine();
      }
    }

  }

}
