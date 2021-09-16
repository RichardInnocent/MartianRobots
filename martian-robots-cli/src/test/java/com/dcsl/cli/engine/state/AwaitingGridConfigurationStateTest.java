package com.dcsl.cli.engine.state;

import org.junit.jupiter.api.Test;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.cli.engine.factory.grid.GridFactory;
import com.dcsl.position.grid.Grid;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AwaitingGridConfigurationStateTest {

  private final GridFactory gridFactory = mock(GridFactory.class);
  private final StateHolder stateHolder = mock(StateHolder.class);
  private final AwaitingGridConfigurationState state =
      new AwaitingGridConfigurationState(gridFactory, stateHolder);

  @Test
  public void process_GridCreated_GridSet() {
    String input = "Test input";
    Grid grid = mock(Grid.class);
    when(gridFactory.createGrid(input)).thenReturn(grid);
    assertTrue(state.process(input).isEmpty());
    verify(stateHolder, times(1)).setGrid(grid);
  }

}