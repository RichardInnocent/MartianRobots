package com.dcsl.commands.parser;

public class InvalidCommandException extends IllegalArgumentException {

  private final int index;
  private final String command;

  private InvalidCommandException(int index, String command) throws IndexOutOfBoundsException {
    if (index >= command.length()) {
      throw new IndexOutOfBoundsException();
    }
    this.index = index;
    this.command = command;
  }

  public static InvalidCommandException forCharacterAtIndex(int index, String command)
      throws IndexOutOfBoundsException {
    return new InvalidCommandException(index, command);
  }

  public int getIndex() {
    return index;
  }

  public String getCommand() {
    return command;
  }
}
