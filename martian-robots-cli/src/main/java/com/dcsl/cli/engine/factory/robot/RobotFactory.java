package com.dcsl.cli.engine.factory.robot;

import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

/**
 * Responsible for creating robots.
 */
public interface RobotFactory {

  /**
   * Creates a robot based on the input provided.
   * @param specification The specification of the robot. The format of this will vary depending on
   * the implementation.
   * @return A robot.
   * @throws IllegalArgumentException Thrown if the specification is invalid.
   */
  MarsRobot createRobot(String specification) throws IllegalArgumentException;

  /**
   * Creates a robot at the position provided.
   * @param orientedPosition The starting position of the robot.
   * @return A robot.
   */
  MarsRobot createRobot(OrientedPosition orientedPosition);

}
