package com.dcsl.cli.engine.factory.grid;

import org.junit.jupiter.api.Test;

import com.dcsl.position.grid.MarsGrid;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class AbstractMarsGridFactoryTest {

  private final AbstractMarsGridFactory gridFactory = spy(AbstractMarsGridFactory.class);

  @Test
  public void createGrid_ValidValues_GridCreated() {
    int xMin = 4;
    int xMax = 23;
    int yMin = 3;
    int yMax = 13;
    assertEquals(
        new MarsGrid(xMin, xMax, yMin, yMax),
        gridFactory.createGrid(xMin, xMax, yMin, yMax)
    );
  }

}