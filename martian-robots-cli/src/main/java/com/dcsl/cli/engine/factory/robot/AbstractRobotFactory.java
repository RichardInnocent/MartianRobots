package com.dcsl.cli.engine.factory.robot;

import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;
import com.dcsl.robots.MarsRobotImpl;

/**
 * Abstract implementation of a {@link RobotFactory}.
 */
public abstract class AbstractRobotFactory implements RobotFactory {

  @Override
  public MarsRobot createRobot(OrientedPosition orientedPosition) {
    return new MarsRobotImpl(orientedPosition);
  }
}
