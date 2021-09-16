package com.dcsl.commands.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.dcsl.commands.parser.types.MoveForwardCommand;
import com.dcsl.commands.parser.types.RotateAnticlockwiseCommand;
import com.dcsl.commands.parser.types.RotateClockwiseCommand;
import com.dcsl.robots.MarsRobot;

/**
 * Simple implementation of the {@link CommandSequenceParser}, where only three commands are valid:
 * <ul>
 *   <li>{@code F}: {@link MoveForwardCommand}</li>
 *   <li>{@code R}: {@link RotateClockwiseCommand}</li>
 *   <li>{@code L}: {@link RotateAnticlockwiseCommand}</li>
 * </ul>
 */
public class SimpleCommandParser implements CommandSequenceParser {

  @Override
  public List<Consumer<MarsRobot>> getCommands(String commandText) throws InvalidCommandException {
    List<Consumer<MarsRobot>> commands = new ArrayList<>(commandText.length());
    for (int i = 0; i < commandText.length(); i++) {
      final int index = i; // For use in lambda expression
      commands.add(
          getCommand(commandText.charAt(i))
              .orElseThrow(() -> InvalidCommandException.forCharacterAtIndex(index, commandText))
      );
    }
    return commands;
  }

  private Optional<Consumer<MarsRobot>> getCommand(char commandChar) {
    switch (commandChar) {
      case 'F':
        return Optional.of(new MoveForwardCommand());
      case 'R':
        return Optional.of(new RotateClockwiseCommand());
      case 'L':
        return Optional.of(new RotateAnticlockwiseCommand());
      default:
        return Optional.empty();
    }
  }
}
