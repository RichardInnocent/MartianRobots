package com.dcsl.cli.engine.factory.robot;

import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

public interface RobotFactory {

  MarsRobot createRobot(String specification) throws IllegalArgumentException;
  MarsRobot createRobot(OrientedPosition orientedPosition);

}
