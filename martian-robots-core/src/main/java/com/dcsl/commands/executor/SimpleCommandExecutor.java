package com.dcsl.commands.executor;

import java.util.Optional;

import com.dcsl.commands.parser.CommandSequenceParser;
import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

public class SimpleCommandExecutor implements CommandExecutor {

  private final CommandSequenceParser parser;

  public SimpleCommandExecutor(CommandSequenceParser parser) {
    this.parser = parser;
  }

  @Override
  public Optional<OrientedPosition> execute(String commandSequence, MarsRobot robot)
      throws InvalidCommandException {
    parser.getCommands(commandSequence).forEach(command -> command.accept(robot));
    return robot.getOrientedPosition();
  }
}
