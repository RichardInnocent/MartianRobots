package com.dcsl.cli.engine.factory.grid;

import com.dcsl.position.grid.MarsGrid;

public abstract class AbstractMarsGridFactory implements GridFactory {

  @Override
  public MarsGrid createGrid(int xMin, int xMax, int yMin, int yMax)
      throws IllegalArgumentException {
    return new MarsGrid(xMin, xMax, yMin, yMax);
  }
}
