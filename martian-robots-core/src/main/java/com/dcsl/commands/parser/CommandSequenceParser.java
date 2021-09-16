package com.dcsl.commands.parser;

import java.util.List;
import java.util.function.Consumer;

import com.dcsl.robots.MarsRobot;

/**
 * Responsible for parsing a command sequence into a collection of executable commands.
 */
public interface CommandSequenceParser {

  /**
   * Parses the command text into a sequence of executable commands.
   * @param commandText The command text to parse.
   * @return A sequence of executable commands.
   * @throws InvalidCommandException Thrown if the command text is invalid.
   */
  List<Consumer<MarsRobot>> getCommands(String commandText) throws InvalidCommandException;

}
