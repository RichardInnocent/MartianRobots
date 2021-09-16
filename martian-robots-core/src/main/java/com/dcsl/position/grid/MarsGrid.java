package com.dcsl.position.grid;

import com.dcsl.position.Position;

public class MarsGrid implements Grid {

  private static final int MAX_X_MAX = 50;
  private static final int MAX_Y_MAX = 50;

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
    if (xMax > 50) {
      throw new IllegalArgumentException(
          "x max is greater than the maximum allowed value of " + MAX_X_MAX
      );
    }
    if (yMax > 50) {
      throw new IllegalArgumentException(
          "y max is greater than the maximum allowed value of " + MAX_Y_MAX
      );
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
