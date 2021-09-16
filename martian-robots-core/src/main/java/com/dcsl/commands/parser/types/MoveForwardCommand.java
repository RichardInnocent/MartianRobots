package com.dcsl.commands.parser.types;


import com.dcsl.commands.parser.RobotCommand;
import com.dcsl.robots.MarsRobot;

public class MoveForwardCommand implements RobotCommand {

  public static String NAME = "MoveForward";

  @Override
  public void accept(MarsRobot marsRobot) {
    marsRobot.moveForward();
  }

  @Override
  public String getName() {
    return NAME;
  }
}
