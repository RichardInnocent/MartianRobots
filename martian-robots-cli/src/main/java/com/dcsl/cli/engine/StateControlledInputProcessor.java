package com.dcsl.cli.engine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.dcsl.cli.engine.factory.grid.GridFactory;
import com.dcsl.cli.engine.factory.grid.WhitespaceSeparatedGridFactory;
import com.dcsl.cli.engine.factory.robot.RobotFactory;
import com.dcsl.cli.engine.factory.robot.WhitespaceSeparatedRobotFactory;
import com.dcsl.cli.engine.state.AwaitingCommandState;
import com.dcsl.cli.engine.state.AwaitingGridConfigurationState;
import com.dcsl.cli.engine.state.AwaitingRobotPositionState;
import com.dcsl.cli.engine.state.InterfaceState;
import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.commands.executor.SimpleCommandExecutor;
import com.dcsl.position.grid.Grid;
import com.dcsl.robots.MarsRobot;

/**
 * Responsible for handling consumer input, and the application state.
 */
public class StateControlledInputProcessor implements InputProcessor, StateHolder {

  private final List<InterfaceState> interfaceStates;
  private int currentInterfaceStateIndex = 0;
  private Grid grid;
  private MarsRobot robot;

  public StateControlledInputProcessor() {
    this(
        new WhitespaceSeparatedGridFactory(),
        new WhitespaceSeparatedRobotFactory(),
        new SimpleCommandExecutor()
    );
  }

  public StateControlledInputProcessor(
      GridFactory gridFactory,
      RobotFactory robotFactory,
      CommandExecutor commandExecutor
  ) {
    this.interfaceStates = Arrays.asList(
        new AwaitingGridConfigurationState(gridFactory, this),
        new AwaitingRobotPositionState(robotFactory, this),
        new AwaitingCommandState(commandExecutor, this)
    );
  }

  @Override
  public Optional<String> process(String input) throws IllegalArgumentException {
    // Process the input
    Optional<String> result = interfaceStates.get(currentInterfaceStateIndex).process(input);

    // If successful, increment the application state
    incrementInterfaceState();
    return result;
  }

  private void incrementInterfaceState() {
    // Ensure we never return to the first state
    currentInterfaceStateIndex =
        currentInterfaceStateIndex == 2 ? 1 : currentInterfaceStateIndex + 1;
  }

  @Override
  public Grid getGrid() {
    return grid;
  }

  @Override
  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  @Override
  public MarsRobot getRobot() {
    return robot;
  }

  @Override
  public void setRobot(MarsRobot robot) {
    this.robot = robot;
  }
}
