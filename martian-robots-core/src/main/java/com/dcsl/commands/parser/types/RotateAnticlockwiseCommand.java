package com.dcsl.commands.parser.types;

import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

/**
 * A command that specifies that the robot should rotate anticlockwise by 90 degrees.
 */
public class RotateAnticlockwiseCommand implements Consumer<MarsRobot> {

  @Override
  public void accept(MarsRobot marsRobot) {
    marsRobot.rotateAnticlockwise();
  }
}
