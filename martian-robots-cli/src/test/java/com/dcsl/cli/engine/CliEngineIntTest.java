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

import static org.junit.jupiter.api.Assertions.assertEquals;

class CliEngineIntTest {

  private static final InputStream ORIGINAL_INPUT = System.in;
  private static final PrintStream ORIGINAL_OUTPUT = System.out;

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
        "1 1 E",
        "RFRFRFRF",
        "3 2 N",
        "FRRFLLFFRRFLL",
        "0 3 W",
        "LLFFFRFLFL"
    );

    CliEngine engine = new CliEngine(new StateControlledInputProcessor());
    engine.run();

    List<String> printedLines = getAllPrintedLines();
    assertEquals("1 1 E", printedLines.get(0));
    assertEquals("3 3 N LOST", printedLines.get(1));
    assertEquals("4 2 N", printedLines.get(2));
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
