package com.tgelder.downhill.writer;

import com.tgelder.downhill.terrain.DownhillException;
import com.tgelder.downhill.terrain.Terrain;
import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FlowWriter {

  private final int flowThreshold;

  public void write(Terrain terrain, String file) throws IOException, DownhillException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.csv", file)))) {
      List<String> columns = new ArrayList<>();
      for (int x = 0; x < terrain.getWidth(); x++) {
        for (int y = 0; y < terrain.getWidth(); y++) {
          int flow = terrain.getFlow()[x][y];
          if (flow >= flowThreshold) {

            Optional<Terrain.Point> maybePoint = terrain.getDownhill(x, y);
            if (maybePoint.isPresent()) {
              Terrain.Point point = maybePoint.get();
              columns.clear();
              columns.add(Integer.toString(x));
              columns.add(Integer.toString(y));
              columns.add(Integer.toString(point.x));
              columns.add(Integer.toString(point.y));
              columns.add(Integer.toString(flow));
              writer.write(columns.stream().collect(Collectors.joining(",")));
              writer.newLine();
            }
          }
        }
      }
    }
  }

}
