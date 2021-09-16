package com.dcsl.position.grid;

import com.dcsl.position.Position;

public class MarsGrid implements Grid {

  private final int xMin;
  private final int xMax;
  private final int yMin;
  private final int yMax;

  public MarsGrid(int xMin, int xMax, int yMin, int yMax) throws IllegalArgumentException {
    if (xMax <= xMin) {
      throw new IllegalArgumentException("x max is less than or equal to x min");
    }
    if (yMax <= yMin) {
      throw new IllegalArgumentException("y max is less than or equal to y min");
    }

    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }

  @Override
  public boolean containsPosition(Position position) {
    int x = position.getX();
    int y = position.getY();
    return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
  }
}
