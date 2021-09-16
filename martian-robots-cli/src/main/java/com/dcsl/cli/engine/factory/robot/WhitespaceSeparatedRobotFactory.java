package com.dcsl.cli.engine.factory.robot;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.robots.MarsRobot;
import com.dcsl.robots.MarsRobotImpl;

/**
 * An implementation of a {@link RobotFactory} where the specification is expected to be in the
 * following format:
 * <pre><code>{x} {y} {orientation}</code></pre>
 * For example
 * <pre><code>3 4 N</code></pre>
 * will create a robot at {@code [3, 4]}, facing {@link Orientation#NORTH}.
 */
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

    return new MarsRobotImpl(new FixedOrientedPosition(x, y, orientation));
  }
}
