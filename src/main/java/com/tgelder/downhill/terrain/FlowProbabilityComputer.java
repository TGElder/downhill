package com.tgelder.downhill.terrain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.*;

@AllArgsConstructor
class FlowProbabilityComputer {

  double[][] getFlow(Mesh mesh, short[] dx, short[] dy) {
    double[][] flow = new double[mesh.getWidth()][mesh.getWidth()];

    List<Cell> work = new ArrayList<>();
    Cell[][] cells = new Cell[mesh.getWidth()][mesh.getWidth()];
    
    mesh.iterate((x, y) -> {
      Cell cell = new Cell(x, y);
      work.add(cell);
      cells[x][y] = cell;
    });

    Comparator<Cell> comparator = Comparator.comparingDouble(a -> mesh.getZ(a.x, a.y));
    work.sort(comparator);

    int closed = 0;

    for (Cell focus : work) {
      if (!focus.closed) {
        focus.queued = true;
        int focusClosed = computeFlow(mesh, flow, focus.x, focus.y, dx, dy, cells);
        closed += focusClosed;
        System.out.println(String.format("Found %s new cells upstream of %s.%s. %spc complete", focusClosed, focus.x, focus.y, (closed * 1.0) / work.size()));
      }
    }
    
    return flow;
  }

  private Cell getUpstream(int x, int y, Cell[][] upstream) {
    Cell out = upstream[x][y];
    if (out == null) {
      out = new Cell(x, y);
    }
    return out;
  }
  
  private int computeFlow(Mesh mesh, double[][] flow, int x, int y, short[] dx, short[] dy, Cell[][] cells) {

    List<Cell> work = new ArrayList<>();

    work.add(cells[x][y]);

    int i = 0;

    while (i < work.size()) {
      Cell focus = work.get(i);
      Set<Cell> upstream = getUpstream(mesh, focus.x, focus.y, dx, dy, cells);
      for (Cell upper : upstream) {
        if (upper.closed) {
          focus.upstream.addAll(upper.upstream);
        } else if (!upper.queued) {
          work.add(upper);
          upper.queued = true;
        }
        focus.upstream.add(upper);
      }
      i++;
    }

    Comparator<Cell> comparator = Comparator.comparingDouble(u -> mesh.getZ(u.x, u.y));
    work.sort(comparator.reversed());

    for (Cell focus : work) {
      Set<Cell> toAdd = new HashSet<>();
      for (Cell upper : focus.upstream) {
        toAdd.addAll(upper.upstream);
      }
      focus.upstream.addAll(toAdd);
      flow[focus.x][focus.y] = 1 + focus.upstream.size();
      focus.closed = true;
    }

    return work.size();
  }



  private Set<Cell> getUpstream(Mesh mesh, int x, int y, short[] dx, short[] dy, Cell[][] upstream) {
    Set<Cell> out = new HashSet<>();
    double z = mesh.getZ(x, y);
    for (int d = 0; d < dx.length; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];
      if (mesh.inBounds(nx, ny)) {
        if (mesh.getZ(nx, ny) > z) {
          out.add(getUpstream(nx, ny, upstream));
        }
      }
    }
    return out;
  }

  @RequiredArgsConstructor
  private class Cell {
    final int x;
    final int y;
    final Set<Cell> upstream = new HashSet<> ();
    boolean queued = false;
    boolean closed = false;
  }


}
