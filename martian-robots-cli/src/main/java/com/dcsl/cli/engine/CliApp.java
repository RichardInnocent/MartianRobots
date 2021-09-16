package com.dcsl.cli.engine;

import com.dcsl.cli.engine.factory.grid.WhitespaceSeparatedGridFactory;
import com.dcsl.cli.engine.factory.robot.WhitespaceSeparatedRobotFactory;
import com.dcsl.commands.executor.SimpleCommandExecutor;

public class CliApp {

  private final CliEngine cliEngine;

  public CliApp(CliEngine cliEngine) {
    this.cliEngine = cliEngine;
  }

  public void start() {
    cliEngine.run();
  }

  public static void main(String[] args) {
    StateControlledInputProcessor inputProcessor = new StateControlledInputProcessor(
        new WhitespaceSeparatedGridFactory(),
        new WhitespaceSeparatedRobotFactory(),
        new SimpleCommandExecutor()
    );
    new CliApp(new CliEngine(inputProcessor, System.in, System.out)).start();
  }

}
