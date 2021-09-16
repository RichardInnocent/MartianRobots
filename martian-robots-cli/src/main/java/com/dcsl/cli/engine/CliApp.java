package com.dcsl.cli.engine;

import com.dcsl.cli.engine.factory.grid.WhitespaceSeparatedGridFactory;
import com.dcsl.cli.engine.factory.robot.WhitespaceSeparatedRobotFactory;
import com.dcsl.commands.executor.SequentialCommandExecutor;

/**
 * An implementation of the application described in the specification.
 */
public class CliApp {

  private final CliEngine cliEngine;

  public CliApp(CliEngine cliEngine) {
    this.cliEngine = cliEngine;
  }

  /**
   * Starts the application.
   */
  public void start() {
    cliEngine.run();
  }

  public static void main(String[] args) {
    StateControlledInputProcessor inputProcessor = new StateControlledInputProcessor(
        new WhitespaceSeparatedGridFactory(),
        new WhitespaceSeparatedRobotFactory(),
        new SequentialCommandExecutor()
    );
    new CliApp(new CliEngine(inputProcessor, System.in, System.out)).start();
  }

}
