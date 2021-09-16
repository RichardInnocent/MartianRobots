package com.dcsl.cli.engine.state;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.position.Orientation;
import com.dcsl.position.OrientedPosition;
import com.dcsl.position.grid.Grid;
import com.dcsl.robots.MarsRobot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AwaitingCommandStateTest {
  private static final int X_COORDINATE = 3;
  private static final int Y_COORDINATE = 6;
  private static final Orientation ORIENTATION = Orientation.EAST;

  private final OrientedPosition position = mock(OrientedPosition.class);
  private final MarsRobot robot = mock(MarsRobot.class);
  private final Grid grid = mock(Grid.class);
  private final CommandExecutor executor = mock(CommandExecutor.class);
  private final StateHolder stateHolder = mock(StateHolder.class);
  private final AwaitingCommandState state = new AwaitingCommandState(executor, stateHolder);

  @BeforeEach
  public void setUp() {
    when(stateHolder.getGrid()).thenReturn(grid);
    when(stateHolder.getRobot()).thenReturn(robot);
    when(position.getX()).thenReturn(X_COORDINATE);
    when(position.getY()).thenReturn(Y_COORDINATE);
    when(position.getOrientation()).thenReturn(ORIENTATION);
    when(robot.getOrientedPosition()).thenReturn(position);
    when(grid.containsPosition(position)).thenReturn(true);
  }

  @Test
  public void process_ValidInputAndRobotStaysOnGrid_RobotNotLost() {
    String input = "Test input";
    assertEquals(
        Optional.of(
            String.format("%d %d %s", X_COORDINATE, Y_COORDINATE, ORIENTATION.getShortName())
        ),
        state.process(input)
    );
    verify(executor, times(1)).execute(input, robot);
  }

  @Test
  public void process_ValidInputAndRobotIsOutOfGrid_RobotLost() {
    when(grid.containsPosition(position)).thenReturn(false);

    String input = "Test input";
    assertEquals(
        Optional.of(
            String.format("%d %d %s LOST", X_COORDINATE, Y_COORDINATE, ORIENTATION.getShortName())
        ),
        state.process(input)
    );
    verify(executor, times(1)).execute(input, robot);
  }

}