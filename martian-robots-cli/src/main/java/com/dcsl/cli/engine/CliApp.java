package com.dcsl.cli.engine;

import com.dcsl.commands.executor.SimpleCommandExecutor;
import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.position.grid.Grid;
import com.dcsl.position.grid.MarsGrid;
import com.dcsl.robots.MarsRobot;
import com.dcsl.robots.MartianRobot;

public class CliApp {

  private final CliEngine cliEngine;

  public CliApp(CliEngine cliEngine) {
    this.cliEngine = cliEngine;
  }

  public void start() {
    cliEngine.run();
  }

  public static void main(String[] args) {
    Grid grid = new MarsGrid(0, 50, 0, 50);
    MarsRobot robot = new MartianRobot(grid, new FixedOrientedPosition(0, 0, Orientation.NORTH));
    new CliApp(new CliEngine(robot, new SimpleCommandExecutor())).start();
  }

}
