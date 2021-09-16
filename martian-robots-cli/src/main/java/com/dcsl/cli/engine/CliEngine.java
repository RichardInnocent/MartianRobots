package com.dcsl.cli.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.OrientedPosition;
import com.dcsl.position.grid.Grid;
import com.dcsl.position.grid.MarsGrid;
import com.dcsl.robots.MarsRobot;

public class CliEngine implements Runnable {

  private final MarsRobot robot;
  private final CommandExecutor executor;
  private final InputStream inputStream;
  private final PrintStream output;

  public CliEngine(MarsRobot robot, CommandExecutor executor) {
    this(robot, executor, System.in, System.out);
  }

  CliEngine(
      MarsRobot robot,
      CommandExecutor executor,
      InputStream inputStream,
      PrintStream output) {
    this.robot = robot;
    this.executor = executor;
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
    printIntroduction();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      Grid grid = initialiseGrid(reader);
      output.println("Grid initialised. You can now command your robot");
      output.println(toOutputFormat(robot.getOrientedPosition(), grid));

      String command;
      while ((command = reader.readLine()) != null) {
        executeCommand(command, grid);
      }
    }
  }

  private void printIntroduction() {
    output.println(
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

  private Grid initialiseGrid(BufferedReader reader) throws IOException {
    while (true) {
      try {
        return initialiseGridAndThrowExceptions(reader);
      } catch (IllegalArgumentException e) {
        output.println("Invalid input: " + e.getMessage());
      }
    }
  }

  private Grid initialiseGridAndThrowExceptions(BufferedReader reader)
      throws IOException, IllegalArgumentException {
    output.println("Enter the size of the grid in the following format: x y");
    String gridCoordinates = reader.readLine();
    String[] components = gridCoordinates.split("\\s+");
    if (components.length != 2) {
      throw new IllegalArgumentException(
          "You must configure the size of the grid by specifying the max x and y coordinates "
              + "respectively. For example:\n3 4"
      );
    }

    int maxX = Integer.parseInt(components[0]);
    int maxY = Integer.parseInt(components[1]);

    return new MarsGrid(0, maxX, 0, maxY);
  }

  private void executeCommand(String command, Grid grid) {
    try {
      executor.execute(command, robot);
      output.println(toOutputFormat(robot.getOrientedPosition(), grid));
    } catch (InvalidCommandException e) {
      output.println("Invalid input detected: " + e.getCommand().charAt(e.getIndex()));
      output.println(e.getCommand());
      output.println(" ".repeat(e.getIndex()) + "^");
    }
  }

  private String toOutputFormat(OrientedPosition position, Grid grid) {
    return String.format(
        "%d %d %s%s",
        position.getX(),
        position.getY(),
        position.getOrientation().getShortName(),
        grid.containsPosition(position) ? "" : " LOST"
    );
  }
}
