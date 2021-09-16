package com.dcsl.cli.engine.factory.grid;

import com.dcsl.position.grid.Grid;
import com.dcsl.position.grid.MarsGrid;

/**
 * Creates a grid given a specification in the following format:
 * <pre><code>{x} {y}</code></pre>
 * The minimum {@code x} and {@code y} values are always 0. For example:
 * <pre><code>3 4</code></pre>
 * will create a 3x4 grid starting at the origin.
 */
public class WhitespaceSeparatedGridFactory extends AbstractMarsGridFactory {

  @Override
  public Grid createGrid(String specification) throws IllegalArgumentException {
    String[] components = specification.split("\\s+");
    if (components.length != 2) {
      throw new IllegalArgumentException(
          "Incorrect number of argument specified. Should be two values separated by whitespace, "
              + "e.g. \"3 4\""
      );
    }
    int xMax = Integer.parseInt(components[0]);
    int yMax = Integer.parseInt(components[1]);

    return new MarsGrid(0, xMax, 0, yMax);
  }
}
