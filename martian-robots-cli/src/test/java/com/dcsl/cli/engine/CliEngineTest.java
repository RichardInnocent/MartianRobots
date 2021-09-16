package com.dcsl.cli.engine;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.dcsl.commands.parser.InvalidCommandException;

import static org.mockito.Mockito.*;

class CliEngineTest {

  private final InputProcessor inputProcessor = mock(InputProcessor.class);
  private final PrintStream outputStream = mock(PrintStream.class);

  @Test
  public void run_SingleValidInputProvidedWithNoResult_Executed() {
    String command = "Test command";
    createEngineToProcess(command).run();
    verify(inputProcessor, times(1)).process(command);
  }

  @Test
  public void run_SingleValidInputProvidedWithResult_ResultPrinted() {
    String command = "Test command";
    String result = "Test result";
    when(inputProcessor.process(command)).thenReturn(Optional.of(result));

    createEngineToProcess(command).run();
    verify(inputProcessor, times(1)).process(command);
    verifyPrinted(result);
  }

  @Test
  public void run_MultipleValidInputsProvided_AllExecuted() {
    String command1 = "Test command 1";
    String command2 = "Test command 2";
    String command3 = "Test command 3";
    createEngineToProcess(command1, command2, command3).run();
    verify(inputProcessor, times(1)).process(command1);
    verify(inputProcessor, times(1)).process(command2);
    verify(inputProcessor, times(1)).process(command3);
  }

  @Test
  public void run_InputThrowsIllegalArgumentException_ExceptionPrinted() {
    String command = "Test command";
    String exceptionMessage = "Test exception";
    when(inputProcessor.process(command)).thenThrow(new IllegalArgumentException(exceptionMessage));
    createEngineToProcess(command).run();
    verifyPrinted("Invalid input: " + exceptionMessage);
  }

  @Test
  public void run_InputThrowsInvalidCommandException_ExceptionPrinted() {
    String command = "Test command";
    int index = 3;
    when(inputProcessor.process(command))
        .thenThrow(InvalidCommandException.forCharacterAtIndex(index, command));
    createEngineToProcess(command).run();
    verifyPrinted("Unknown command: " + command.charAt(index));
    verifyPrinted(command);
    verifyPrinted("   ^");
  }

  private void verifyPrinted(String text) {
    verify(outputStream, times(1)).println(eq(text));
  }

  private CliEngine createEngineToProcess(String... commands) {
    return createEngineToProcess(Arrays.asList(commands));
  }

  private CliEngine createEngineToProcess(List<String> commands) {
    return createEngineToProcess(String.join(System.lineSeparator(), commands));
  }

  private CliEngine createEngineToProcess(String commands) {
    return new CliEngine(
        inputProcessor,
        createInputStream(commands),
        outputStream
    );
  }

  private InputStream createInputStream(String text) {
    return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
  }

}