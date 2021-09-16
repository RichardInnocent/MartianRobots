package com.dcsl.commands.parser.types;

import org.junit.jupiter.api.Test;

import com.dcsl.robots.MarsRobot;

import static org.mockito.Mockito.*;

class RotateAnticlockwiseCommandTest {

  private final MarsRobot robot = mock(MarsRobot.class);
  private final RotateAnticlockwiseCommand command = new RotateAnticlockwiseCommand();

  @Test
  public void apply_Always_RobotIsInstructedToRotateAnticlockwise() {
    command.accept(robot);
    verify(robot, times(1)).rotateAnticlockwise();
  }

}