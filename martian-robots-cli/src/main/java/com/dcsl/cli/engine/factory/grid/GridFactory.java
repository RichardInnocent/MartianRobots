package com.dcsl.cli.engine.factory.grid;

import com.dcsl.position.grid.Grid;

public interface GridFactory {

  Grid createGrid(String specification) throws IllegalArgumentException;
  Grid createGrid(int xMin, int xMax, int yMin, int yMax) throws IllegalArgumentException;

}
