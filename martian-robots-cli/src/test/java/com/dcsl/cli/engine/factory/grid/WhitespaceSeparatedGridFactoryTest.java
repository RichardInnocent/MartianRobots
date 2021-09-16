package com.dcsl.cli.engine.factory.grid;

import org.junit.jupiter.api.Test;

import com.dcsl.position.grid.Grid;

import static org.junit.jupiter.api.Assertions.*;

class WhitespaceSeparatedGridFactoryTest {

  private final GridFactory gridFactory = new WhitespaceSeparatedGridFactory();

  @Test
  public void createGrid_CorrectlyFormatted_GridCreated() {
    int xMax = 5;
    int yMax = 7;
    Grid grid = gridFactory.createGrid(String.format("%d %d", xMax, yMax));
    assertEquals(0, grid.getXMin());
    assertEquals(xMax, grid.getXMax());
    assertEquals(0, grid.getYMin());
    assertEquals(yMax, grid.getYMax());
  }

  @Test
  public void createGrid_TooFewArguments_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> gridFactory.createGrid("13")
    );
  }

  @Test
  public void createGrid_TooManyArguments_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> gridFactory.createGrid("1 2 3")
    );
  }

  @Test
  public void createGrid_NotIntegerArguments_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> gridFactory.createGrid("3.4 5.6")
    );
  }

}