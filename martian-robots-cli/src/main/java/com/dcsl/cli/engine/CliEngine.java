package com.dcsl.cli.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.dcsl.commands.executor.CommandExecutor;
import com.dcsl.commands.parser.InvalidCommandException;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

public class CliEngine implements Runnable {

  private final MarsRobot robot;
  private final CommandExecutor executor;
  private final InputStream inputStream;
  private final PrintStream output;

  public CliEngine(MarsRobot robot, CommandExecutor executor) {
    this(robot, executor, System.in, System.out);
  }

  private CliEngine(
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
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      while (true) {
        String command = reader.readLine();
        executeCommand(command);
      }
    }
  }

  private void executeCommand(String command) {
    try {
      executor.execute(command, robot);
      output.println(robot.getOrientedPosition().map(this::toOutputFormat).orElse("LOST"));
    } catch (InvalidCommandException e) {
      output.println("Invalid input detected: " + e.getCommand().charAt(e.getIndex()));
      output.print(e.getCommand());
      output.println(" ".repeat(e.getIndex()) + "^");
    }
  }

  private String toOutputFormat(OrientedPosition position) {
    // TODO switch to character format
    return String.format("%d %d %s", position.getX(), position.getY(), position.getOrientation());
  }
}
