package com.dcsl.cli.engine;

import com.dcsl.position.grid.Grid;
import com.dcsl.robots.MarsRobot;

public interface StateHolder {

  MarsRobot getRobot();
  void setRobot(MarsRobot robot);
  Grid getGrid();
  void setGrid(Grid grid);
}
