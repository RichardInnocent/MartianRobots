package com.dcsl.position.grid;

import com.dcsl.position.Position;

public class MarsGrid implements Grid {

  private final int xMin;
  private final int xMax;
  private final int yMin;
  private final int yMax;

  public MarsGrid(int xMin, int xMax, int yMin, int yMax) {
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
