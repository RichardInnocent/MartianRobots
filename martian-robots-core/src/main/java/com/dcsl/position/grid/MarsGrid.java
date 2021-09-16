package com.dcsl.position.grid;

import java.util.Objects;

import com.dcsl.position.Position;

/**
 * Implementation of a {@link Grid}.
 */
public class MarsGrid implements Grid {

  private static final int MAX_X_MAX = 50;
  private static final int MAX_Y_MAX = 50;

  private final int xMin;
  private final int xMax;
  private final int yMin;
  private final int yMax;

  /**
   * Creates a new grid.
   * @param xMin The minimum x value in the grid.
   * @param xMax The maximum x value in the grid (exclusive).
   * @param yMin The minimum y value in the grid.
   * @param yMax The maximum y value in the grid (exclusive).
   * @throws IllegalArgumentException Thrown if the area of the grid would be 0, or if the min and
   * max values have been entered incorrectly.
   */
  public MarsGrid(int xMin, int xMax, int yMin, int yMax) throws IllegalArgumentException {
    if (xMax <= xMin + 1) {
      throw new IllegalArgumentException("x max is less than or equal to x min");
    }
    if (yMax <= yMin + 1) {
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

  public int getXMin() {
    return xMin;
  }

  public int getXMax() {
    return xMax;
  }

  public int getYMin() {
    return yMin;
  }

  public int getYMax() {
    return yMax;
  }

  @Override
  public boolean containsPosition(Position position) {
    int x = position.getX();
    int y = position.getY();
    return x >= xMin && x < xMax && y >= yMin && y < yMax;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Grid)) {
      return false;
    }
    Grid marsGrid = (Grid) o;
    return xMin == marsGrid.getXMin()
        && xMax == marsGrid.getXMax()
        && yMin == marsGrid.getYMin()
        && yMax == marsGrid.getYMax();
  }

  @Override
  public int hashCode() {
    return Objects.hash(xMin, xMax, yMin, yMax);
  }
}
