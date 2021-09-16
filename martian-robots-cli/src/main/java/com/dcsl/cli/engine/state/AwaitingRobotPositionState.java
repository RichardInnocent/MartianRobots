package com.dcsl.cli.engine.state;

import java.util.Optional;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.cli.engine.factory.robot.RobotFactory;

/**
 * The state induced when the robot has not been configured.
 */
public class AwaitingRobotPositionState implements InterfaceState {

  private final RobotFactory robotFactory;
  private final StateHolder stateHolder;

  public AwaitingRobotPositionState(RobotFactory robotFactory, StateHolder stateHolder) {
    this.robotFactory = robotFactory;
    this.stateHolder = stateHolder;
  }

  @Override
  public Optional<String> process(String input) throws IllegalArgumentException {
    stateHolder.setRobot(robotFactory.createRobot(input));
    return Optional.empty();
  }
}
