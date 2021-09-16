package com.dcsl.cli.engine.state;

import java.util.Optional;

/**
 * A state of the CLI program.
 */
public interface InterfaceState {

  /**
   * Processes the provided CLI input.
   * @param input The input.
   * @return Text output to present to the user, or an empty optional.
   * @throws IllegalArgumentException Thrown if the input from the user is invalid.
   */
  Optional<String> process(String input) throws IllegalArgumentException;

}
