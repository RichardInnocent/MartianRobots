package com.dcsl.position.grid;

import com.dcsl.position.Position;

/**
 * A grid, effectively encompassing a set of "permitted" coordinates.
 */
public interface Grid {

  int getXMin();
  int getXMax();
  int getYMin();
  int getYMax();

  /**
   * Checks if the grid contains the given position.
   * @param position The position to check.
   * @return {@code true} if the grid contains the position, otherwise {@code false}.
   */
  boolean containsPosition(Position position);

}
