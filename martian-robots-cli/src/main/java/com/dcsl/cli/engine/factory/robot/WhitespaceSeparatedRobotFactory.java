package com.dcsl.cli.engine.factory.robot;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.robots.MarsRobot;
import com.dcsl.robots.MartianRobot;

public class WhitespaceSeparatedRobotFactory extends AbstractRobotFactory {

  @Override
  public MarsRobot createRobot(String specification) throws IllegalArgumentException {
    String[] components = specification.split("\\s+");
    if (components.length != 3) {
      throw new IllegalArgumentException(
          "Incorrect number of arguments provided. The format should be {x} {y} {orientation}, "
              + "e.g.:\n"
              + "3 4 N"
      );
    }
    int x = Integer.parseInt(components[0]);
    int y = Integer.parseInt(components[1]);
    Orientation orientation = Orientation
        .fromShortName(components[2])
        .orElseThrow(() -> new IllegalArgumentException("Invalid orientation"));

    return new MartianRobot(new FixedOrientedPosition(x, y, orientation));
  }
}
