package com.dcsl.commands.parser.types;

import org.junit.jupiter.api.Test;

import com.dcsl.robots.MarsRobot;

import static org.mockito.Mockito.*;

class RotateClockwiseCommandTest {

  private final MarsRobot robot = mock(MarsRobot.class);
  private final RotateClockwiseCommand command = new RotateClockwiseCommand();

  @Test
  public void accept_Always_RobotInstructedToRotateClockwise() {
    command.accept(robot);
    verify(robot, times(1)).rotateClockwise();
  }

}