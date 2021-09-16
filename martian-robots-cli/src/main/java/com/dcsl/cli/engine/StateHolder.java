package com.dcsl.cli.engine;

import com.dcsl.position.grid.Grid;
import com.dcsl.robots.MarsRobot;

/**
 * Responsible for managing the fields that are interacted with during the various application
 * states.
 */
public interface StateHolder {

  MarsRobot getRobot();
  void setRobot(MarsRobot robot);
  Grid getGrid();
  void setGrid(Grid grid);
}
