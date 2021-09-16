package com.dcsl.commands.executor;

import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

public interface CommandExecutor {

  OrientedPosition execute(String command, MarsRobot robot)
      throws InvalidCommandException;

}
