package com.dcsl.commands.parser;

import java.util.List;
import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

public interface CommandSequenceParser {

  List<Consumer<MarsRobot>> getCommands(String commandText) throws InvalidCommandException;

}
