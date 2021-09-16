package com.dcsl.cli.engine.state;

import java.util.Optional;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.cli.engine.factory.robot.RobotFactory;

public class AwaitingRobotPositionState implements InterfaceState {

  private final RobotFactory robotFactory;
  private final StateHolder stateHolder;

  public AwaitingRobotPositionState(RobotFactory robotFactory, StateHolder stateHolder) {
    this.robotFactory = robotFactory;
    this.stateHolder = stateHolder;
  }

  @Override
  public Optional<String> process(String input) {
    stateHolder.setRobot(robotFactory.createRobot(input));
    return Optional.empty();
  }
}
