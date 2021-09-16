package com.dcsl.cli.engine;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

import static org.mockito.Mockito.*;

class CliEngineTest {

  private final MarsRobot robot = mock(MarsRobot.class);
  private final CommandExecutor executor = mock(CommandExecutor.class);
  private final PrintStream outputStream = mock(PrintStream.class);

  @Test
  public void run_GridSizeSetCorrectlyAndRobotIsInGrid_GridSizeSet() {
    CliEngine engine = createEngineToProcess("3 4");
    FixedOrientedPosition position = new FixedOrientedPosition(3, 4, Orientation.NORTH);
    when(robot.getOrientedPosition()).thenReturn(position);
    engine.run();
    verifyIntroductionPrinted();
    verifyGridInitialisationMessagePrinted();
    verifyCoordinatesPrinted(position);
  }

  @Test
  public void run_GridSizeSetCorrectlyAndRobotOutOfGrid_GridSizeSet() {
    CliEngine engine = createEngineToProcess("3 4");
    FixedOrientedPosition position = new FixedOrientedPosition(4, 5, Orientation.NORTH);
    when(robot.getOrientedPosition()).thenReturn(position);
    engine.run();
    verifyIntroductionPrinted();
    verifyGridInitialisationMessagePrinted();
    verifyCoordinatesPrintedOutOfBounds(position);
  }

  @Test
  public void run_GridSizeSetIncorrectlyTwiceThenCorrectlyAndRobotIsInGrid_GridSizeSet() {
    CliEngine engine = createEngineToProcess("a b", "2.3 6.7", "3 4");
    FixedOrientedPosition position = new FixedOrientedPosition(3, 4, Orientation.NORTH);
    when(robot.getOrientedPosition()).thenReturn(position);
    engine.run();
    verifyIntroductionPrinted();
    verifyGridInitialisationMessagePrinted(3);
    verifyCoordinatesPrinted(position);
  }

  @Test
  public void run_CoordinatesSetThenSingleCommandRun_CommandExecuted() {
    String command = "Test command";
    CliEngine engine = createEngineToProcess("3 4", command);
    FixedOrientedPosition position = new FixedOrientedPosition(3, 4, Orientation.NORTH);
    when(robot.getOrientedPosition()).thenReturn(position);
    engine.run();
    verifyIntroductionPrinted();
    verifyGridInitialisationMessagePrinted();
    verifyCoordinatesPrinted(position, 2);
    verify(executor, times(1)).execute(command, robot);
  }

  @Test
  public void run_CoordinatesSetThenMultipleCommandsRun_CommandsExecuted() {
    String command1 = "Test command 1";
    String command2 = "Test command 2";
    String command3 = "Test command 3";
    CliEngine engine = createEngineToProcess("3 4", command1, command2, command3);
    FixedOrientedPosition position = new FixedOrientedPosition(3, 4, Orientation.NORTH);
    when(robot.getOrientedPosition()).thenReturn(position);
    engine.run();
    verifyIntroductionPrinted();
    verifyGridInitialisationMessagePrinted();
    verifyCoordinatesPrinted(position, 4);

    InOrder inOrder = inOrder(executor);
    inOrder.verify(executor, times(1)).execute(command1, robot);
    inOrder.verify(executor, times(1)).execute(command2, robot);
    inOrder.verify(executor, times(1)).execute(command3, robot);
  }

  @Test
  public void run_CoordinatesSetThenInvalidCommandRun_InvalidMessagePrinted() {
    String command = "Test command";
    InvalidCommandException exception = InvalidCommandException.forCharacterAtIndex(3, command);
    when(executor.execute(command, robot)).thenThrow(exception);

    CliEngine engine = createEngineToProcess("3 4", command);
    FixedOrientedPosition position = new FixedOrientedPosition(3, 4, Orientation.NORTH);
    when(robot.getOrientedPosition()).thenReturn(position);
    engine.run();
    verifyIntroductionPrinted();
    verifyGridInitialisationMessagePrinted();
    verifyCoordinatesPrinted(position, 1);
    verify(outputStream, times(1)).println(eq("Invalid input detected: t"));
    verify(outputStream, times(1)).println(eq(command));
    verify(outputStream, times(1)).println(eq("   ^"));
  }

  private void verifyIntroductionPrinted() {
    verify(outputStream, times(1)).println(
        "Martian Robots\n\n"
            + "Welcome to Martian Robots, an interactive terminal application where you can move "
            + "around a robot on Mars!\n\n"
            + "A robot exists within a grid frame. A robot can move with the following commands:\n"
            + "- F: Move forwards one space\n"
            + "- R: Rotate 90\u00B0 to the right (clockwise)\n"
            + "- R: Rotate 90\u00B0 to the left (anticlockwise)\n"
            + "You can try stringing a sequence of command together, e.g. RFFFLFFRRF\n"
            + "Be careful not to fall off the grid!\n"
    );
  }

  private void verifyGridInitialisationMessagePrinted() {
    verifyGridInitialisationMessagePrinted(1);
  }

  private void verifyGridInitialisationMessagePrinted(int numberOfTimes) {
    verify(outputStream, times(numberOfTimes)).println(
        "Enter the size of the grid in the following format: x y"
    );
  }

  private void verifyCoordinatesPrinted(OrientedPosition position) {
    verifyCoordinatesPrinted(position, 1);
  }

  private void verifyCoordinatesPrinted(OrientedPosition position, int numberOfTimes) {
    verify(outputStream, times(numberOfTimes)).println(
        eq(
            String.format(
                "%d %d %s",
                position.getX(),
                position.getY(),
                position.getOrientation().getShortName()
            )
        )
    );
  }

  private void verifyCoordinatesPrintedOutOfBounds(OrientedPosition position) {
    verify(outputStream).println(
        eq(
            String.format(
                "%d %d %s LOST",
                position.getX(),
                position.getY(),
                position.getOrientation().getShortName()
            )
        )
    );
  }

  private CliEngine createEngineToProcess(String... commands) {
    return createEngineToProcess(Arrays.asList(commands));
  }

  private CliEngine createEngineToProcess(List<String> commands) {
    return createEngineToProcess(String.join(System.lineSeparator(), commands));
  }

  private CliEngine createEngineToProcess(String commands) {
    return new CliEngine(
        robot,
        executor,
        createInputStream(commands),
        outputStream
    );
  }

  private InputStream createInputStream(String text) {
    return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
  }

}