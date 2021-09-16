package com.dcsl.commands.executor;

import java.util.Optional;

import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

public interface CommandExecutor {

  Optional<OrientedPosition> execute(String command, MarsRobot robot)
      throws InvalidCommandException;

}
