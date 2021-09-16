package com.dcsl.commands.parser;

import java.util.List;
import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

public class SimpleCommandParser implements CommandSequenceParser {

  @Override
  public List<Consumer<MarsRobot>> getCommands(String commandText) throws InvalidCommandException {
    return null;
  }
}
