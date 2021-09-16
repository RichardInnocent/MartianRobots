package com.dcsl.cli.engine.factory.grid;

import com.dcsl.position.grid.Grid;

/**
 * Allows the creation of a grid.
 */
public interface GridFactory {

  /**
   * Creates a grid from a given string.
   * @param specification The specification for the grid factory. The format of this will depend on
   * the implementation.
   * @return A grid.
   * @throws IllegalArgumentException Thrown if the input is invalid;
   */
  Grid createGrid(String specification) throws IllegalArgumentException;

  /**
   * Creates a grid with the given dimensions.
   * @param xMin The minimum x value in the grid.
   * @param xMax The maximum x value in the grid (exclusive).
   * @param yMin The minimum y value in the grid.
   * @param yMax The maximum y value in the grid (exclusive).
   * @return A grid.
   * @throws IllegalArgumentException Thrown if the dimensions are invalid.
   */
  Grid createGrid(int xMin, int xMax, int yMin, int yMax) throws IllegalArgumentException;

}
