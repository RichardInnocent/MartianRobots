package com.dcsl.robots;

import org.junit.jupiter.api.Test;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.FixedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.position.OrientedPosition;

import static org.junit.jupiter.api.Assertions.*;

class MartianRobotTest {

  @Test
  public void getPosition_RobotNotMoved_PositionReturnedAsExpected() {
    int x = 5;
    int y = -3;
    Orientation orientation = Orientation.EAST;
    MartianRobot robot = new MartianRobot(new FixedOrientedPosition(x, y, orientation));
    assertEquals(new FixedPosition(x, y), robot.getPosition());
  }

  @Test
  public void getOrientedPosition_RobotNotMoved_PositionReturnedAsExpected() {
    OrientedPosition position = new FixedOrientedPosition(5, -3, Orientation.EAST);
    MartianRobot robot = new MartianRobot(position);
    assertEquals(
        new FixedOrientedPosition(position, position.getOrientation()),
        robot.getPosition()
    );
  }

  @Test
  public void moveForward_Always_NewPositionReturned() {
    Orientation orientation = Orientation.NORTH;
    OrientedPosition startingPosition = new FixedOrientedPosition(0, 0, orientation);
    MartianRobot robot = new MartianRobot(startingPosition);

    OrientedPosition expectedNewPosition = new FixedOrientedPosition(
        orientation.getPositionInFront(startingPosition),
        orientation
    );

    OrientedPosition newPosition = robot.moveForward();
    assertEquals(expectedNewPosition, newPosition);
    assertEquals(newPosition, robot.getOrientedPosition());
  }

  @Test
  public void rotateClockwise_Always_TurnsToFaceNextClockwiseDirection() {
    Orientation startingOrientation = Orientation.NORTH;
    OrientedPosition startingPosition = new FixedOrientedPosition(0, 0, startingOrientation);
    MartianRobot robot = new MartianRobot(startingPosition);

    OrientedPosition newPosition = robot.rotateClockwise();
    OrientedPosition expectedPosition = new FixedOrientedPosition(
        startingPosition.getX(),
        startingPosition.getY(),
        startingOrientation.getNextClockwiseOrientation()
    );
    assertEquals(expectedPosition, newPosition);
  }

  @Test
  public void rotateAnticlockwise_Always_TurnsToFaceNextAnticlockwiseDirection() {
    Orientation startingOrientation = Orientation.NORTH;
    OrientedPosition startingPosition = new FixedOrientedPosition(0, 0, startingOrientation);
    MartianRobot robot = new MartianRobot(startingPosition);

    OrientedPosition newPosition = robot.rotateAnticlockwise();
    OrientedPosition expectedPosition = new FixedOrientedPosition(
        startingPosition.getX(),
        startingPosition.getY(),
        startingOrientation.getNextAnticlockwiseOrientation()
    );
    assertEquals(expectedPosition, newPosition);
  }

}