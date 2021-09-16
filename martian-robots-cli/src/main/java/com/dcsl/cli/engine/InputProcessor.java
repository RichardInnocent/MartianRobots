package com.dcsl.cli.engine;

import java.util.Optional;

/**
 * Responsible for consuming user input.
 */
public interface InputProcessor {

  /**
   * Processes the user's input.
   * @param input The user's input.
   * @return The output of the action, if appropriate.
   */
  Optional<String> process(String input);

}
