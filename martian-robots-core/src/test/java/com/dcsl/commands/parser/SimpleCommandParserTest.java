package com.dcsl.commands.parser;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.dcsl.commands.parser.types.MoveForwardCommand;
import com.dcsl.commands.parser.types.RotateAnticlockwiseCommand;
import com.dcsl.commands.parser.types.RotateClockwiseCommand;
import com.dcsl.robots.MarsRobot;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCommandParserTest {

  private static final String INVALID_COMMAND_CHAR = "X";
  private static final String MOVE_FORWARD_COMMAND_CHAR = "F";
  private static final String ROTATE_CLOCKWISE_COMMAND_CHAR = "R";
  private static final String ROTATE_ANTICLOCKWISE_COMMAND_CHAR = "L";

  private final SimpleCommandParser commandParser = new SimpleCommandParser();

  @Test
  public void getCommands_CommandIsEmpty_ReturnsEmptyList() {
    assertTrue(commandParser.getCommands("").isEmpty());
  }

  @Test
  public void getCommands_ContainsSingleMoveForwardCommand_CommandReturned() {
    List<Consumer<MarsRobot>> commands = commandParser.getCommands(MOVE_FORWARD_COMMAND_CHAR);
    assertEquals(1, commands.size());
    assertTrue(commands.get(0) instanceof MoveForwardCommand);
  }

  @Test
  public void getCommands_ContainsSingleRotateClockwiseCommand_CommandReturned() {
    List<Consumer<MarsRobot>> commands = commandParser.getCommands(ROTATE_CLOCKWISE_COMMAND_CHAR);
    assertEquals(1, commands.size());
    assertTrue(commands.get(0) instanceof RotateClockwiseCommand);
  }

  @Test
  public void getCommands_ContainsSingleRotateAnticlockwiseCommand_CommandReturned() {
    List<Consumer<MarsRobot>> commands = commandParser.getCommands(ROTATE_ANTICLOCKWISE_COMMAND_CHAR);
    assertEquals(1, commands.size());
    assertTrue(commands.get(0) instanceof RotateAnticlockwiseCommand);
  }

  @Test
  public void getCommands_ContainsSingleInvalidCommand_ExceptionThrown() {
    try {
      commandParser.getCommands(INVALID_COMMAND_CHAR);
      fail("No exception thrown");
    } catch (InvalidCommandException e) {
      assertEquals(INVALID_COMMAND_CHAR, e.getCommand());
      assertEquals(0, e.getIndex());
    }
  }

  @Test
  public void getCommands_ContainsMultipleValidCommands_CommandsReturned() {
    String command = ROTATE_CLOCKWISE_COMMAND_CHAR
        + MOVE_FORWARD_COMMAND_CHAR
        + MOVE_FORWARD_COMMAND_CHAR
        + ROTATE_ANTICLOCKWISE_COMMAND_CHAR
        + ROTATE_ANTICLOCKWISE_COMMAND_CHAR
        + MOVE_FORWARD_COMMAND_CHAR
        + ROTATE_CLOCKWISE_COMMAND_CHAR;

    List<Consumer<MarsRobot>> commands = commandParser.getCommands(command);
    assertEquals(command.length(), commands.size());
    assertTrue(commands.get(0) instanceof RotateClockwiseCommand);
    assertTrue(commands.get(1) instanceof MoveForwardCommand);
    assertTrue(commands.get(2) instanceof MoveForwardCommand);
    assertTrue(commands.get(3) instanceof RotateAnticlockwiseCommand);
    assertTrue(commands.get(4) instanceof RotateAnticlockwiseCommand);
    assertTrue(commands.get(5) instanceof MoveForwardCommand);
    assertTrue(commands.get(6) instanceof RotateClockwiseCommand);
  }

  @Test
  public void getCommands_ContainsInvalidCommandInSequence_ExceptionThrown() {
    String command = ROTATE_CLOCKWISE_COMMAND_CHAR
        + MOVE_FORWARD_COMMAND_CHAR
        + MOVE_FORWARD_COMMAND_CHAR
        + INVALID_COMMAND_CHAR
        + ROTATE_ANTICLOCKWISE_COMMAND_CHAR;

    try {
      commandParser.getCommands(command);
      fail("No exception thrown");
    } catch (InvalidCommandException e) {
      assertEquals(command.indexOf(INVALID_COMMAND_CHAR), e.getIndex());
      assertEquals(command, e.getCommand());
    }
  }

}