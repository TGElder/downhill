package com.tgelder.downhill.writer;

import com.sun.rowset.internal.Row;
import com.tgelder.downhill.terrain.Terrain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HeightWriter {

  public static void write(Terrain terrain, String file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.csv", file)))) {

        for (int y = 0; y < terrain.getWidth(); y++) {
          List<String> row = new ArrayList<>();
          for (int x = 0; x < terrain.getWidth(); x++) {
            row.add(Double.toString(terrain.getAltitudes()[x][y]));
          }
          writer.write(String.join(",", row));
          writer.newLine();
        }
    }

  }

}
