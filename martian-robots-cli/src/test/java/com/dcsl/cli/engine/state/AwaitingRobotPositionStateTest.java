package com.dcsl.cli.engine.state;

import org.junit.jupiter.api.Test;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.cli.engine.factory.robot.RobotFactory;
import com.dcsl.robots.MarsRobot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AwaitingRobotPositionStateTest {

  private final RobotFactory robotFactory = mock(RobotFactory.class);
  private final StateHolder stateHolder = mock(StateHolder.class);
  private final AwaitingRobotPositionState state =
      new AwaitingRobotPositionState(robotFactory, stateHolder);

  @Test
  public void process_InputValid_RobotSet() {
    String input = "Test input";
    MarsRobot robot = mock(MarsRobot.class);
    when(robotFactory.createRobot(input)).thenReturn(robot);
    assertTrue(state.process(input).isEmpty());
    verify(stateHolder, times(1)).setRobot(robot);
  }

}