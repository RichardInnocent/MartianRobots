package com.dcsl.commands.parser;

import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

public interface RobotCommand extends Consumer<MarsRobot> {

  String getName();

}
