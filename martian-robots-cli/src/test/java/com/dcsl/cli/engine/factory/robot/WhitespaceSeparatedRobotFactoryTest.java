package com.dcsl.cli.engine.factory.robot;

import org.junit.jupiter.api.Test;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.position.OrientedPosition;

import static org.junit.jupiter.api.Assertions.*;

class WhitespaceSeparatedRobotFactoryTest {

  private final RobotFactory factory = new WhitespaceSeparatedRobotFactory();

  @Test
  public void createRobot_SpecificationValid_RobotCreated() {
    int x = 3;
    int y = 4;
    Orientation orientation = Orientation.EAST;
    OrientedPosition position = new FixedOrientedPosition(x, y, orientation);
    assertEquals(
        position,
        factory.createRobot(
            String.format("%d %d %s", x, y, orientation.getShortName())
        ).getOrientedPosition()
    );
  }

  @Test
  public void createRobot_TooFewArguments_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> factory.createRobot("1 2")
    );
  }

  @Test
  public void createRobot_TooManyArguments_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> factory.createRobot("1 2 E N")
    );
  }

  @Test
  public void createRobot_XCoordinateIsNotAnInteger_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> factory.createRobot("1.2 2 E")
    );
  }

  @Test
  public void createRobot_YCoordinateIsNotAnInteger_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> factory.createRobot("1 2.3 E")
    );
  }

  @Test
  public void createRobot_OrientationIsInvalid_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> factory.createRobot("1 2 NotAnOrientation")
    );
  }

}