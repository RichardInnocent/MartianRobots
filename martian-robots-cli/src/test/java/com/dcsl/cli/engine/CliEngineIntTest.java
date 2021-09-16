package com.dcsl.cli.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.commands.executor.SimpleCommandExecutor;
import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.robots.MarsRobot;
import com.dcsl.robots.MartianRobot;

class CliEngineIntTest {

  private static final InputStream ORIGINAL_INPUT = System.in;
  private static final PrintStream ORIGINAL_OUTPUT = System.out;

  private final MarsRobot robot =
      new MartianRobot(new FixedOrientedPosition(1, 1, Orientation.EAST));
  private final CommandExecutor commandExecutor = new SimpleCommandExecutor();

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream output = new PrintStream(outputStream, true, StandardCharsets.UTF_8);

  @BeforeEach
  public void setUp() {
    System.setOut(output);
  }

  @AfterEach
  public void tearDown() {
    output.close();
  }

  @AfterAll
  public static void resetSystem() {
    System.setIn(ORIGINAL_INPUT);
    System.setOut(ORIGINAL_OUTPUT);
  }

  @Test
  public void run_GivenExample_InputsAndOutputsAsExpected() {
    configureInputToReturn(
        "5 3",
        "RFRFRFRF",
        "FRRFLLFFRRFLL",
        "LLFFFRFLFL"
    );

    new CliEngine(robot, commandExecutor).run();
    getAllPrintedLines().forEach(ORIGINAL_OUTPUT::println);
    ORIGINAL_OUTPUT.println("Hello world!");
  }

  private void configureInputToReturn(String... inputLines) {
    configureInputToReturn(Arrays.asList(inputLines));
  }

  private void configureInputToReturn(List<String> inputLines) {
    System.setIn(
        new ByteArrayInputStream(
            String.join(System.lineSeparator(), inputLines).getBytes(StandardCharsets.UTF_8)
        )
    );
  }

  private List<String> getAllPrintedLines() {
    String allOutput = outputStream.toString(StandardCharsets.UTF_8);
    return Arrays.asList(allOutput.split(System.lineSeparator()));
  }

}
