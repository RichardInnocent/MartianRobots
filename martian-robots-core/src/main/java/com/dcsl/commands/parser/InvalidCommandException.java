package com.dcsl.commands.parser;

/**
 * An exception that can be thrown to indicate that a command is invalid.
 */
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

  /**
   * Creates a new exception to indicate that a command is invalid.
   * @param index The first index where an invalid character was detected.
   * @param command The invalid command that was attempted to be executed.
   * @return An exception.
   * @throws IndexOutOfBoundsException Thrown if {@code index >= command.length()}.
   */
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
