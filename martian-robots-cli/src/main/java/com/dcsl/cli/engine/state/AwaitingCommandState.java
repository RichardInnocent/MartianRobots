package com.dcsl.cli.engine.state;

import java.util.Optional;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

public class AwaitingCommandState implements InterfaceState {

  private final CommandExecutor commandExecutor;
  private final StateHolder stateHolder;

  public AwaitingCommandState(
      CommandExecutor commandExecutor,
      StateHolder stateHolder
  ) {
    this.commandExecutor = commandExecutor;
    this.stateHolder = stateHolder;
  }

  @Override
  public Optional<String> process(String input) {
    MarsRobot robot = stateHolder.getRobot();
    commandExecutor.execute(input, robot);
    OrientedPosition position = robot.getOrientedPosition();
    return Optional.of(
        String.format(
            "%d %d %s%s",
            position.getX(),
            position.getY(),
            position.getOrientation().getShortName(),
            stateHolder.getGrid().containsPosition(position) ? "" : " LOST"
        )
    );
  }
}
