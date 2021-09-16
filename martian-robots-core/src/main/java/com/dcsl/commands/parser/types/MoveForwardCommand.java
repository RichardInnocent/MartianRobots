package com.dcsl.commands.parser.types;


import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

public class MoveForwardCommand implements Consumer<MarsRobot> {

  @Override
  public void accept(MarsRobot marsRobot) {
    marsRobot.moveForward();
  }

}
