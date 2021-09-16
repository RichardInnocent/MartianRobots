package com.dcsl.commands.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCommandExceptionTest {

  @Test
  public void forCharacterAtIndex_Always_CharacterAndIndexSet() {
    int index = 4;
    String command = "Test command";
    InvalidCommandException exception = InvalidCommandException.forCharacterAtIndex(index, command);
    assertEquals(index, exception.getIndex());
    assertEquals(command, exception.getCommand());
  }

  @Test
  public void forCharacterAtIndex_IndexExceedsLengthOfString_ExceptionThrown() {
    String command = "Test command";
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> InvalidCommandException.forCharacterAtIndex(command.length(), command)
    );
  }

}