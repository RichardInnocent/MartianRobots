package com.dcsl.commands.parser.types;

import org.junit.jupiter.api.Test;

import com.dcsl.robots.MarsRobot;

import static org.mockito.Mockito.*;

class MoveForwardCommandTest {

  private final MarsRobot robot = mock(MarsRobot.class);
  private final MoveForwardCommand command = new MoveForwardCommand();

  @Test
  public void accept_Always_MovesRobotForward() {
    command.accept(robot);
    verify(robot, times(1)).moveForward();
  }

}