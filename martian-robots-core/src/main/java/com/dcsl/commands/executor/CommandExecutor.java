package com.dcsl.commands.executor;

import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

/**
 * Responsible for executing a given command.
 */
public interface CommandExecutor {

  /**
   * Executes the provided command.
   * @param command The command to execute.
   * @param robot The robot to apply the command to.
   * @return The new position of the robot.
   * @throws InvalidCommandException Thrown if the command is invalid.
   */
  OrientedPosition execute(String command, MarsRobot robot)
      throws InvalidCommandException;

}
