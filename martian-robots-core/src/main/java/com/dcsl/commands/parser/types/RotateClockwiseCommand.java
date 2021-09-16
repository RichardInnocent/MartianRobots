package com.dcsl.commands.parser.types;

import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

/**
 * A command that specifies that the robot should rotate clockwise by 90 degrees.
 */
public class RotateClockwiseCommand implements Consumer<MarsRobot> {

  @Override
  public void accept(MarsRobot marsRobot) {
    marsRobot.rotateClockwise();
  }
}
