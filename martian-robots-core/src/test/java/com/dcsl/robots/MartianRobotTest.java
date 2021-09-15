package com.dcsl.robots;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.FixedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.position.OrientedPosition;
import com.dcsl.position.grid.Grid;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MartianRobotTest {

  private final Grid grid = mock(Grid.class);

  @BeforeEach
  public void setUp() {
    when(grid.containsPosition(any())).thenReturn(true);
  }

  @Test
  public void getPosition_RobotNotMoved_PositionReturnedAsExpected() {
    int x = 5;
    int y = -3;
    Orientation orientation = Orientation.EAST;
    MartianRobot robot = new MartianRobot(grid, new FixedOrientedPosition(x, y, orientation));
    assertEquals(Optional.of(new FixedPosition(x, y)), robot.getPosition());
  }

  @Test
  public void getOrientedPosition_RobotNotMoved_PositionReturnedAsExpected() {
    OrientedPosition position = new FixedOrientedPosition(5, -3, Orientation.EAST);
    MartianRobot robot = new MartianRobot(grid, position);
    assertEquals(Optional.of(new FixedPosition(position)), robot.getPosition());
  }

  @Test
  public void moveForward_NewPositionIsOnGrid_NewPositionReturned() {
    Orientation orientation = Orientation.NORTH;
    OrientedPosition startingPosition = new FixedOrientedPosition(0, 0, orientation);
    MartianRobot robot = new MartianRobot(grid, startingPosition);

    OrientedPosition expectedNewPosition = new FixedOrientedPosition(
        orientation.getPositionInFront(startingPosition),
        orientation
    );

    Optional<OrientedPosition> newPositionOptional = robot.moveForward();
    assertTrue(newPositionOptional.isPresent());
    OrientedPosition newPosition = newPositionOptional.get();
    assertEquals(expectedNewPosition, newPosition);
    assertEquals(newPositionOptional, robot.getOrientedPosition());
  }

  @Test
  public void moveForward_NewPositionIsNotOnGrid_NewPositionReturned() {
    when(grid.containsPosition(any())).thenReturn(false);

    Orientation orientation = Orientation.NORTH;
    OrientedPosition startingPosition = new FixedOrientedPosition(0, 0, orientation);
    MartianRobot robot = new MartianRobot(grid, startingPosition);

    assertTrue(robot.moveForward().isEmpty());
    assertEquals(Optional.empty(), robot.getOrientedPosition());
  }

}