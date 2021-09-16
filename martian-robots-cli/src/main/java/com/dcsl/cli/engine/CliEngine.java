package com.dcsl.cli.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.dcsl.commands.parser.InvalidCommandException;

/**
 * The engine responsible for continually consuming user input and presenting results.
 */
public class CliEngine implements Runnable {

  private final InputProcessor inputProcessor;
  private final InputStream inputStream;
  private final PrintStream output;

  public CliEngine(InputProcessor inputProcessor) {
    this(inputProcessor, System.in, System.out);
  }

  CliEngine(
      InputProcessor inputProcessor,
      InputStream inputStream,
      PrintStream output) {
    this.inputProcessor = inputProcessor;
    this.inputStream = inputStream;
    this.output = output;
  }

  @Override
  public void run() {
    try {
      runAndThrowExceptions();
    } catch (IOException e) {
      throw new RuntimeException("Failed to process input", e);
    }
  }

  private void runAndThrowExceptions() throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = reader.readLine()) != null) {
        try {
          inputProcessor.process(line).ifPresent(output::println);
          continue;
        } catch (InvalidCommandException e) {
          // There's a nice format for this type of exception, so show that
          output.println("Unknown command: " + e.getCommand().charAt(e.getIndex()));
          output.println(e.getCommand());
          output.println(" ".repeat(e.getIndex()) + "^");
        } catch (IllegalArgumentException e) {
          output.println("Invalid input: " + e.getMessage());
        }
        output.println("Please enter an appropriate value");
      }
    }
  }

}
